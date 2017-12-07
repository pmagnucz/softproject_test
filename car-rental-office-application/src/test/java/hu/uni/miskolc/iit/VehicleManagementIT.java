package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.VehicleController;
import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.dao.VehicleManagementDaoImpl;
import hu.uni.miskolc.iit.exception.ExistingVehiclePlateNumber;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rozgonyi on 2017. 12. 07..
 */
public class VehicleManagementIT {
    private VehicleController controller;

    private VehicleManagementDao vehicleManagementDao;

    @Before
    public void setUp(){
        vehicleManagementDao = new VehicleManagementDaoImpl(new File("src/test/resources/vehicleDatabase.json"));
        vehicleManagementDao.clear();
        VehicleManagementService service = new VehicleManagementServiceImpl(vehicleManagementDao);
        controller = new VehicleController(service);
    }

    @After
    public void tearDown() throws Exception{
        vehicleManagementDao.clear();
    }

    @Test
    public void createCarTest() throws NotSupportedVehicleTypeException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2001-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setId(1L);
        vehicleRequest.setType(VehicleType.CAR);
        vehicleRequest.setManufacturer("Volkswagen");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(12000);
        vehicleRequest.setPersons(5);
        vehicleRequest.setPerformance(175);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("LOT-749");
        vehicleRequest.setVehicleIdentificationNumber("32432423423432");
        vehicleRequest.setDrawBar(true);

        Car expected = new Car();
        expected.setId(1L);
        expected.setType(VehicleType.CAR);
        expected.setManufacturer("Volkswagen");
        expected.setYearOfManufacture(date);
        expected.setRentCost(12000);
        expected.setPersons(5);
        expected.setPerformance(175);
        expected.setVehicleStatus(VehicleStatusType.FREE);
        expected.setPlateNumber("LOT-749");
        expected.setVehicleIdentificationNumber("32432423423432");
        expected.setDrawBar(true);

        Car actual = (Car)controller.addNewVehicle(vehicleRequest).getBody();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createShipTest() throws NotSupportedVehicleTypeException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2009-06");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setId(2L);
        vehicleRequest.setType(VehicleType.SHIP);
        vehicleRequest.setManufacturer("Honda");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(25000);
        vehicleRequest.setPersons(4);
        vehicleRequest.setPerformance(86);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setShipId("111");
        vehicleRequest.setLength(12.3);
        vehicleRequest.setWithTrailer(true);

        Ship expected = new Ship();
        expected.setId(2L);
        expected.setType(VehicleType.SHIP);
        expected.setManufacturer("Honda");
        expected.setYearOfManufacture(date);
        expected.setRentCost(25000);
        expected.setPersons(4);
        expected.setPerformance(86);
        expected.setVehicleStatus(VehicleStatusType.FREE);
        expected.setShipId("111");
        expected.setLength(12.3);
        expected.setWithTrailer(true);

