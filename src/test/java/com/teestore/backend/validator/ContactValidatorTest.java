package com.teestore.backend.validator;

import com.teestore.backend.model.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ContactValidatorTest {

    @Test
    public void validateContactValidTest () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo("7894561230");
        ContactValidator.validateContact(u);
    }
    
    @Test
    public void validateContactInvalidTestInvalidContactNo1 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo("1234567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_CONTACT_NUMBER_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidContactNo2 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo("734567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_CONTACT_NUMBER_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidContactNoNull () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo(null);
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_CONTACT_NUMBER_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidEmail1 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.emailweb.com");
        u.setPhoneNo("7234567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidEmail2 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.email@webcom");
        u.setPhoneNo("7234567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidEmail3 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail(".sample.email@web.com");
        u.setPhoneNo("7234567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidEmail4 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample+email@web.com");
        u.setPhoneNo("7234567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidEmailLength () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact");
        u.setContactEmail("sample.fffvxsrvfbhuibjbbhhvhbhhhgghhhkkvhhhohhhohhuhuvyviohhkemail@web.com");
        u.setPhoneNo("7234567892");
        
        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidSubject1 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sample Contact gh ghgh hghgh hg hghghhg ghghghghh ghgggygyg gyg yg yg y");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo("7234567892");

        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_SUBJECT_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidSubject2 () throws Exception {
        Contact u = new Contact();
        u.setMessage("SamplePass@97");
        u.setSubject("Sa pl Co ta ct gh vg fg td hg hj jn u g yu h m n b");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo("7234567892");

        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_SUBJECT_FORMAT", e.getMessage());
    }

    @Test
    public void validateContactInvalidTestInvalidMessageNull () throws Exception {
        Contact u = new Contact();
        u.setMessage(null);
        u.setSubject("Sa pl td hg hj jn u g yu h m n b");
        u.setContactEmail("sample.email@web.com");
        u.setPhoneNo("7234567892");

        Exception e = Assert.assertThrows(Exception.class,
                () -> ContactValidator.validateContact(u));
        Assert.assertEquals("ContactValidator.INVALID_MESSAGE_FORMAT", e.getMessage());
    }
}
