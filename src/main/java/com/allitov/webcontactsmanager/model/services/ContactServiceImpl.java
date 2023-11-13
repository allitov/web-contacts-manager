package com.allitov.webcontactsmanager.model.services;

import com.allitov.webcontactsmanager.exceptions.NoSuchRecordException;
import com.allitov.webcontactsmanager.model.data.Contact;
import com.allitov.webcontactsmanager.model.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException("User tried to find contact with id = " + id));
    }

    @Override
    public Contact saveContact(Contact contact) {
        if (isContactDataInvalid(contact)) {
            throw new InvalidParameterException("User tried to save contact: " + contact);
        }

        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        if (isContactDataInvalid(contact)) {
            throw new InvalidParameterException("User tried to update contact: " + contact);
        }
        getContactById(contact.getId());

        return contactRepository.update(contact);
    }

    @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

    private boolean isContactDataInvalid(Contact contact) {
        return contact.getFirstName() == null || contact.getLastName() == null
                || contact.getEmail() == null || contact.getPhone() == null;
    }
}
