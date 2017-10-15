package hu.uni.miskolc.iit.model;

import static org.junit.Assert.*;

import org.junit.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rozgonyi on 2017. 10. 15..
 */

public class VehicleModelTest {
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String dateString =  "2001-09";
        Date yearOfManufactureDate = dateFormat.parse(dateString);
        this.vehicle = new Vehicle(1, VehichleType.CAR, "Volkswagen", yearOfManufactureDate, 15000, 5, 178, VehicleStatusType.FREE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() throws Exception {
        int expected = 1;
        int actual = vehicle.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void getType() throws Exception {
        String expected = VehichleType.CAR.name();
        String actual = vehicle.getType().name();
        assertEquals(expected, actual);
    }

    @Test
    public void getManufacturer() throws Exception {
        String expected = "Volkswagen";
        String actual = vehicle.getManufacturer();
        assertEquals(expected, actual);
    }

    @Test
    public void getYearOfManufacture() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String dateString =  "2001-09";
        Date expected = dateFormat.parse(dateString);
        Date actual = vehicle.getYearOfManufacture();
        assertEquals(expected, actual);
    }

    @Test
    public void getRentCost() throws Exception {
        double expected = 15000;
        double actual = vehicle.getRentCost();
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getPersons() throws Exception {
        int expected = 5;
        int actual = vehicle.getPersons();
        assertEquals(expected, actual);
    }

    @Test
    public void getPerformance() throws Exception {
        double expected = 178;
        double actual = vehicle.getPerformance();
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getVehicleStatus() throws Exception {
        String expected = VehicleStatusType.FREE.name();
        String actual = vehicle.getVehicleStatus().name();
        assertEquals(expected, actual);
    }
}