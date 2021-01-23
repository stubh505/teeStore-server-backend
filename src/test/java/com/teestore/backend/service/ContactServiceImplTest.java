package com.teestore.backend.service;

import com.teestore.backend.dao.ContactDAO;
import com.teestore.backend.model.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ContactServiceImplTest {

    @Mock
    private ContactDAO contactDAO;

    @InjectMocks
    private final ContactService contactService = new ContactServiceImpl();

    @Test
    public void getMessageValidTest () throws Exception {
        Mockito.when(contactDAO.getMessage(Mockito.anyString())).thenReturn(new Contact());
        Contact res = contactService.getMessage("C101");
        Assert.assertNotNull(res);
    }

    @Test
    public void getMessageInvalidTest () throws Exception {
        Mockito.when(contactDAO.getMessage(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.getMessage("C101"));
        Assert.assertEquals("ContactService.NO_MESSAGE_RETRIEVED", e.getMessage());
    }

    @Test
    public void getMessageInvalidTestNullId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.getMessage(null));
        Assert.assertEquals("ContactService.INVALID_CONTACT_ID", e.getMessage());
    }

    @Test
    public void getMessageInvalidTestEmptyId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.getMessage(""));
        Assert.assertEquals("ContactService.INVALID_CONTACT_ID", e.getMessage());
    }

    @Test
    public void addMessageValidTest () throws Exception {
        Contact c = new Contact();
        c.setPhoneNo("7894561230");
        c.setSubject("this is a test");
        c.setContactEmail("this.is@atest.com");
        c.setMessage("this is a test");
        Mockito.when(contactDAO.addMessage(Mockito.any())).thenReturn("C101");
        String res = contactService.addMessage(c);
        Assert.assertNotNull(res);
    }

    @Test
    public void addMessageInvalidTest () throws Exception {
        Contact c = new Contact();
        c.setPhoneNo("7894561230");
        c.setSubject("this is a test");
        c.setContactEmail("this.is@atest.com");
        c.setMessage("this is a test");
        Mockito.when(contactDAO.addMessage(Mockito.any())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.addMessage(c));
        Assert.assertEquals("ContactService.MESSAGE_NOT_SAVED", e.getMessage());
    }

    @Test
    public void addMessageInvalidTestNullId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.addMessage(null));
        Assert.assertEquals("ContactService.INVALID_CONTACT_FORMAT", e.getMessage());
    }

    @Test
    public void getMessageOfUserValidTest () throws Exception {
        Mockito.when(contactDAO.getMessagesOfUser(Mockito.anyString())).thenReturn(Collections.singletonList(new Contact()));
        List<Contact> res = contactService.getMessagesOfUser("U1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void getMessageOfUserInvalidTest () throws Exception {
        Mockito.when(contactDAO.getMessagesOfUser(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.getMessagesOfUser("C101"));
        Assert.assertEquals("ContactService.NO_MESSAGE_RETRIEVED", e.getMessage());
    }

    @Test
    public void getMessageOfUserInvalidTestNullId () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> contactService.getMessagesOfUser(""));
        Assert.assertEquals("ContactService.INVALID_USER_ID", e.getMessage());
    }
}
