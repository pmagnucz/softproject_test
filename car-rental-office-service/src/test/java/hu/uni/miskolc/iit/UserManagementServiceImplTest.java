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
    private UserManagementDao userManagementDao;

    private Customer customer;
    private Company company;
   
// TODO UserManagementServiceImplTest
    @Before
    public void before()
    {

        userManagementService = new UserManagementServiceImpl(userManagementDao);
        userManagementDao = mock(UserManagementDao.class);

        customer = new Customer();
        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId("aaa111");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("21213565");

        company = new Company();
        company.setCompanyId("companyId");
        company.setBillingAddress("Budapest, Ganz utca 12");
        company.setRepresentative(customer);
        company.setId(2L);
        company.setPhoneNumber("363231243512");
        company.setUserName("testCompany");
        company.setAddress("Budapest, Andrássy krt. 10");
    }

    @Test
    public void readDatabase()
    {
        Assert.assertNotNull(userManagementService.getUsers());
    }

    @Test
    public void createNewUser() throws Exception {

        expect(userManagementDao.addUser(anyObject(Customer.class))).andReturn(customer);
        replay(userManagementDao);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("testUser");
        createUserRequest.setAddress("Miskolc");
        createUserRequest.setPhoneNumber("+363231231231");
        createUserRequest.setUserId("userId");
        createUserRequest.setYearOfBirth(1990);
        createUserRequest.setDrivingLicenceNumber("21213565");

        User actual = userManagementService.createNewUser(createUserRequest);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);

    }

    @Test
    public void getUserById() throws Exception {
        expect(userManagementDao.getUserById(anyLong())).andReturn(customer);
        replay(userManagementDao);

        User actual = userManagementService.getUserById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(customer, actual);

    }

    @Test
    public void getUserByFilterOptions() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(customer);

        expect(userManagementDao.getUsers()).andReturn(users);
        replay(userManagementDao);

        SearchUserRequest searchUserRequest = new SearchUserRequest("Jóska István","+363231231231","Miskolc","21213565");
        List<User> actual = userManagementService.getUserByFilterOptions(searchUserRequest);

        Assert.assertNotNull(actual);
        Assert.assertEquals(users,actual);
    }

    @Test
    public void getUsers() throws Exception {
        List<User> expected = new ArrayList<>();
        expected.add(customer);
        expected.add(company);

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
        customerUpdated.setUserId("NewUserId");
        customerUpdated.setYearOfBirth(1990);
        customerUpdated.setDrivingLicenceNumber("21213565");

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(userManagementDao.getUserById(1L)).andReturn(customer);
        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(customerUpdated);

        replay(userManagementDao);


        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(1L);
        updateUserRequest.setUserName("Jóska István");
        updateUserRequest.setUserId("NewUserId");
        updateUserRequest.setCustomer(true);

        User actual = userManagementService.updateUser(updateUserRequest);

        Assert.assertEquals(customerUpdated, actual);
    }

    @Test
    public void deleteUser() throws Exception {
        expect(userManagementDao.exists(anyObject())).andReturn(true);
        expect(userManagementDao.getUserById(1L)).andReturn(customer);
        expectLastCall();
        replay(userManagementDao);

        userManagementService.deleteUser(customer);
    }

    @Test
    public void countUser() throws Exception {
        List<User> expected = new ArrayList<>();
        expected.add(customer);
        expected.add(company);

        expect(userManagementDao.getUsers()).andReturn(expected);
        replay(userManagementDao);

        int actual = userManagementService.countUser();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.size(), actual);

    }
}

