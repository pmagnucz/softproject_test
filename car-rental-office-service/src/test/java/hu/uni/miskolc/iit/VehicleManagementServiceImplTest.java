package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.easymock.EasyMock;
import org.easymock.internal.IMocksControlState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class VehicleManagementServiceImplTest {
    private VehicleManagementDao vehicleManagementDao;
    private VehicleManagementService vehicleManagementService;

    private Vehicle vehicle;
    private Ship ship;
    private Car car;

    @Before
    public void setUp() throws Exception {
        vehicleManagementDao = EasyMock.mock(VehicleManagementDao.class);
        vehicleManagementService = new VehicleManagementServiceImpl(vehicleManagementDao);

        vehicle = new Vehicle();

        vehicle.setId(1L);
        vehicle.setType(VehicleType.CAR);
        vehicle.setManufacturer("Ford");
        vehicle.setYearOfManufacture(new Date());
        vehicle.setRentCost(15000);
        vehicle.setPersons(5);
        vehicle.setPerformance(1500.24);
        vehicle.setVehicleStatus(VehicleStatusType.FREE);

        car = new Car();

        car.setPlateNumber("AAA-123");
        car.setVehicleIdentificationNumber("ID123123AS");
        car.setDrawBar(false);
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Opel");
        car.setYearOfManufacture(new Date());
        car.setRentCost(40.0D);
        car.setPersons(5);
        car.setPerformance(75.0D);
        car.setVehicleStatus(VehicleStatusType.FREE);

        ship = new Ship();

        ship.setId(2L);
        ship.setLength(2000.0);
        ship.setWithTrailer(true);
        ship.setType(VehicleType.SHIP);
        ship.setShipId("M");
        ship.setManufacturer("Lambo");
        ship.setPerformance(120);
        ship.setPersons(6);
        ship.setRentCost(13000);
        ship.setVehicleStatus(VehicleStatusType.RESERVED);
        ship.setYearOfManufacture(new Date());
    }

    @Test
    public void addNewVehicleCar() throws Exception {
        expect(vehicleManagementDao.addVehicle(anyObject(Vehicle.class))).andReturn(car);
        replay(vehicleManagementDao);

        Vehicle actual = vehicleManagementService.addNewVehicle(car);

        Assert.assertNotNull(actual);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void addNewVehicleShip() throws Exception {
        expect(vehicleManagementDao.addVehicle(anyObject(Vehicle.class))).andReturn(ship);
        replay(vehicleManagementDao);

        Vehicle actual = vehicleManagementService.addNewVehicle(ship);

        Assert.assertNotNull(actual);
        Assert.assertEquals(ship, actual);
    }

    @Test
    public void getVehicleById() throws Exception {
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);
        replay(vehicleManagementDao);

        Vehicle actual = vehicleManagementService.getVehicleById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void getVehicles() throws Exception {
        List<Vehicle> expected = new ArrayList<>();
        expected.add(car);

        expect(vehicleManagementDao.getVehicles()).andReturn(expected);
        replay(vehicleManagementDao);

        List<Vehicle> actual = vehicleManagementService.getVehicles();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateVehicle() throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date manufactureDate = null;

        try {
            manufactureDate = format.parse("1960-01");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Car expectedCar = new Car();
        expectedCar.setId(1L);
        expectedCar.setManufacturer("Ford2222");
        expectedCar.setType(VehicleType.CAR);
        expectedCar.setYearOfManufacture(manufactureDate);
        expectedCar.setRentCost(25000);
        expectedCar.setPersons(2);
        expectedCar.setPerformance(2500.24);
        expectedCar.setVehicleStatus(VehicleStatusType.FREE);
        expectedCar.setPlateNumber("AAA-222");
        expectedCar.setVehicleIdentificationNumber("222222sd");
        expectedCar.setDrawBar(true);

        UpdateVehicleRequest updateVehicleRequest = new UpdateVehicleRequest();

        updateVehicleRequest.setId(1L);
        updateVehicleRequest.setType(VehicleType.CAR);
        updateVehicleRequest.setManufacturer("Ford2222");
        updateVehicleRequest.setYearOfManufacture(manufactureDate);
        updateVehicleRequest.setRentCost(25000);
        updateVehicleRequest.setPersons(2);
        updateVehicleRequest.setCar(true);
        updateVehicleRequest.setPerformance(2500.24);
        updateVehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        updateVehicleRequest.setPlateNumber("AAA-222");
        updateVehicleRequest.setVehicleIdentificationNumber("222222sd");
        updateVehicleRequest.setDrawBar(true);

        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);
        expect(vehicleManagementDao.addVehicle(anyObject(Vehicle.class))).andReturn(car);

        replay(vehicleManagementDao);

        Vehicle actual = vehicleManagementService.updateVehicle(updateVehicleRequest);

        Assert.assertEquals(expectedCar, actual);
    }

    public void removeVehicleCar() throws Exception {
        expect(vehicleManagementDao.exists(anyObject())).andReturn(true);
        expectLastCall();
        replay(vehicleManagementDao);

        vehicleManagementService.removeVehicle(car);
    }

}