package hu.uni.miskolc.iit.dao;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.model.VehicleType;

import java.util.ArrayList;
import java.util.Date;
import hu.uni.miskolc.iit.model.VehicleStatusType;

import hu.uni.miskolc.iit.model.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class VehicleManagementDaoImplTest {
    //TODO: VehicleManagementDaoImplTest ExceptionalFlow
    private VehicleManagementDao vehicleManagementDao;

    @Before
    public void setUp() throws Exception {
        vehicleManagementDao = new VehicleManagementDaoImpl(new File("src/test/resources/vehicleDatabase.json"));
        vehicleManagementDao.clear();
    }

    @Test
    public void addVehicle() throws Exception {
        Car car = new Car();
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

        Vehicle actual = vehicleManagementDao.addVehicle(car);

        Assert.assertNotNull(actual);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void getVehicleById() throws Exception {
        Car car = new Car();
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
        vehicleManagementDao.addVehicle(car);

        Vehicle actual =vehicleManagementDao.getVehicleById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void getVehicles() throws Exception {
        Car car = new Car();
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
        vehicleManagementDao.addVehicle(car);

        List<Vehicle> expectedList = new ArrayList<Vehicle>();
        expectedList.add(car);

        List<Vehicle> actual = (List<Vehicle>) vehicleManagementDao.getVehicles();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expectedList, actual);
    }

    @Test
    public void deleteVehicle() throws Exception {
        Car car = new Car();
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
        vehicleManagementDao.addVehicle(car);

        List<Vehicle> expectedList = new ArrayList<Vehicle>();
        vehicleManagementDao.deleteVehicle(car);
        List<Vehicle> actual = (List<Vehicle>) vehicleManagementDao.getVehicles();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expectedList, actual);
    }

    @Test
    public void exists() throws Exception {
        Car car = new Car();
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
        vehicleManagementDao.addVehicle(car);

        boolean actual = vehicleManagementDao.exists(car);
        Assert.assertTrue(actual);
    }

}