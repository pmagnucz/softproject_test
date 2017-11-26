package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.VehicleController;
import hu.uni.miskolc.iit.exception.ExistingVehiclePlateNumber;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * Created by rozgonyi on 2017. 11. 25..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class VehicleManagementIT {

    private VehicleController controller;

    @Autowired
    private VehicleRepository repository;

    @Before
    public void setUp(){
        VehicleManagementService service = new VehicleManagementServiceImpl(repository);
        controller = new VehicleController(service);
    }

    @After
    public void tearDown() throws Exception{
        repository.deleteAll();
    }

    @Test
    public void createCarTest() throws NotSupportedVehicleTypeException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-11", dateTimeFormatter);

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

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createShipTest() throws NotSupportedVehicleTypeException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2009-06", dateTimeFormatter);

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

        Ship actual = (Ship) controller.addNewVehicle(vehicleRequest).getBody();
        //TODO: megnézni, mert ez így elég csúnya
        expected.setId(actual.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NotSupportedVehicleTypeException.class)
    public void createVehicleTestExceptionalFlow() throws NotSupportedVehicleTypeException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2009-06", dateTimeFormatter);

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
    public void getAllVehiclesTest(){

    }

    @Test
    public void updateVehicleTest() throws VehicleNotFoundException, NotValidPlateNumberFormatException, ExistingVehiclePlateNumber {

    }

    @Test(expected = VehicleNotFoundException.class)
    public void updateVehicleTestExceptionalFlow() throws VehicleNotFoundException, NotValidPlateNumberFormatException {
        UpdateVehicleRequest vehicleRequest = new UpdateVehicleRequest();
        vehicleRequest.setId(0L);

        controller.updateVehicle(vehicleRequest);
    }

    @Test
    public void deleteVehicleTest() throws VehicleNotFoundException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-11", dateTimeFormatter);

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
        Assert.assertEquals(0, usersSize);
    }

    @Test
    public void deleteVehicleTestExceptionalFlow() throws VehicleNotFoundException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-11", dateTimeFormatter);

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
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);

        controller.removeVehicle(vehicle);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getVehicleByIdTest() throws ExistingVehiclePlateNumber, NotValidPlateNumberFormatException, VehicleNotFoundException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-11", dateTimeFormatter);

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

    @Test
    public void getVehicleByIdTestExceptionalFlow() throws ExistingVehiclePlateNumber, NotValidPlateNumberFormatException, VehicleNotFoundException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-11", dateTimeFormatter);

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

    @Test
    public void getVehicleByFilterOptionsTest() throws NotValidPlateNumberFormatException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2001-11", dateTimeFormatter);

        SearchVehicleRequest searchVehicleRequest = new SearchVehicleRequest();
        controller.getVehicleByFilterOptions(searchVehicleRequest);
    }

    public void getVehicleByFilterOptionsExceptionalFlow(){}

}
