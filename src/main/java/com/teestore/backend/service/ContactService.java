package com.teestore.backend.service;

import com.teestore.backend.model.Contact;

import java.util.List;

public interface ContactService {
    Contact getMessage(String contactId) throws Exception;

    String addMessage(Contact contact) throws Exception;

    List<Contact> getMessagesOfUser(String userId) throws Exception;
}
