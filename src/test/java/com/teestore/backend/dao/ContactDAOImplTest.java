package com.teestore.backend.dao;

import com.teestore.backend.model.Contact;
import com.teestore.backend.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ContactDAOImplTest {

    @Autowired
    private ContactDAO contactDAO;

    @Test
    public void getMessagesOfUserValidTest () throws Exception {
        List<Contact> contacts = contactDAO.getMessagesOfUser("U1002");
        Assert.assertNotNull(contacts);
        Assert.assertFalse(contacts.isEmpty());
    }

    @Test
    public void getMessagesOfUserInvalidTest () throws Exception {
        List<Contact> contacts = contactDAO.getMessagesOfUser("U1000");
        Assert.assertNull(contacts);
    }

    @Test
    public void getMessageValidTest () throws Exception {
        Contact contacts = contactDAO.getMessage("C101");
        Assert.assertNotNull(contacts);
    }

    @Test
    public void getMessageInvalidTest () throws Exception {
        Contact contacts = contactDAO.getMessage("C100");
        Assert.assertNull(contacts);
    }

    @Test
    public void addMessageValidTest () throws Exception {
        Contact c = new Contact();
        c.setContactEmail("message@test.com");
        c.setMessage("This is a test message");
        c.setSubject("This is a test subject");
        c.setPhoneNo("7894561230");
        User user = new User();
        user.setUserId("U1001");
        c.setUser(user);
        String contacts = contactDAO.addMessage(c);
        Assert.assertTrue(contacts.matches("[C][0-9]{3}"));
    }

    @Test
    public void addMessageValidTestNoUser () throws Exception {
        Contact c = new Contact();
        c.setContactEmail("message@test.com");
        c.setMessage("This is a test message");
        c.setSubject("This is a test subject");
        c.setPhoneNo("7894561230");
        String contacts = contactDAO.addMessage(c);
        Assert.assertTrue(contacts.matches("[C][0-9]{3}"));
    }

    @Test
    public void addMessageValidTestInvalidUser () throws Exception {
        Contact c = new Contact();
        c.setContactEmail("message@test.com");
        c.setMessage("This is a test message");
        c.setSubject("This is a test subject");
        c.setPhoneNo("7894561230");
        User user = new User();
        user.setUserId("U1000");
        c.setUser(user);
        String contacts = contactDAO.addMessage(c);
        Assert.assertTrue(contacts.matches("[C][0-9]{3}"));
    }

    @Test
    public void addMessageInvalidTestInValidSubject () throws Exception {
        Contact c = new Contact();
        c.setContactEmail("message@test.com");
        c.setMessage("This is a test message");
        c.setSubject("This is a test subject which is longer than fifty characters");
        c.setPhoneNo("7894561230");
        User user = new User();
        user.setUserId("U1001");
        c.setUser(user);
        String contacts = contactDAO.addMessage(c);
        Assert.assertTrue(contacts.matches("[C][0-9]{3}"));
    }

    @Test
    public void addMessageInvalidTestInValidPhoneNo () throws Exception {
        Contact c = new Contact();
        c.setContactEmail("message@test.com");
        c.setMessage("This is a test message");
        c.setSubject("This is a test subject");
        c.setPhoneNo("789456123012");
        User user = new User();
        user.setUserId("U1001");
        c.setUser(user);
        String contacts = contactDAO.addMessage(c);
        Assert.assertTrue(contacts.matches("[C][0-9]{3}"));
    }
}
