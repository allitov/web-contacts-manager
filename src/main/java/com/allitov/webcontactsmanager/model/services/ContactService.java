package com.allitov.webcontactsmanager.model.services;

import com.allitov.webcontactsmanager.model.data.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(Long id);

    Contact saveContact(Contact contact);

    Contact updateContact(Contact contact);

    void deleteContactById(Long id);
}
