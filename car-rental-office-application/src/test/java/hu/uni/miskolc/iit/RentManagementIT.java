package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.RentController;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.RentRepository;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.RentManagementService;
import hu.uni.miskolc.iit.service.UserManagementService;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by zsoltme on 2017.11.23..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class RentManagementIT {

    private RentController rentController;

    private Rent rent;
    private Rent rent2;
    private boolean userAndVehicleAdded = false;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Before
    public void setUp() throws UserTypeDoesNotExistException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        RentManagementService rentService = new RentManagementServiceImpl(rentRepository,userRepository,vehicleRepository);
        rentController = new RentController(rentService);

        UserManagementService userService = new UserManagementServiceImpl(userRepository);
        VehicleManagementService vehicleService = new VehicleManagementServiceImpl(vehicleRepository);

        rent = new Rent();

        rent.setId(1L);
        rent.setCustomerId(1L);
        rent.setCompanyId(0L);
        rent.setVehicleId(1L);
        rent.setStartDate(LocalDate.parse("2017-02-01"));
        rent.setEndDate(LocalDate.parse("2017-03-01"));
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setDayFee(150000.0);
        rent.setKmFee(100000.0);
        rent.setOtherFee(0.0);
        rent.setTotalFee(250000.0);
        rent.setPaid(false);

        if(userAndVehicleAdded == false) {
            CreateUserRequest userRequest = new CreateUserRequest();

            userRequest.setUserId("1");
            userRequest.setUserName("rentControllerTestUser");
            userRequest.setAddress("test");
            userRequest.setPhoneNumber("test");
            userRequest.setYearOfBirth(1990);
            userRequest.setDrivingLicenceNumber("test");

            Car car = new Car();
            Date yearOfManufactureDate = new Date(1950-02);

            car.setId(1L);
            car.setType(VehicleType.CAR);
            car.setManufacturer("test");
            car.setYearOfManufacture(yearOfManufactureDate);
            car.setRentCost(15000);
            car.setPersons(5);
            car.setPerformance(1500.24);
            car.setVehicleStatus(VehicleStatusType.FREE);
            car.setPlateNumber("AAA-111");
            car.setVehicleIdentificationNumber("test");
            car.setDrawBar(true);

            userService.createNewUser(userRequest);
            vehicleService.addNewVehicle(car);

            userAndVehicleAdded = true;
        }
    }

    @After
    public void tearDown() throws Exception {
        rentRepository.deleteAll();
    }


    @Test
    public void createRent() throws Exception {
        Rent expected = rent;
        Rent actual = rentController.createRent(rent).getBody();
        expected.setId(actual.getId());

        Assert.assertEquals(rent,actual);
    }

    @Test
    public void getRentById() throws Exception {
            Rent expected = rentController.createRent(rent).getBody();
            Rent actual = rentController.getRentById(Math.toIntExact(expected.getId())).getBody();

            Assert.assertEquals(expected,actual);
    }

    @Test
    public void getRentByFilterOptions() throws Exception {
            rent2 = new Rent();

            rent2.setId(2L);
            rent2.setCustomerId(1L);
            rent2.setCompanyId(0L);
            rent2.setVehicleId(1L);
            rent2.setStartDate(LocalDate.parse("2017-05-01"));
            rent2.setEndDate(LocalDate.parse("2017-06-01"));
            rent2.setDurationExtendable(true);
            rent2.setExtendedHours(24);
            rent2.setKmUsed(100);
            rent2.setDayFee(150000.0);
            rent2.setKmFee(100000.0);
            rent2.setOtherFee(0.0);
            rent2.setTotalFee(250000.0);
            rent2.setPaid(false);

            rent = rentController.createRent(rent).getBody();
            rent2 = rentController.createRent(rent2).getBody();

            SearchRentRequest searchRentRequest = new SearchRentRequest
                    (10,10,10,rent.getStartDate(),rent.getEndDate());

            List<Rent> expectedList = new ArrayList();

            expectedList.add(rent);

            List<Rent> actualList = rentController.getRentByFilterOptions(searchRentRequest).getBody();

            Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void getAllRent() throws Exception {
    }

    @Test
    public void updateRent() throws Exception {
    }

    @Test
    public void deleteRent() throws Exception {
    }

    @Test
    public void getRentCount() throws Exception {
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

        rent.setStartDate(LocalDate.parse("2017-05-01"));
        rent.setEndDate(LocalDate.parse("2017-04-01"));

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

    @Test
    public void userNotFoundException() throws Exception {
        rent.setCustomerId(0L);
        rent.setCompanyId(0L);

        try {
            rentController.createRent(rent);
        } catch(UserNotFoundException actual) {
            Assert.assertEquals("User Ids wrong: Customer - " + rent.getCustomerId() + ",Company - " + rent.getCompanyId() + ".",actual.getMessage());
        }
    }

    @Test
    public void userCustomerNotFoundException() throws Exception {
        rent.setCustomerId(15L);
        rent.setCompanyId(0L);

        try {
            rentController.createRent(rent);
        } catch(UserNotFoundException actual) {
            Assert.assertEquals("Customer with Id: " + rent.getCustomerId() + " does not exist.",actual.getMessage());
        }
    }

    @Test
    public void userCompanyNotFoundException() throws Exception {
        rent.setCustomerId(0L);
        rent.setCompanyId(30L);
        try {
            rentController.createRent(rent);
        } catch(UserNotFoundException actual) {
            Assert.assertEquals("Company with Id: " + rent.getCompanyId() + " does not exist.",actual.getMessage());
        }
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleNotFoundException() throws Exception {
        rent.setVehicleId(50L);

        rentController.createRent(rent);
    }

}
