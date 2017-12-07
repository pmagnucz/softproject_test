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
        user.setYearOfBirth(1990);
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
        updateUserRequest.setUserName(user.getUserName());
        updateUserRequest.setAddress(user2.getAddress());
        updateUserRequest.setPhoneNumber(user2.getPhoneNumber());
        updateUserRequest.setCompany(false);
        updateUserRequest.setCustomer(true);
        updateUserRequest.setUserId("1");
        updateUserRequest.setYearOfBirth(user2.getYearOfBirth());
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

    public void updateUser() throws UserNotFoundException {
        user2.setId(user.getId());
        user2.setUserName(user.getUserName());
        user2.setUserId(user.getUserId());

        expect(userManagementDao.getUserById(anyLong())).andReturn(user);
        expect(userManagementDao.addUser(anyObject(User.class))).andReturn(user);

        replay(userManagementDao);

        User actual = userController.updateUser(updateUserRequest).getBody();

        assertEquals(user2,actual);
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

    /*
    private static final String USER_NAME = "Teszt Elek";
    private static final String ADDRESS = "3525 Miskolc, Szentpáli utca 12.";
    private static final String PHONENUMBER = "+36302587913";

    private static final String USER_NAME_2 = "Gipsz Jakab";
    private static final String ADDRESS_2 = "1011 Budapest, Tesztelés utca 3.";
    private static final String PHONENUMBER_2 = "06205896324";

    private User user;
    private User user2;

    private CreateUserRequest userRequest;
    private CreateUserRequest userRequest2;

    private SearchUserRequest searchUserRequest;
*/
/*
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
        user = new User(1L, USER_NAME, ADDRESS, PHONENUMBER);
        user2 = new User(2L, USER_NAME_2, ADDRESS_2, PHONENUMBER_2);

        userRequest = new CreateUserRequest();
        userRequest.setUserName(USER_NAME);
        userRequest.setAddress(ADDRESS);
        userRequest.setPhoneNumber(PHONENUMBER);
        userRequest.setUserId("1");
        userRequest.setYearOfBirth(1996);
        userRequest.setDrivingLicenceNumber("105213851412");

        userRequest2 = new CreateUserRequest();
        userRequest2.setUserName(USER_NAME_2);
        userRequest2.setAddress(ADDRESS_2);
        userRequest2.setPhoneNumber(PHONENUMBER_2);
        userRequest2.setUserId("2");
        userRequest2.setYearOfBirth(1986);
        userRequest2.setDrivingLicenceNumber("25687452");

        searchUserRequest = new SearchUserRequest();
        searchUserRequest.setUserName(USER_NAME);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createUser() throws Exception {
        HttpEntity<CreateUserRequest> entity = new HttpEntity<CreateUserRequest>(userRequest, headers);
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8080/user/create", HttpMethod.POST, entity, User.class);

        assertEquals(user, response.getBody());
    }

    @Test
    public void getAllUser() throws Exception {
        List<User> expectedUsers = new ArrayList<User>();

        ResponseEntity<Integer> responseCount = restTemplate.exchange("http://localhost:8080/user/count", HttpMethod.GET, null, Integer.class);

        int id = 0;
        while(expectedUsers.size() != responseCount.getBody()){
            HttpEntity<Long> entity = new HttpEntity<Long>(Long.valueOf(id), headers);
            ResponseEntity<User> responseFindOne = restTemplate.exchange("http://localhost:8080/user/getUser/", HttpMethod.GET, entity, User.class);

            if(responseFindOne.getBody().getId() != null) {
                expectedUsers.add(responseFindOne.getBody());
            }

            id++;
        }

        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};

        ResponseEntity<List<User>> responseGetAll  = restTemplate.exchange("http://localhost:8080/user/getAll",HttpMethod.GET,null, responseType);

        assertEquals(expectedUsers, responseGetAll.getBody());
    }

    @Test
    public void updateUser() throws Exception {
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8080/user/update", HttpMethod.POST, entity, User.class);

        assertEquals(user, response.getBody());
    }

    @Test
    public void deleteUser() throws Exception {
        HttpEntity<CreateUserRequest> entity = new HttpEntity<CreateUserRequest>(userRequest2, headers);
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8080/user/create", HttpMethod.POST, entity, User.class);

        HttpEntity<User> userHttpEntity = new HttpEntity<User>(response.getBody(), headers);

        ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange("http://localhost:8080/user/delete", HttpMethod.DELETE, userHttpEntity, HttpStatus.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getUserById() throws Exception {
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8080/user/getUser/" + user.getId(), HttpMethod.GET, entity, User.class);

        assertEquals(user, response.getBody());
    }

    @Test
    public void getUserByFilterOptions() throws Exception {
        List<User> expectedList = new ArrayList<User>();
        expectedList.add(user);

        HttpEntity<SearchUserRequest> entity = new HttpEntity<SearchUserRequest>(searchUserRequest, headers);

        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> response = restTemplate.exchange("http://localhost:8080/user/search/", HttpMethod.POST, entity, responseType);

        assertEquals(expectedList, response.getBody());
    }
*/
}