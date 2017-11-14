package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.model.CreateUserRequest;
import hu.uni.miskolc.iit.model.User;
import org.junit.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rozgonyi on 2017.11.13..
 */

public class UserControllerTest {
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
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>(){};

        ResponseEntity<List<User>> entity = restTemplate.exchange("http://localhost:8080/user/getAll", HttpMethod.GET, null, responseType);
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

    }

    @Test
    public void getUserByFilterOptions() throws Exception {

    }
}