package com.teestore.backend.api;

import com.teestore.backend.model.Contact;
import com.teestore.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("ContactAPI")
public class ContactAPI {

    @Autowired
    private ContactService contactService;

    @GetMapping("/getMessage/{contactId}")
    public ResponseEntity<Contact> getMessage(@PathVariable String contactId) throws Exception {
        try {
            Contact contact = contactService.getMessage(contactId);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(@RequestBody Contact contact) throws Exception {
        try {
            String id = contactService.addMessage(contact);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @GetMapping("/getUserMessages/{userId}")
    public ResponseEntity<List<Contact>> getMessagesOfUser(@PathVariable String userId) throws Exception {
        try {
            List<Contact> messages = contactService.getMessagesOfUser(userId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
