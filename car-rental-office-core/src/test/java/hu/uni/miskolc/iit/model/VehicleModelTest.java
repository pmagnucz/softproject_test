package hu.uni.miskolc.iit.model;

import static org.junit.Assert.*;

import org.junit.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * Created by rozgonyi on 2017. 10. 15..
 */

public class VehicleModelTest {
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception{
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-09", dateTimeFormatter);
        this.vehicle = new Vehicle(1L, VehicleType.CAR, "Volkswagen", date, 15000, 5, 178, VehicleStatusType.FREE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() throws Exception {
        Long expected = 1L;
        Long actual = vehicle.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void getType() throws Exception {
        String expected = VehicleType.CAR.name();
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
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate expected = LocalDate.parse("2001-09", dateTimeFormatter);
        LocalDate actual = vehicle.getYearOfManufacture();
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