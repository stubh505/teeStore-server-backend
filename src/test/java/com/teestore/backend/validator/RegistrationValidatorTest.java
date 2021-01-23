package com.teestore.backend.validator;

import com.teestore.backend.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrationValidatorTest {

    @Test
    public void validateRegistrationValidTest () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        RegistrationValidator.validateRegistration(u);
    }

    @Test
    public void validateRegistrationInvalidTestInvalidName1 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User1");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_USERNAME_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidName2 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User Name Test");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_USERNAME_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidName3 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("S User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_USERNAME_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidNameNull () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName(null);
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_USERNAME_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidPassword1 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_PASSWORD_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidPassword2 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass78");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_PASSWORD_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidPassword3 () throws Exception {
        User u = new User();
        u.setPassword("samplep@79ass");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_PASSWORD_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidPassword4 () throws Exception {
        User u = new User();
        u.setPassword("SAMPLE@45");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_PASSWORD_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidPasswordNull () throws Exception {
        User u = new User();
        u.setPassword(null);
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_PASSWORD_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidContactNo1 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("1234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_CONTACT_NUMBER_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidContactNo2 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("734567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_CONTACT_NUMBER_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidContactNoNull () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber(null);
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_CONTACT_NUMBER_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidEmail1 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.emailweb.com");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidEmail2 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@webcom");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidEmail3 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId(".sample.email@web.com");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidEmail4 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample+email@web.com");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidEmailLength () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.fffvxsrvfbhuibjbbhhvhbhhhgghhhkkvhhhohhhohhuhuvyviohhkemail@web.com");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_EMAIL_ID_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidDOB1 () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(LocalDate.now().minusYears(5));
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_DATE_OF_BIRTH_FORMAT", e.getMessage());
    }

    @Test
    public void validateRegistrationInvalidTestInvalidDOBNull () throws Exception {
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7234567892");
        u.setDateOfBirth(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> RegistrationValidator.validateRegistration(u));
        Assert.assertEquals("RegistrationValidator.INVALID_DATE_OF_BIRTH_FORMAT", e.getMessage());
    }
}