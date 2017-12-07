package hu.uni.miskolc.iit.dao;

import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class UserManagementDaoImplTest {
    private UserManagementDao userManagementDao;

    @Before
    public void setUp() throws Exception {
        userManagementDao = new UserManagementDaoImpl(new File("src/test/resources/userDatabase.json"));
        userManagementDao.clear();
    }

    @Test
    public void addUser() throws Exception {
        Customer customer = new Customer();
        customer.setUserId("TestCustomerId");
        customer.setYearOfBirth(1989);
        customer.setDrivingLicenceNumber("AS887678");
        customer.setId(1L);
        customer.setPhoneNumber("12334565");
        customer.setUserName("userName");
        customer.setAddress("Dummy Address St. 23.");

        User actual = userManagementDao.addUser(customer);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);
    }

    @Test
    public void getUserById() throws Exception {
        Customer customer = new Customer();
        customer.setUserId("TestCustomerId");
        customer.setYearOfBirth(1989);
        customer.setDrivingLicenceNumber("AS887678");
        customer.setId(1L);
        customer.setPhoneNumber("12334565");
        customer.setUserName("userName");
        customer.setAddress("Dummy Address St. 23.");
        userManagementDao.addUser(customer);

        User actual = userManagementDao.getUserById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);
    }

    @Test
    public void getUsers() throws Exception {
        Customer customer = new Customer();
        customer.setUserId("TestCustomerId");
        customer.setYearOfBirth(1989);
        customer.setDrivingLicenceNumber("AS887678");
        customer.setId(1L);
        customer.setPhoneNumber("12334565");
        customer.setUserName("userName");
        customer.setAddress("Dummy Address St. 23.");
        userManagementDao.addUser(customer);

        List<User> expected = new ArrayList<User>();
        expected.add(customer);

        List<User> actual = (List<User>) userManagementDao.getUsers();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteUser() throws Exception {
        Customer customer = new Customer();
        customer.setUserId("TestCustomerId");
        customer.setYearOfBirth(1989);
        customer.setDrivingLicenceNumber("AS887678");
        customer.setId(1L);
        customer.setPhoneNumber("12334565");
        customer.setUserName("userName");
        customer.setAddress("Dummy Address St. 23.");
        userManagementDao.addUser(customer);

        List<User> expected = new ArrayList<User>();

        userManagementDao.deleteUser(customer);

        List<User> actual = (List<User>) userManagementDao.getUsers();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void exists() throws Exception {
        Customer customer = new Customer();
        customer.setUserId("TestCustomerId");
        customer.setYearOfBirth(1989);
        customer.setDrivingLicenceNumber("AS887678");
        customer.setId(1L);
        customer.setPhoneNumber("12334565");
        customer.setUserName("userName");
        customer.setAddress("Dummy Address St. 23.");
        userManagementDao.addUser(customer);

        boolean actual = userManagementDao.exists(customer);

        Assert.assertNotNull(actual);
        Assert.assertTrue(actual);
    }
}