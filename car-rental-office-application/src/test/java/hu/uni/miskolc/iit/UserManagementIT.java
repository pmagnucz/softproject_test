package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.UserManagementController;
import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDaoImpl;
import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 11. 22..
 */
public class UserManagementIT {

    private UserManagementController controller;

    @Before
    public void setUp(){
        UserManagementDao userManagementDao = new UserManagementDaoImpl(new File("src/test/resources/userDatabase.json"));
        userManagementDao.clear();
        UserManagementService service = new UserManagementServiceImpl(userManagementDao);
        controller = new UserManagementController(service);
    }

    @After
    public void tearDown(){
        // Clean User repository between tests

    }

    @Test
    public void createCustomerTest() throws UserTypeDoesNotExistException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("customer 1");
        createUserRequest.setAddress("Customer's Address Example St. 23");
        createUserRequest.setPhoneNumber("06202122545");
        createUserRequest.setUserId("993115AS");
        createUserRequest.setDrivingLicenceNumber("DLN254789");


        Customer expected = new Customer();
        expected.setId(1L);
        expected.setPhoneNumber("06202122545");
        expected.setUserName("customer 1");
        expected.setAddress("Customer's Address Example St. 23");
        expected.setUserId("993115AS");
        expected.setDrivingLicenceNumber("DLN254789");

        Customer actual = (Customer) controller.createUser(createUserRequest).getBody();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createCompanyTest() throws UserTypeDoesNotExistException {
        //TODO: ennek utána kell járni
        Customer representative = new Customer();
        representative.setId(1L);
        representative.setPhoneNumber("06202122545");
        representative.setUserName("customer 1");
        representative.setAddress("Customer's Address Example St. 23");
        representative.setUserId("993115AS");

        CreateUserRequest companyRequest = new CreateUserRequest();
        companyRequest.setUserName("company_1");
        companyRequest.setAddress("Company's Address Example St. 10");
        companyRequest.setPhoneNumber("06236522545");
        companyRequest.setCompanyId("CO_100232");
        companyRequest.setBillingAddress("Company's Billing Address Example St. 10");
        companyRequest.setRepresentative(new Customer());

        Company expected = new Company();
        expected.setId(2L);
        expected.setUserName("company_1");
        expected.setAddress("Company's Address Example St. 10");
        expected.setPhoneNumber("06236522545");
        expected.setCompanyId("CO_100232");
        expected.setBillingAddress("Company's Billing Address Example St. 10");
        expected.setRepresentative(new Customer());

        Company actual = (Company) controller.createUser(companyRequest).getBody();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = UserTypeDoesNotExistException.class)
    public void createUserTestExceptionalFlow() throws UserTypeDoesNotExistException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("customer 1");
        createUserRequest.setAddress("Customer's Address Example St. 23");
        createUserRequest.setPhoneNumber("06202122545");
        createUserRequest.setDrivingLicenceNumber("DLN254789");

        controller.createUser(createUserRequest);
    }

    @Test
    public void getAllUsersTest_EmptyRepository(){
        List<User> users = controller.getAllUser().getBody();
        Assert.assertEquals(0, users.size());
    }

    public void updateUserTest(){}

    public void updateUserTestExceptionalFlow(){}

    @Test
    public void deleteUserTest() throws UserNotFoundException, UserTypeDoesNotExistException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("customer 1");
        createUserRequest.setAddress("Customer's Address Example St. 23");
        createUserRequest.setPhoneNumber("06202122545");
        createUserRequest.setUserId("993115AS");
        createUserRequest.setDrivingLicenceNumber("DLN254789");
        User actual = controller.createUser(createUserRequest).getBody();

        controller.deleteUser(actual);

        int usersSize = controller.getAllUser().getBody().size();
        Assert.assertEquals(0, usersSize);
    }

    public void deleteUserTestExceptionalFlow() throws UserNotFoundException {
        // Call delete with not existing user should raise UserNotFoundException
        User user = new User();
        user.setId(0L);

        controller.deleteUser(user);
    }

    public void getUserByIdTest(){}

    public void getUserByIdTestExceptionalFlow() throws UserNotFoundException {
        // Call getUserById with not existing id should raise UserNotFoundException
        controller.getUserById(0L);
    }

    public void getUserByFilterOptionsTest(){}

    @Test(expected = UserNotFoundException.class)
    public void getUserByFilterOptionsExceptionalFlow() throws UserNotFoundException {
        SearchUserRequest searchUserRequest = new SearchUserRequest();
        controller.getUserByFilterOptions(searchUserRequest);
    }

}
