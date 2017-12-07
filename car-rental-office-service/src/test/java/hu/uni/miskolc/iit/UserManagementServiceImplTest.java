package hu.uni.miskolc.iit;


import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDaoImpl;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.Company;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.model.SearchUserRequest;
import hu.uni.miskolc.iit.model.CreateUserRequest;
import hu.uni.miskolc.iit.model.UpdateUserRequest;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import java.io.File;


public class UserManagementServiceImplTest {
    private UserManagementService userManagementService;
    private UserManagementDao userManagementDao = new UserManagementDaoImpl(new File("src/test/resources/database.json"));
    private User user;
    private Customer customer;
    private Company company;
   
// TODO UserManagementServiceImplTest
    @Before
    public void before()
    {

        userManagementService = new UserManagementServiceImpl(userManagementDao);
        userManagementDao = mock(UserManagementDao.class);
       
        
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
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");
    }

    @Test
    public void readDatabase()
    {
        Assert.assertNotNull(userManagementService.getUsers());
    }

    @Test
    public void createNewUser() throws Exception {
        
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");

        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(customer);
        replay(userManagementDao);

        CreateUserRequest createUserRequest =
                new CreateUserRequest();

        User actual = userManagementService.createNewUser(createUserRequest);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);

    }

    @Test
    public void getUserById() throws Exception {
        
        customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");
       
        expect(userManagementDao.getUserById(anyLong())).andReturn(customer);
        replay(userManagementDao);

        User actual = userManagementService.getUserById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);

    }

    @Test
    public void getUserByFilterOptions() throws Exception {
        
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");

        
        Company company = new Company();
        company.setId(2L);
        company.setPhoneNumber("+363231231231");
        company.setAddress("Miskolc");
        company.setCompanyId("aaa111");
        company.setBillingAddress("Debrecen");

        
        List<User> users = new ArrayList<>();
        users.add(customer);
        users.add(company);
        expect(userManagementDao.getUsers()).andReturn(users);
        
        replay(userManagementDao);
        
        SearchUserRequest searchUserRequest =
                new SearchUserRequest("Jóska István","+363744746","Miskolc","646445465");
      
        List<User> expected = new ArrayList<>();
        expected.add(customer);
        List<User> actual = userManagementService.getUserByFilterOptions(searchUserRequest);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void getUsers() throws Exception {
        
        customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");

        List<User> expected = new ArrayList<>();
        expected.add(customer);

        expect(userManagementDao.getUsers()).andReturn(expected);
        replay(userManagementDao);

        List<User> actual = userManagementService.getUsers();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void updateUser() throws Exception {
        
        Customer customerUpdated = new Customer();

        customerUpdated.setId(1L);
        customerUpdated.setPhoneNumber("+363231231231");
        customerUpdated.setAddress("Miskolc");
        customerUpdated.setUserName("Jóska István");
        customerUpdated.setUserId("aaa111");
        customerUpdated.setYearOfBirth(1990);
        customerUpdated.setDrivingLicenceNumber("21213565");
        
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(customerUpdated);
        
        replay(userManagementDao);
        
        User actual = userManagementService.updateUser(updateUserRequest);

        Assert.assertEquals(customerUpdated, actual);

    }

    @Test
    public void deleteUser() throws Exception {
              
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");

        expect(userManagementDao.exists(anyObject())).andReturn(true);
        expectLastCall();
        replay(userManagementDao);

        userManagementService.deleteUser(customer);

    }

    @Test
    public void countUser() throws Exception {

        customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");

        List<User> userList = new ArrayList<>();
        userList.add(customer);

        expect(userManagementDao.getUsers()).andReturn(userList);
        replay(userManagementDao);

        int actual = userManagementService.countUser();

        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual);

    }
}

