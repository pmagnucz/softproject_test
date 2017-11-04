package hu.uni.miskolc.iit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SearchVehicleRequestTest {

    SearchVehicleRequest searchVehicleRequest;
    VehicleType vehicleType;
    Date date;

    @Before
    public void setUp() throws Exception {
        this.date = new Date(2017-10-17);
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
        Date expected = date;
        Date actual = searchVehicleRequest.getYearOfManufacture();
        assertEquals(expected,actual);
    }

    @Test
    public void getRentCost() throws Exception {
        double expected = 2546;
        double actual = searchVehicleRequest.getRentCost();
        assertEquals(expected,actual,0);
    }

}