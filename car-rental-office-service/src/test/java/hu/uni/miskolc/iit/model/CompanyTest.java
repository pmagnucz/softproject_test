package hu.uni.miskolc.iit.model;

import static org.junit.Assert.*;
import org.junit.*;

public class CompanyTest {

    private Company company;
    private Customer customer;

    @org.junit.Before
    public void setUp() throws Exception {
        this.customer= new Customer("lalala",2000, "asd123"  );
        this.company= new Company("asd", "2658, Kukutyin, Béla utca 16", customer );
        company.setAddress("4566, Budapest, Valami 56");
        company.setPhoneNumber("5425458");
        company.setUserName("User");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getCompanyId() throws Exception {
        String expected = "asd";
        String actual = company.getCompanyId();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getBillingAddress() throws Exception {
        String expected = "2658, Kukutyin, Béla utca 16";
        String actual = company.getBillingAddress();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getRepresentative() throws Exception {
        Customer expected = new Customer("lalala",2000, "asd123"  );
        Customer actual = company.getRepresentative();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getPhoneNumber() throws Exception {
        String expected = "5425458";
        String actual = company.getPhoneNumber();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getUserName() throws Exception {
        String expected = "User";
        String actual = company.getUserName();
        assertEquals(expected,actual);
    }

    @org.junit.Test
    public void getAddress() throws Exception {
        String expected = "4566, Budapest, Valami 56";
        String actual =  company.getAddress();
        assertEquals(expected, actual);
    }

}