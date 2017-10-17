package hu.uni.miskolc.iit.model;

import org.junit.*;

import static org.junit.Assert.*;

public class CustomerTest {

    Customer customer;

    @Before
    public void setUp() throws Exception {
        this.customer = new Customer("asd", 1995 ,"abc123");
        customer.setAddress("valami út valahol");
        customer.setPhoneNumber("12345");
        customer.setUserName("valaki");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserId() throws Exception {
        String expected = "asd";
        String actual = customer.getUserId();
        assertEquals(expected,actual);
    }

    @Test
    public void getYearOfBirth() throws Exception {
        int expected = 1995;
        int actual = customer.getYearOfBirth();
        assertEquals(expected,actual);
    }

    @Test
    public void getDrivingLicenceNumber() throws Exception {
        String expected = "abc123";
        String actual = customer.getDrivingLicenceNumber();
        assertEquals(expected,actual);
    }

    @Test
    public void getPhoneNumber() throws Exception {
        String expected = "12345";
        String actual = customer.getPhoneNumber();
        assertEquals(expected,actual);
    }

    @Test
    public void getUserName() throws Exception {
        String expected = "valaki";
        String actual = customer.getUserName();
        assertEquals(expected,actual);
    }

    @Test
    public void getAddress() throws Exception {
        String expected = "valami út valahol";
        String actual = customer.getAddress();
        assertEquals(expected,actual);
    }

}