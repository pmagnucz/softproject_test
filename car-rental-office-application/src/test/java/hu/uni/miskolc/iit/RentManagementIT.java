package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.RentController;
import hu.uni.miskolc.iit.exception.ExistingVehiclePlateNumber;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by zsoltme on 2017.11.23..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class RentManagementIT {

    private RentController rentController;

    private Rent rent;
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

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
}
