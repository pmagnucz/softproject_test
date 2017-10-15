package hu.uni.miskolc.iit.model;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @org.junit.Before
    public void setUp() throws Exception {
        String name="Kis Józsi";
        String address="1234 Budapest Alma u. 30";
        String phoneNumber="06301234567";
        this.user = new User(name,address,phoneNumber);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getPhoneNumber() throws Exception {
        String expected = "06301234567";
        String actual = user.getPhoneNumber();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getUserName() throws Exception {
        String expected = "Kis Józsi";
        String actual = user.getUserName();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getAddress() throws Exception {
        String expected = "1234 Budapest Alma u. 30";
        String actual = user.getAddress();
        assertEquals(expected,actual);
    }

}