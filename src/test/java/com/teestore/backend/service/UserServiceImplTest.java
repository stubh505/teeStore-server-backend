package com.teestore.backend.service;

import com.teestore.backend.dao.UserDAO;
import com.teestore.backend.model.Address;
import com.teestore.backend.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private final UserService userService= new UserServiceImpl();

    @Test
    public void addUserValidTest() throws Exception{
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        u.setAddresses(null);

        Mockito.when(userDAO.addUser(Mockito.any())).thenReturn(new User());
        User user= userService.addUser(u);
        Assert.assertNotNull(user);
    }

    @Test
    public void addUserInvalidTest() throws Exception{
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        u.setAddresses(null);

        Mockito.when(userDAO.addUser(Mockito.any())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                ()-> userService.addUser(u));
        Assert.assertEquals("UserService.INVALID_REGISTRATION",e.getMessage());
    }

    @Test
    public void loginUserValidTest1() throws Exception{
        User u = new User();
        u.setPassword("Scott@123");
        u.setContactNumber("8884967823");

        User u2 = new User();
        u2.setPassword("3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3");
        u2.setContactNumber("8884967823");

        Mockito.when(userDAO.getUserByContactNumber(Mockito.anyString())).thenReturn(u2);
        User user= userService.loginUser(u);
        Assert.assertNotNull(user);
    }

    @Test
    public void loginUserValidTest2() throws Exception{
        User u = new User();
        u.setPassword("Scott@123");
        u.setEmailId("scott@stark.com");

        User u2 = new User();
        u2.setPassword("3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3");
        u2.setEmailId("scott@stark.com");

        Mockito.when(userDAO.getUserByEmailId(Mockito.anyString())).thenReturn(u2);
        User user= userService.loginUser(u);
        Assert.assertNotNull(user);
    }

    @Test
    public void loginUserInValidTest1() throws Exception{
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setEmailId("sample.email@web.com");

        Mockito.when(userDAO.getUserByEmailId(Mockito.anyString())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.loginUser(u));
        Assert.assertEquals("UserService.INVALID_CREDENTIALS",e.getMessage());
    }

    @Test
    public void loginUserInValidTest2() throws Exception{
        User u = new User();
        u.setPassword("SamplePass@97");
        u.setEmailId("sample.email@web.com");

        Mockito.when(userDAO.getUserByEmailId(Mockito.anyString())).thenReturn(new User());
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.loginUser(u));
        Assert.assertEquals("UserService.INVALID_CREDENTIALS",e.getMessage());
    }

    @Test
    public void getUserValidTest() throws Exception{
        User u = new User();
        u.setUserId("U1002");

        Mockito.when(userDAO.getUser(Mockito.anyString())).thenReturn(new User());
        User user= userService.getUser(u.getUserId());
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserInValidTest1() throws Exception{
        Mockito.when(userDAO.getUser(Mockito.anyString())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> userService.getUser("U101"));
        Assert.assertEquals("UserService.USER_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getUserInvalidTest2() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()-> userService.getUser(null));
        Assert.assertEquals("UserService.Invalid_User",e.getMessage());
    }

    @Test
    public void getUserInvalidTest3() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()-> userService.getUser(""));
        Assert.assertEquals("UserService.Invalid_User",e.getMessage());
    }

    @Test
    public void editUserValidTest() throws Exception{
        User u = new User();
        u.setUserId("U1001");
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        Mockito.when(userDAO.editUser(Mockito.anyString(),Mockito.any())).thenReturn("U1001");
        String uId= userService.editUser(u.getUserId(),u);
        Assert.assertTrue(uId.matches("[U][0-9]{4}"));
    }

    @Test
    public void edituserInvalidTest1() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.editUser(null,new User()));
        Assert.assertEquals("UserService.INVALID_USER",e.getMessage());
    }

    @Test
    public void edituserInvalidTest2() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.editUser("",new User()));
        Assert.assertEquals("UserService.INVALID_USER",e.getMessage());
    }

    @Test
    public void edituserInvalidTest3() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.editUser("U1001",null));
        Assert.assertEquals("UserService.INVALID_USER",e.getMessage());
    }

    @Test
    public void edituserInvalidTest4() throws Exception{
        Mockito.when(userDAO.editUser(Mockito.anyString(),Mockito.any())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.editUser("U1001",new User()));
        Assert.assertEquals("UserService.UNABLE_TO_EDIT_USER",e.getMessage());
    }

    @Test
    public void addAddressValidTest() throws Exception{
        User u = new User();
        u.setUserId("U1002");
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        u.setAddresses(null);

        Address address = new Address();
        address.setStreet("Sample Street");
        address.setCity("Sample City");
        address.setState("Sample State");
        address.setPinCode(112233);

        Mockito.when(userDAO.addAddress(Mockito.anyString(),Mockito.any())).thenReturn("A1001");
        String aId= userService.addAddress(u.getUserId(),address);
        Assert.assertTrue(aId.matches("[A][0-9]{4}"));
    }

    @Test
    public void addAddressInvalidTest1() throws Exception{
        Address address = new Address();
        address.setStreet("Sample Street");
        address.setCity("Sample City");
        address.setState("Sample State");
        address.setPinCode(112233);
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.addAddress(null, address));
        Assert.assertEquals("UserService.INVALID_USER_ID",e.getMessage());
    }

    @Test
    public void addAddressInvalidTest2() throws Exception{
        Address address = new Address();
        address.setStreet("Sample Street");
        address.setCity("Sample City");
        address.setState("Sample State");
        address.setPinCode(112233);
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.addAddress("",address));
        Assert.assertEquals("UserService.INVALID_USER_ID",e.getMessage());
    }

    @Test
    public void addAddressInvalidTest3() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.addAddress("U1001",null));
        Assert.assertEquals("UserService.INVALID_ADDRESS",e.getMessage());
    }

    @Test
    public void addAddressInvalidTest4() throws Exception{
        User u = new User();
        u.setUserId("U1002");
        u.setPassword("SamplePass@97");
        u.setUserName("Sample User");
        u.setEmailId("sample.email@web.com");
        u.setContactNumber("7894561230");
        u.setDateOfBirth(LocalDate.now().minusYears(25));
        u.setAddresses(null);

        Address address = new Address();
        address.setStreet("Sample Street");
        address.setCity("Sample City");
        address.setState("Sample State");
        address.setPinCode(112233);

        Mockito.when(userDAO.addAddress(Mockito.anyString(),Mockito.any())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> userService.addAddress(u.getUserId(),address));
        Assert.assertEquals("UserService.UNABLE_TO_ADD_ADDRESS",e.getMessage());
    }

    @Test
    public void deleteAddressValidTest() throws Exception{
        User u= new User();
        u.setUserId("U1001");
        Address a= new Address();
        a.setAddressId("A1001");

        Mockito.when(userDAO.deleteAddress(Mockito.anyString(),Mockito.anyString())).thenReturn("A1001");
        String aId= userService.deleteAddress(u.getUserId(),a.getAddressId());
        Assert.assertTrue(aId.matches("[A][0-9]{4}"));
    }

    @Test
    public void deleteAddressInvalidTest1() throws Exception{
        Address a = new Address();
        a.setAddressId("A1001");
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.deleteAddress(null, a.getAddressId()));
        Assert.assertEquals("UserService.INVALID_USER_ID",e.getMessage());
    }

    @Test
    public void deleteAddressInvalidTest2() throws Exception{
        Address a = new Address();
        a.setAddressId("A1001");
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.deleteAddress("",a.getAddressId()));
        Assert.assertEquals("UserService.INVALID_USER_ID",e.getMessage());
    }

    @Test
    public void deleteAddressInvalidTest3() throws Exception{
        User u= new User();
        u.setUserId("U1001");
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.deleteAddress(u.getUserId(),null));
        Assert.assertEquals("UserService.INVALID_ADDRESS_ID",e.getMessage());
    }

    @Test
    public void deleteAddressInvalidTest4() throws Exception{
        User u= new User();
        u.setUserId("U1001");
        Exception e= Assert.assertThrows(Exception.class,
                ()->userService.deleteAddress(u.getUserId(),""));
        Assert.assertEquals("UserService.INVALID_ADDRESS_ID",e.getMessage());
    }

    @Test
    public void deleteAddressInvalidTest5() throws Exception{
        User u= new User();
        u.setUserId("U1001");
        Address a= new Address();
        a.setAddressId("A1001");

        Mockito.when(userDAO.deleteAddress(Mockito.anyString(),Mockito.any())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> userService.deleteAddress(u.getUserId(),a.getAddressId()));
        Assert.assertEquals("UserService.UNABLE_TO_DELETE_ADDRESS",e.getMessage());
    }


}
