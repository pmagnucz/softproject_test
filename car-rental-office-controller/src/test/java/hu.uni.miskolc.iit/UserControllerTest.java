package hu.uni.miskolc.iit;
import hu.uni.miskolc.iit.model.CreateUserRequest;
import hu.uni.miskolc.iit.model.User;
import org.junit.*;

/**
 * Created by rozgonyi on 2017.11.13..
 */

public class UserControllerTest {
    private static final String USER_NAME = "Teszt Elek";
    private static final String ADDRESS = "3525 Miskolc, Szentpáli utca 12.";
    private static final String PHONENUMBER = "+36302587913";

    private User user;

    private CreateUserRequest userRequest;

    @Before
    public void setUp() throws Exception {
        user = new User(1L, USER_NAME, ADDRESS, PHONENUMBER);

        userRequest = new CreateUserRequest();
        userRequest.setUserName(USER_NAME);
        userRequest.setAddress(ADDRESS);
        userRequest.setPhoneNumber(PHONENUMBER);
        userRequest.setUserId("1");
        userRequest.setYearOfBirth(1996);
        userRequest.setDrivingLicenceNumber("105213851412");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createUser() throws Exception {

    }

    @Test
    public void getAllUser() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void getUserById() throws Exception {

    }

    @Test
    public void getUserByFilterOptions() throws Exception {

    }
}