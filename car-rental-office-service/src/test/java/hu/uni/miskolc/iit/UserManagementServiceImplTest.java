package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDaoImpl;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.Company;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import java.io.File;


public class UserManagementServiceImplTest {
    private UserManagementService userManagementService;
   
// TODO UserManagementServiceImplTest
    @Before
    public void before()
    {
        UserManagementDao userManagementDao = new UserManagementDaoImpl(new File("src/test/resources/database.json"));
        userManagementService = new UserManagementServiceImpl(userManagementDao);
       
        
        user = new User();
        customer = new Customer();
        
        
        user.setId(1L);
        user.setPhoneNumber("+363231231231");
        user.setAddress("Miskolc");
        user.setUserName("Jóska István");
        
        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId(1L);
        customer.setYearOfBirth(1990);
        customer.setDrivingLincenceNumber("21213565");
    }

    @Test
    public void readDatabase()
    {
        Assert.assertNotNull(userManagementService.getUsers());
    }

    @Test
    public void createNewUser() throws Exception {
        
        customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId(1L);
        customer.setYearOfBirth(1990);
        customer.setDrivingLincenceNumber("21213565");

        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(customer);
        replay(userManagementDao);

        User actual = userManagementService.addNewUser(customer);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);

    }

    @Test
    public void getUserById() throws Exception {

    }

    @Test
    public void getUserByFilterOptions() throws Exception {

    }

    @Test
    public void getUsers() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void countUser() throws Exception {

    }
}
