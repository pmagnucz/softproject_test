package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.RentRepository;
import hu.uni.miskolc.iit.service.RentManagementService;
import hu.uni.miskolc.iit.service.UserManagementService;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by zsoltme on 2017.11.18..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentServiceImplCT {

    Rent rent;

    Car car;

    CreateUserRequest userRequest;

    @Autowired
    RentManagementService rentManagementService;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    VehicleManagementService vehicleManagementService;

    @Before
    public void setUp() throws UserTypeDoesNotExistException, ExistingVehiclePlateNumber, NotValidPlateNumberFormatException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent = new Rent(null,1L,0L,1L,startDate,endDate,
                false,24,100,
                100000.0,50000.0,0.0,150000.0,false);

        userRequest = new CreateUserRequest();

        userRequest.setUserId("1");
        userRequest.setUserName("rentServiceTestUser");
        userRequest.setAddress("test");
        userRequest.setPhoneNumber("test");
        userRequest.setYearOfBirth(1990);
        userRequest.setDrivingLicenceNumber("test");

        car = new Car();

        DateFormat formatYearOfManufacture = new SimpleDateFormat("yyyy-MM");
        Date dateOfManufacture = null;
        try {
            dateOfManufacture = formatYearOfManufacture.parse("2017-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("test");
        car.setYearOfManufacture(dateOfManufacture);
        car.setRentCost(15000);
        car.setPersons(5);
        car.setPerformance(1500.24);
        car.setVehicleStatus(VehicleStatusType.FREE);
        car.setPlateNumber("AAA-111");
        car.setVehicleIdentificationNumber("test");
        car.setDrawBar(true);

        userManagementService.createNewUser(userRequest);
        vehicleManagementService.addNewVehicle(car);
    }

    @Test
    public void addNewRent() throws NegativeValueException, RentWrongTotalFeeException, RentIdAlreadyExistsException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException, RentNotFoundException {
        Rent actual = rentManagementService.addNewRent(rent);

        rent.setId(actual.getId());

        assertEquals(rent,actual);
    }

    @Test
    public void getRentById() throws NegativeValueException, RentWrongTotalFeeException, RentIdAlreadyExistsException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException, RentNotFoundException {
        Rent expected = rentManagementService.addNewRent(rent);

        assertEquals(expected,rentManagementService.getRentById(Math.toIntExact(expected.getId())));
    }
}
