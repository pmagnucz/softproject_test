package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.UserManagementController;
import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.exception.NegativeValueException;
import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by rozgonyi on 2017.11.13..
 */

public class UserControllerTest {

    private UserManagementController userController;
    private UserManagementService userService;
    private UserManagementDao userManagementDao;

    private Customer user;
    private Customer user2;

    private CreateUserRequest userRequest;

    private UpdateUserRequest updateUserRequest;

    @Before
    public void setUp() {
        userManagementDao = mock(UserManagementDao.class);

        userService = new UserManagementServiceImpl(userManagementDao);
        userController = new UserManagementController(userService);

        user = new Customer();

        user.setId(1L);
        user.setUserName("Teszt Elek");
        user.setPhoneNumber("+36302587913");
        user.setAddress("3525 Miskolc, Szentpáli utca 12.");
        user.setYearOfBirth(1991);
        user.setUserId("1");
        user.setDrivingLicenceNumber("test1");

        user2 = new Customer();

        user2.setId(2L);
        user2.setUserName("Gipsz Jakab");
        user2.setPhoneNumber("06205896324");
        user2.setAddress("1011 Budapest, Tesztelés utca 3.");
        user2.setYearOfBirth(1991);
        user2.setUserId("2");
        user2.setDrivingLicenceNumber("test2");

        userRequest = new CreateUserRequest();
        userRequest.setUserName(user.getUserName());
        userRequest.setAddress(user.getAddress());
        userRequest.setPhoneNumber(user.getPhoneNumber());
        userRequest.setUserId(user.getUserId());
        userRequest.setYearOfBirth(user.getYearOfBirth());
        userRequest.setDrivingLicenceNumber(user.getDrivingLicenceNumber());

        updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(1L);
        updateUserRequest.setUserName(user2.getUserName());
        updateUserRequest.setAddress(user2.getAddress());
        updateUserRequest.setPhoneNumber(user2.getPhoneNumber());
        updateUserRequest.setCompany(false);
        updateUserRequest.setCustomer(true);
        updateUserRequest.setUserId("2");
        updateUserRequest.setYearOfBirth(1991);
        updateUserRequest.setDrivingLicenceNumber(user2.getDrivingLicenceNumber());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createUser() throws NegativeValueException, UserNotFoundException, UserTypeDoesNotExistException {
        User expected = user;
        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(user);

        replay(userManagementDao);

        User actual = userController.createUser(userRequest).getBody();

        assertEquals(expected,actual);

    }

    @Test
    public void updateUser() throws UserNotFoundException {
        user2.setId(1L);
        expect(userManagementDao.getUserById(anyLong())).andReturn(user);
        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(user2);

        replay(userManagementDao);

        User actual = userController.updateUser(updateUserRequest).getBody();

        assertEquals(user2, actual);
    }

    @Test
    public void deleteUser() throws NegativeValueException, UserNotFoundException {
        expect(userManagementDao.getUserById(anyLong())).andReturn(user);
        userManagementDao.deleteUser(user);
        expectLastCall();
        replay(userManagementDao);

        userController.deleteUser(user);
    }

    @Test
    public void getUserById() throws UserNotFoundException {
        expect(userManagementDao.getUserById(anyLong())).andReturn(user);

        replay(userManagementDao);

        User actual = userController.getUserById(user.getId()).getBody();
        assertEquals(user,actual);
    }

    @Test
    public void getAllUser() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        expect(userManagementDao.getUsers()).andReturn(users);

        replay(userManagementDao);

        List<User> actual = userController.getAllUser().getBody();
        assertEquals(users, actual);

    }

    @Test
    public void getUsersByFilterOptions() throws UserNotFoundException {
        SearchUserRequest searchUserRequest = new SearchUserRequest();

        searchUserRequest.setAddress("3525 Miskolc, Szentpáli utca 12.");
        searchUserRequest.setDrivingLicenceNumber("test1");
        searchUserRequest.setPhoneNumber("+36302587913");
        searchUserRequest.setUserName("Teszt Elek");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        expect(userManagementDao.getUsers()).andReturn(users);

        replay(userManagementDao);

        List<User> expected = new ArrayList<>();
        expected.add(user);

        List<User> actual = userController.getUserByFilterOptions(searchUserRequest).getBody();

        assertEquals(expected,actual);

    }
}