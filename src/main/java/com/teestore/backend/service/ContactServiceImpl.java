package com.teestore.backend.service;

import com.teestore.backend.dao.ContactDAO;
import com.teestore.backend.model.Contact;
import com.teestore.backend.validator.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDAO contactDAO;

    @Override
    public Contact getMessage(String contactId) throws Exception {
        if (contactId == null || contactId.equals(""))
            throw new Exception("ContactService.INVALID_CONTACT_ID");

        Contact contact = contactDAO.getMessage(contactId);
        if (contact == null)
            throw new Exception("ContactService.NO_MESSAGE_RETRIEVED");

        return contact;
    }

    @Override
    public String addMessage(Contact contact) throws Exception {
        if (contact == null)
            throw new Exception("ContactService.INVALID_CONTACT_FORMAT");

        ContactValidator.validateContact(contact);
        String id = contactDAO.addMessage(contact);

        if (id == null)
            throw new Exception("ContactService.MESSAGE_NOT_SAVED");

        return id;
    }

    @Override
    public List<Contact> getMessagesOfUser(String userId) throws Exception {
        if (userId == null || userId.equals(""))
            throw new Exception("ContactService.INVALID_USER_ID");

        List<Contact> contacts = contactDAO.getMessagesOfUser(userId);
        if (contacts == null || contacts.isEmpty())
            throw new Exception("ContactService.NO_MESSAGE_RETRIEVED");

        return contacts;
    }
}
