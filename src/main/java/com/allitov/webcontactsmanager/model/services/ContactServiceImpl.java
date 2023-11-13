package com.allitov.webcontactsmanager.model.services;

import com.allitov.webcontactsmanager.model.data.Contact;
import com.allitov.webcontactsmanager.model.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return contactRepository.findById(id).orElseThrow();
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactRepository.update(contact);
    }

    @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
