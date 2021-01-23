package com.teestore.backend.dao;

import com.teestore.backend.model.Contact;

import java.util.List;

public interface ContactDAO {
    Contact getMessage(String contactId) throws Exception;

    String addMessage(Contact contact) throws Exception;

    List<Contact> getMessagesOfUser(String userId) throws Exception;
}
