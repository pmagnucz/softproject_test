package hu.uni.miskolc.iit.model;

import org.junit.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class RentModelTest {

    private Rent rent;

    @Before
    public void setUp() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString =  "2017-09-01";
        Date startDate = dateFormat.parse(startDateString);
        String endDateString =  "2017-10-01";
        Date endDate = dateFormat.parse(endDateString);
        this.rent = new Rent(1L,5L,6L,7L,startDate,endDate,true,56,300,
                100000,50000,0,150000,false);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() throws Exception {
        Long expected = 1L;
        Long actual = rent.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void getCustomerId() throws Exception {
        Long expected = 5L;
        Long actual = rent.getCustomerId();
        assertEquals(expected,actual);

    }

    @Test
    public void getCompanyId() throws Exception {
        Long expected = 6L;
        Long actual = rent.getCompanyId();
        assertEquals(expected,actual);
    }

    @Test
    public void getVehicleId() throws Exception {
        Long expected = 7L;
        Long actual = rent.getVehicleId();
        assertEquals(expected,actual);
    }

    @Test
    public void getStartDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString =  "2017-09-01";
        Date expected = dateFormat.parse(startDateString);
        Date actual = rent.getStartDate();
        assertEquals(expected,actual);
    }

    @Test
    public void getEndDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString =  "2017-10-01";
        Date expected = dateFormat.parse(endDateString);
        Date actual = rent.getEndDate();
        assertEquals(expected,actual);
    }

    @Test
    public void isDurationExtendable() throws Exception {
        boolean expected = true;
        boolean actual = rent.isDurationExtendable();
        assertEquals(expected,actual);
    }

    @Test
    public void getExtendedHours() throws Exception {
        int expected = 56;
        int actual = rent.getExtendedHours();
        assertEquals(expected,actual);
    }

    @Test
    public void getKmUsed() throws Exception {
        int expected = 300;
        int actual = rent.getKmUsed();
        assertEquals(expected,actual);
    }

    @Test
    public void getDayFee() throws Exception {
        double expected = 100000;
        double actual = rent.getDayFee();
        assertEquals(expected,actual,0);
    }

    @Test
    public void getKmFee() throws Exception {
        double expected = 50000;
        double actual = rent.getKmFee();
        assertEquals(expected,actual,0);
    }

    @Test
    public void getOtherFee() throws Exception {
        double expected = 0;
        double actual = rent.getOtherFee();
        assertEquals(expected,actual,0);
    }

    @Test
    public void getTotalFee() throws Exception {
        double expected = 150000;
        double actual = rent.getTotalFee();
        assertEquals(expected,actual,0);
    }

    @Test
    public void isPaid() throws Exception {
        boolean expected = false;
        boolean actual = rent.isPaid();
        assertEquals(expected,actual);
    }

}