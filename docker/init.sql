CREATE SCHEMA IF NOT EXISTS contacts_schema;

DROP TABLE IF EXISTS contacts_schema.contact;

CREATE TABLE IF NOT EXISTS contacts_schema.contact (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL
);