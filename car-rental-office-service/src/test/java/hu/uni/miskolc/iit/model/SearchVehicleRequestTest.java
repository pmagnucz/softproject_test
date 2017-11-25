package hu.uni.miskolc.iit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import static org.junit.Assert.*;

public class SearchVehicleRequestTest {

    SearchVehicleRequest searchVehicleRequest;
    VehicleType vehicleType;
    LocalDate date;

    @Before
    public void setUp() throws Exception {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        this.date = LocalDate.parse("2017-10", dateTimeFormatter);
        this.vehicleType = VehicleType.CAR;
        this.searchVehicleRequest = new SearchVehicleRequest(vehicleType, "Opel", date, 2546);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() throws Exception {
        VehicleType expected = vehicleType;
        VehicleType actual = searchVehicleRequest.getType();
        assertEquals(expected,actual);
    }

    @Test
    public void getManufacturer() throws Exception {
        String expected = "Opel";
        String actual = searchVehicleRequest.getManufacturer();
        assertEquals(expected,actual);
    }

    @Test
    public void getYearOfManufacture() throws Exception {
        LocalDate expected = date;
        LocalDate actual = searchVehicleRequest.getYearOfManufacture();
        assertEquals(expected,actual);
    }

    @Test
    public void getRentCost() throws Exception {
        double expected = 2546;
        double actual = searchVehicleRequest.getRentCost();
        assertEquals(expected,actual,0);
    }

}