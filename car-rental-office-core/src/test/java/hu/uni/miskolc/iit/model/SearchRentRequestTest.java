package hu.uni.miskolc.iit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SearchRentRequestTest {

    SearchRentRequest searchRentRequest;

    @Before
    public void setUp() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString =  "2017-09-01";
        Date startDate = dateFormat.parse(startDateString);
        String endDateString =  "2017-10-01";
        Date endDate = dateFormat.parse(endDateString);
        searchRentRequest = new SearchRentRequest(1,2,3,startDate,endDate);
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString =  "2017-09-01";
        Date expected = dateFormat.parse(startDateString);
        Date actual = searchRentRequest.getStartDate();
        assertEquals(expected,actual);
    }

    @Test
    public void getEndDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString =  "2017-10-01";
        Date expected = dateFormat.parse(endDateString);
        Date actual = searchRentRequest.getEndDate();
        assertEquals(expected,actual);
    }

}