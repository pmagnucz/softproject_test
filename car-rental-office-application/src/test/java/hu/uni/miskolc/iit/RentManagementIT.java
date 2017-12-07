package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.RentController;
import hu.uni.miskolc.iit.dao.*;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.*;

import hu.uni.miskolc.iit.service.RentManagementService;
import hu.uni.miskolc.iit.service.UserManagementService;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.*;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by zsoltme on 2017.11.23..
 */

public class RentManagementIT {
    private RentController rentController;

    private RentManagementDao rentManagementDao;
    private UserManagementDao userManagementDao;
    private VehicleManagementDao vehicleManagementDao;

    private Rent rent;
    private Rent rent2;

    @Before
    public void setUp() throws UserTypeDoesNotExistException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        rentManagementDao = new RentManagementDaoImpl(new File("src/test/resources/rentDatabase.json"));
        userManagementDao = new UserManagementDaoImpl(new File("src/test/resources/userDatabase.json"));
        vehicleManagementDao = new VehicleManagementDaoImpl(new File("src/test/resources/vehicleDatabase.json"));

        VehicleManagementService vehicleService = new VehicleManagementServiceImpl(vehicleManagementDao);
        RentManagementService rentService = new RentManagementServiceImpl(rentManagementDao, userManagementDao, vehicleManagementDao);

        rentController = new RentController(rentService);

        // Create test data
        DateFormat formatRent = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatManufacture = new SimpleDateFormat("yyyy-MM");
        Date startDate = null;
        Date endDate = null;
        Date manufactureDate = null;
        try {
            startDate = formatRent.parse("2017-02-01");
            endDate = formatRent.parse("2017-03-01");
            manufactureDate = formatManufacture.parse("1960-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent = new Rent();
        rent.setId(1L);
        rent.setCustomerId(1L);
        rent.setVehicleId(1L);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setDayFee(150000.0);
        rent.setKmFee(100000.0);
        rent.setOtherFee(0.0);
        rent.setTotalFee(250000.0);
        rent.setPaid(false);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUserId("1");
        customer.setUserName("rentControllerTestUser");
        customer.setAddress("test");
        customer.setPhoneNumber("test");
        customer.setYearOfBirth(1990);
        customer.setDrivingLicenceNumber("test");
        userManagementDao.addUser(customer);

        Car car = new Car();
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("test");
        car.setYearOfManufacture(manufactureDate);
        car.setRentCost(15000);
        car.setPersons(5);
        car.setPerformance(1500.24);
        car.setVehicleStatus(VehicleStatusType.FREE);
        car.setPlateNumber("AAA-111");
        car.setVehicleIdentificationNumber("test");
        car.setDrawBar(true);
        vehicleService.addNewVehicle(car);
    }

    @After
    public void tearDown() throws Exception {
        rentManagementDao.clear();
        userManagementDao.clear();
        vehicleManagementDao.clear();
    }


    @Test
    public void createRent() throws Exception {
        Rent expected = rent;
        Rent actual = rentController.createRent(rent).getBody();
        expected.setId(actual.getId());

        Assert.assertEquals(rent, actual);
    }

    @Test
    public void getRentById() throws Exception {
        Rent expected = rentController.createRent(rent).getBody();
        Rent actual = rentController.getRentById(Math.toIntExact(expected.getId())).getBody();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRentByFilterOptions() throws Exception {

    }

    @Test
    public void getAllRent() throws Exception {
        rent2 = new Rent();
        BeanUtils.copyProperties(rent, rent2);
        rent2.setId(2L);

        Rent rentTest1 = rentController.createRent(rent).getBody();
        Rent rentTest2 = rentController.createRent(rent2).getBody();

        List<Rent> expected = new ArrayList<>();
        expected.add(rentTest1);
        expected.add(rentTest2);

        List<Rent> actual = rentController.getAllRent().getBody();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateRent() throws Exception {

        DateFormat formatRent = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatRent.parse("2017-05-01");
            endDate = formatRent.parse("2017-06-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent rentUpdate = new Rent();

        rentUpdate.setId(2L);
        rentUpdate.setCustomerId(1L);
        rentUpdate.setVehicleId(1L);
        rentUpdate.setStartDate(startDate);
        rentUpdate.setEndDate(endDate);
        rentUpdate.setDurationExtendable(false);
        rentUpdate.setExtendedHours(0);
        rentUpdate.setKmUsed(200);
        rentUpdate.setDayFee(250000.0);
        rentUpdate.setKmFee(200000.0);
        rentUpdate.setOtherFee(0.0);
        rentUpdate.setTotalFee(450000.0);
        rentUpdate.setPaid(true);

        Rent rentTest = rentController.createRent(rent).getBody();
        rentUpdate.setId(rentTest.getId());
        rentController.updateRent(rentUpdate);
        Rent rentUpdated = rentController.getRentById(Math.toIntExact(rentUpdate.getId())).getBody();
        Assert.assertEquals(rentUpdate, rentUpdated);
    }

    @Test
    public void deleteRent() throws Exception {
    }

    @Test
    public void getRentCount() throws Exception {


        rentController.createRent(rent);
        rent.setId(10L);
        rentController.createRent(rent);

        int count = rentController.getRentCount().getBody();
        Assert.assertEquals(2, count);

    }

    @Test(expected = NegativeValueException.class)
    public void negativeNumberException() throws Exception {
        rent.setExtendedHours(-1);
        rent.setKmUsed(-1);
        rent.setKmFee(-1);
        rent.setDayFee(-1);
        rent.setOtherFee(-1);
        rent.setTotalFee(-1);

        rentController.createRent(rent);

    }

    @Test(expected = WrongRentDateException.class)
    public void wrongDateException() throws Exception {
        DateFormat formatRent = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatRent.parse("2017-03-01");
            endDate = formatRent.parse("2017-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setStartDate(startDate);
        rent.setEndDate(endDate);

        rentController.createRent(rent);
    }

    @Test(expected = RentIdAlreadyExistsException.class)
    public void rentIdAlreadyExistsException() throws Exception {
        Rent rentTest = rentController.createRent(rent).getBody();
        rent.setId(rentTest.getId());
        rentController.createRent(rent);
    }

    @Test(expected = RentWrongTotalFeeException.class)
    public void wrongTotalFeeException() throws Exception {
        rent.setTotalFee(1);

        rentController.createRent(rent);
    }

    @Test(expected = UserNotFoundException.class)
    public void userCustomerNotFoundException() throws Exception {
        rent.setCustomerId(15L);
        rentController.createRent(rent);
    }

    @Test(expected = UserNotFoundException.class)
    public void userCompanyNotFoundException() throws Exception {
        rent.setCompanyId(30L);
        rentController.createRent(rent);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleNotFoundException() throws Exception {
        rent.setVehicleId(50L);
        rentController.createRent(rent);
    }

}
