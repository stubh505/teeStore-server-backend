package com.teestore.backend.validator;

import com.teestore.backend.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserValidatorLoginTest {

    @Test
    public void validateUserForLoginValidTest1() throws Exception{
        User u = new User();
        u.setEmailId("sample.email@web.com");
        u.setPassword("SamplePass@97");
        UserValidatorLogin.validateUserForLogin(u);
    }

    @Test
    public void validateUserForLoginValidTest2() throws Exception{
        User u = new User();
        u.setContactNumber("7894561230");
        u.setPassword("SamplePass@97");
        UserValidatorLogin.validateUserForLogin(u);
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidUser() throws Exception{
        User u = new User();
        u.setEmailId(null);
        u.setContactNumber(null);
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_USER",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidContactNo1() throws Exception{
        User u = new User();
        u.setContactNumber("1234567892");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_CONTACT_NUMBER_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidContactNo2() throws Exception{
        User u = new User();
        u.setContactNumber("734567892");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_CONTACT_NUMBER_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidEmailId1() throws Exception{
        User u = new User();
        u.setEmailId("sample.emailweb.com");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_EMAIL_ID_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidEmailId2() throws Exception{
        User u = new User();
        u.setEmailId("sample.email@webcom");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_EMAIL_ID_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidEmailId3() throws Exception{
        User u = new User();
        u.setEmailId(".sample.email@web.com");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_EMAIL_ID_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidEmailId4() throws Exception{
        User u = new User();
        u.setEmailId("sample+email@web.com");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_EMAIL_ID_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidEmailIdLength() throws Exception{
        User u = new User();
        u.setEmailId("sample.fffvxsrvfbhuibjbbhhvhbhhhgghhhkkvhhhohhhohhuhuvyviohhkemail@web.com");
        u.setPassword("SamplePass@97");
        Exception e= Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_EMAIL_ID_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidPassword1() throws Exception{
        User u = new User();
        u.setContactNumber("7894561230");
        u.setPassword("SamplePass");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_PASSWORD_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidPassword2() throws Exception{
        User u = new User();
        u.setContactNumber("7894561230");
        u.setPassword("SamplePass78");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_PASSWORD_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidPassword3() throws Exception{
        User u = new User();
        u.setEmailId("sample.email@web.com");
        u.setPassword("samplep@79ass");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_PASSWORD_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidPassword4() throws Exception{
        User u = new User();
        u.setEmailId("sample.email@web.com");
        u.setPassword("SAMPLE@45");
        Exception e = Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_PASSWORD_FORMAT",e.getMessage());
    }

    @Test
    public void validateUserForLoginInvalidTestInvalidPasswordNull() throws Exception{
        User u = new User();
        u.setEmailId("sample.email@web.com");
        u.setPassword(null);
        Exception e = Assert.assertThrows(Exception.class,
                ()-> UserValidatorLogin.validateUserForLogin(u));
        Assert.assertEquals("UserValidatorLogin.INVALID_PASSWORD_FORMAT",e.getMessage());
    }

}
