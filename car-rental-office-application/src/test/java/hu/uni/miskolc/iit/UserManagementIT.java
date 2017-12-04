package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.UserManagementController;
import hu.uni.miskolc.iit.model.CreateUserRequest;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by pmagnucz on 2017. 11. 22..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class UserManagementIT {

    private UserManagementController controller;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp(){
        UserManagementService service = new UserManagementServiceImpl(repository);
        controller = new UserManagementController(service);
    }

    @Test
    public void createCustomerTest(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("userName");
        createUserRequest.setAddress("");
        createUserRequest.setPhoneNumber("");
        createUserRequest.setUserId("asd");


        Customer expected = new Customer();
        expected.setId(1L);
        expected.setPhoneNumber("");
        expected.setUserName("userName");
        expected.setAddress("");
        expected.setUserId("asd");

        User actual = controller.createUser(createUserRequest).getBody();

        Assert.assertEquals(expected, actual);
    }

    public void createCompanyTest(){
    CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("userName");
        createUserRequest.setAddress("");
        createUserRequest.setPhoneNumber("");
        createUserRequest.setUserId("asd");
        createUserRequest.setBillingAddress("");


        Company expected = new Company();
        expected.setId(1L);
        expected.setPhoneNumber("");
        expected.setUserName("userName");
        expected.setAddress("");
        expected.setUserId("asd");
        expected.setBillingAddress("");

        User actual = controller.createUser(createUserRequest).getBody();

        Assert.assertEquals(expected, actual);
    }

    public void createUserTestExceptionalFlow(){}

    public void getAllUsersTest(){}

    public void updateUserTest(){}

    public void updateUserTestExceptionalFlow(){}

    public void deleteUserTest(){}

    public void deleteUserTestExceptionalFlow(){}

    public void getUserByIdTest(){}

    public void getUserByIdTestExceptionalFlow(){}

    public void getUserByFilterOptionsTest(){}

    public void getUserByFilterOptionsExceptionalFlow(){}

}
