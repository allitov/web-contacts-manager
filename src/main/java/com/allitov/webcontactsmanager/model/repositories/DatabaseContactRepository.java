package com.allitov.webcontactsmanager.model.repositories;

import com.allitov.webcontactsmanager.model.data.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DatabaseContactRepository implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = """
            SELECT *
            FROM contact;
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT *
            FROM contact
            WHERE id = ?;
            """;

    private static final String SQL_SAVE = """
            INSERT INTO task (first_name, last_name, email, phone)
            VALUES (?, ?, ?, ?);
            """;

    private static final String SQL_UPDATE = """
            UPDATE contact
            SET first_name = ?, last_name = ?, email = ?, phone = ?
            WHERE id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE
            FROM task
            WHERE id = ?;
            """;

    @Override
    public List<Contact> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, this::mapRowToContact);
    }

    // TODO: проверить поиск, если одинаковых записей несколько
    @Override
    public Optional<Contact> findById(Long id) {
        Contact contact = DataAccessUtils.singleResult(jdbcTemplate.query(
                SQL_FIND_BY_ID,
                new ArgumentPreparedStatementSetter(new Object[] {id}),
                new RowMapperResultSetExtractor<>(this::mapRowToContact, 1))
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        SQL_SAVE,
                        Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(Arrays.asList(
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getEmail(),
                        contact.getPhone())
                );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        Long id = keyHolder.getKey().longValue();
        contact.setId(id);

        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        Contact foundContact = findById(contact.getId()).orElse(null);
        if (foundContact != null) {
            jdbcTemplate.update(
                    SQL_UPDATE,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhone()
            );
            return contact;
        }

        System.out.println("Такой записи в базе нет!");

        return contact;
    }

    //TODO: проверить на удаление несуществующей записи
    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    private Contact mapRowToContact(ResultSet rs, int rowNum) throws SQLException {
        return Contact.builder()
                .id(rs.getLong(Contact.Fields.id))
                .firstName(rs.getString(Contact.Fields.firstName))
                .lastName(rs.getString(Contact.Fields.lastName))
                .email(rs.getString(Contact.Fields.email))
                .phone(rs.getString(Contact.Fields.phone))
                .build();
    }
}