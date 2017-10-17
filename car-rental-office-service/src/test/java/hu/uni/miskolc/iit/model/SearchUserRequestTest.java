package hu.uni.miskolc.iit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchUserRequestTest {

    SearchUserRequest searchUserRequest;

    @Before
    public void setUp() throws Exception {
        this.searchUserRequest = new SearchUserRequest("Valaki Valahol", 132123,"3546 Bélafalva, Rövid út 33", "3828791");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserName() throws Exception {
        String expected = "Valaki Valahol";
        String actual = searchUserRequest.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    public void getPhoneNumber() throws Exception {
        long expected = 132123;
        long actual = searchUserRequest.getPhoneNumber();
        assertEquals(expected,actual);
    }

    @Test
    public void getAddress() throws Exception {
        String expected = "3546 Bélafalva, Rövid út 33";
        String actual = searchUserRequest.getAddress();
        assertEquals(expected,actual);
    }

    @Test
    public void getDrivingLicenceNumber() throws Exception {
        String expected = "3828791";
        String actual = searchUserRequest.getDrivingLicenceNumber();
        assertEquals(expected,actual);
    }

}