        Ship actual = (Ship)controller.addNewVehicle(vehicleRequest).getBody();
        expected.setId(actual.getId());

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NotSupportedVehicleTypeException.class)
    public void createVehicleTestExceptionalFlow() throws NotSupportedVehicleTypeException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2009-06");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setId(1L);
        vehicleRequest.setManufacturer("Volkswagen");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(12000);
        vehicleRequest.setPersons(5);
        vehicleRequest.setPerformance(175);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("LOT-749");
        vehicleRequest.setVehicleIdentificationNumber("32432423423432");
        vehicleRequest.setDrawBar(true);

        controller.addNewVehicle(vehicleRequest);
    }

    @Test
    public void getAllVehiclesTest_EmptyRepository(){
        List<Vehicle> vehicles = controller.getVehicles().getBody();
        Assert.assertEquals(0, vehicles.size());
    }

    @Test
    public void updateVehicleTest() throws VehicleNotFoundException, NotValidPlateNumberFormatException, ExistingVehiclePlateNumber {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2001-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setType(VehicleType.CAR);
        vehicleRequest.setManufacturer("Volkswagen");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(12000);
        vehicleRequest.setPersons(5);
        vehicleRequest.setPerformance(175);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("LOT-749");
        vehicleRequest.setVehicleIdentificationNumber("32432423423432");
        vehicleRequest.setDrawBar(true);

        Car actual = (Car)controller.addNewVehicle(vehicleRequest).getBody();

        UpdateVehicleRequest updateVehicleRequest = new UpdateVehicleRequest();
        updateVehicleRequest.setId(actual.getId());
        updateVehicleRequest.setType(actual.getType());
        updateVehicleRequest.setManufacturer(actual.getManufacturer());
        updateVehicleRequest.setYearOfManufacture(actual.getYearOfManufacture());
        updateVehicleRequest.setRentCost(actual.getRentCost());
        updateVehicleRequest.setPersons(actual.getPersons());
        updateVehicleRequest.setPerformance(actual.getPerformance());
        updateVehicleRequest.setVehicleStatus(actual.getVehicleStatus());
        updateVehicleRequest.setPlateNumber(actual.getPlateNumber());
        updateVehicleRequest.setVehicleIdentificationNumber(actual.getVehicleIdentificationNumber());
        updateVehicleRequest.setDrawBar(false);
        updateVehicleRequest.setCar(true);

        actual.setDrawBar(false);

        Car updated = (Car)controller.updateVehicle(updateVehicleRequest).getBody();

        Assert.assertEquals(updated, actual);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void updateVehicleTestExceptionalFlow() throws VehicleNotFoundException, NotValidPlateNumberFormatException {
        UpdateVehicleRequest vehicleRequest = new UpdateVehicleRequest();
        vehicleRequest.setId(0L);

        controller.updateVehicle(vehicleRequest);
    }

    @Test
    public void deleteVehicleTest() throws VehicleNotFoundException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2001-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setId(1L);
        vehicleRequest.setType(VehicleType.CAR);
        vehicleRequest.setManufacturer("Volkswagen");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(12000);
        vehicleRequest.setPersons(5);
        vehicleRequest.setPerformance(175);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("LOT-749");
        vehicleRequest.setVehicleIdentificationNumber("32432423423432");
        vehicleRequest.setDrawBar(true);

        Car expected = new Car();
        expected.setId(1L);
        expected.setType(VehicleType.CAR);
        expected.setManufacturer("Volkswagen");
        expected.setYearOfManufacture(date);
        expected.setRentCost(12000);
        expected.setPersons(5);
        expected.setPerformance(175);
        expected.setVehicleStatus(VehicleStatusType.FREE);
        expected.setPlateNumber("LOT-749");
        expected.setVehicleIdentificationNumber("32432423423432");
        expected.setDrawBar(true);

        Vehicle actual = controller.addNewVehicle(vehicleRequest).getBody();

        controller.removeVehicle(actual);

        int usersSize = controller.getVehicles().getBody().size();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void deleteVehicleTestExceptionalFlow() throws VehicleNotFoundException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(0L);

        controller.removeVehicle(vehicle);
    }

    @Test
    public void getVehicleByIdTest() throws ExistingVehiclePlateNumber, NotValidPlateNumberFormatException, VehicleNotFoundException {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2001-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setId(1L);
        vehicleRequest.setType(VehicleType.CAR);
        vehicleRequest.setManufacturer("Volkswagen");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(12000);
        vehicleRequest.setPersons(5);
        vehicleRequest.setPerformance(175);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("LOT-749");
        vehicleRequest.setVehicleIdentificationNumber("32432423423432");
        vehicleRequest.setDrawBar(true);

        Car expected = new Car();
        expected.setId(1L);
        expected.setType(VehicleType.CAR);
        expected.setManufacturer("Volkswagen");
        expected.setYearOfManufacture(date);
        expected.setRentCost(12000);
        expected.setPersons(5);
        expected.setPerformance(175);
        expected.setVehicleStatus(VehicleStatusType.FREE);
        expected.setPlateNumber("LOT-749");
        expected.setVehicleIdentificationNumber("32432423423432");
        expected.setDrawBar(true);

        Vehicle actual = controller.addNewVehicle(vehicleRequest).getBody();
        controller.getVehicleById(1L);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void getVehicleByIdTestExceptionalFlow() throws ExistingVehiclePlateNumber, NotValidPlateNumberFormatException, VehicleNotFoundException {
        controller.getVehicleById(0L);
    }

    @Test
    public void getVehicleByFilterOptionsTest() throws NotValidPlateNumberFormatException, ExistingVehiclePlateNumber {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2001-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SearchVehicleRequest searchVehicleRequest = new SearchVehicleRequest();
        searchVehicleRequest.setType(VehicleType.CAR);

        Car expected = new Car();
        expected.setId(1L);
        expected.setType(VehicleType.CAR);
        expected.setManufacturer("Volkswagen");
        expected.setYearOfManufacture(date);
        expected.setRentCost(12000);
        expected.setPersons(5);
        expected.setPerformance(175);
        expected.setVehicleStatus(VehicleStatusType.FREE);
        expected.setPlateNumber("LOT-749");
        expected.setVehicleIdentificationNumber("32432423423432");
        expected.setDrawBar(true);

        List<Car> carList = new ArrayList<>();
        carList.add(expected);

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setId(1L);
        vehicleRequest.setType(VehicleType.CAR);
        vehicleRequest.setManufacturer("Volkswagen");
        vehicleRequest.setYearOfManufacture(date);
        vehicleRequest.setRentCost(12000);
        vehicleRequest.setPersons(5);
        vehicleRequest.setPerformance(175);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("LOT-749");
        vehicleRequest.setVehicleIdentificationNumber("32432423423432");
        vehicleRequest.setDrawBar(true);
        controller.addNewVehicle(vehicleRequest);

        List<Vehicle> actual = controller.getVehicleByFilterOptions(searchVehicleRequest).getBody();

        Assert.assertEquals(carList, actual);
    }

    public void getVehicleByFilterOptionsExceptionalFlow(){}
}
