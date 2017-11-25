package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.RentManagementService;
import hu.uni.miskolc.iit.service.UserManagementService;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by zsoltme on 2017.11.18..
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RentServiceImplCT {
//TODO: újra kell írni az egészet. Repository lehet mock, de a controller és a service NEM
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

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate dateOfManufacture = LocalDate.parse("2017-08", dateTimeFormatter);

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
