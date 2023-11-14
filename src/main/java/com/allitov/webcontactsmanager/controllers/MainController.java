package com.allitov.webcontactsmanager.controllers;

import com.allitov.webcontactsmanager.model.data.Contact;
import com.allitov.webcontactsmanager.model.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ContactService contactService;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());

        return "index";
    }

    @GetMapping("/contact/edit")
    public String showEditForm(Model model) {
        model.addAttribute("contact", new Contact());

        return "edit";
    }

    @GetMapping("/contact/edit/{id}")
    public String editContact(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);

        return "edit";
    }

    @PostMapping("/contact/update")
    public String updateContact(@ModelAttribute Contact contact) {
        if (contact.getId() == null) {
            contactService.saveContact(contact);
        } else {
            contactService.updateContact(contact);
        }

        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContactById(id);

        return "redirect:/";
    }
}
