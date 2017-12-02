package hu.uni.miskolc.iit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

public class SearchRentRequestTest {

    SearchRentRequest searchRentRequest;

    @Before
    public void setUp() throws Exception {
        searchRentRequest = new SearchRentRequest(1,2,3,LocalDate.parse("2017-09-01"),LocalDate.parse("2017-10-01"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCustomerId() throws Exception {
        int expected = 1;
        int actual = searchRentRequest.getCustomerId();
        assertEquals(expected,actual);
    }

    @Test
    public void getCompanyId() throws Exception {
        int expected = 2;
        int actual = searchRentRequest.getCompanyId();
        assertEquals(expected,actual);
    }

    @Test
    public void getVehicleId() throws Exception {
        int expected = 3;
        int actual = searchRentRequest.getVehicleId();
        assertEquals(expected,actual);
    }

    @Test
    public void getStartDate() throws Exception {
        LocalDate expected = LocalDate.parse("2017-09-01");
        LocalDate actual = searchRentRequest.getStartDate();
        assertEquals(expected,actual);
    }

    @Test
    public void getEndDate() throws Exception {
        LocalDate expected = LocalDate.parse("2017-10-01");
        LocalDate actual = searchRentRequest.getEndDate();
        assertEquals(expected,actual);
    }

}