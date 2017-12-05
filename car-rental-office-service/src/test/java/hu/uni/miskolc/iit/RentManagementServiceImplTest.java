package hu.uni.miskolc.iit;
import hu.uni.miskolc.iit.model.*;

import hu.uni.miskolc.iit.dao.RentManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.service.RentManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by zsoltme on 2017.10.23..
 */
public class RentManagementServiceImplTest {

    private RentManagementService rentManagementService;
    private RentManagementDao rentManagementDao;
    private UserManagementDao userManagementDao;
    private VehicleManagementDao vehicleManagementDao;

    private Rent rentObject;
    private Rent anotherRentObject;
    private Rent rentAfterUpdate;

    private Car car;

    @Before
    public void setUp() throws Exception {
        rentManagementDao = mock(RentManagementDao.class);
        userManagementDao = mock(UserManagementDao.class);
        vehicleManagementDao = mock(VehicleManagementDao.class);
        rentManagementService = new RentManagementServiceImpl(rentManagementDao,userManagementDao,vehicleManagementDao);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rentObject = new Rent();
        rentObject.setId(1L);
        rentObject.setCustomerId(1L);
        rentObject.setVehicleId(1L);
        rentObject.setStartDate(startDate);
        rentObject.setEndDate(endDate);
        rentObject.setDurationExtendable(true);
        rentObject.setExtendedHours(24);
        rentObject.setKmUsed(100);
        rentObject.setDayFee(150000.0);
        rentObject.setKmFee(100000.0);
        rentObject.setOtherFee(0.0);
        rentObject.setTotalFee(250000.0);
        rentObject.setPaid(false);

        anotherRentObject = new Rent();
        anotherRentObject.setId(2L);
        anotherRentObject.setCompanyId(2L);
        anotherRentObject.setVehicleId(2L);
        anotherRentObject.setStartDate(startDate);
        anotherRentObject.setEndDate(endDate);
        anotherRentObject.setDurationExtendable(false);
        anotherRentObject.setExtendedHours(48);
        anotherRentObject.setKmUsed(200);
        anotherRentObject.setDayFee(150000.0);
        anotherRentObject.setKmFee(200000.0);
        anotherRentObject.setOtherFee(0.0);
        anotherRentObject.setTotalFee(350000.0);
        anotherRentObject.setPaid(true);
    }

    @Test
    public void addNewRent() throws Exception {
        car = new Car();
        car.setPlateNumber("JMK-776");
        car.setVehicleIdentificationNumber("ID76594");
        car.setDrawBar(false);
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Opel");
        car.setYearOfManufacture(new Date());
        car.setRentCost(212.0D);
        car.setPersons(5);
        car.setPerformance(101.0D);
        car.setVehicleStatus(VehicleStatusType.FREE);

        Customer customer = new Customer();
        customer.setId(1L);

        expect(rentManagementDao.createRent(anyObject(Rent.class))).andReturn(rentObject);
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        replay(rentManagementDao);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true).anyTimes();
        expect(userManagementDao.getUserById(anyLong())).andReturn(customer).anyTimes();
        replay(userManagementDao);

        expect(vehicleManagementDao.exists(anyObject(Vehicle.class))).andReturn(true);
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);
        replay(vehicleManagementDao);

        Rent actual = rentManagementService.addNewRent(rentObject);
        assertEquals(rentObject, actual);
    }

    @Test
    public void getRentById() throws Exception {
        expect(rentManagementDao.getRentById(1L)).andReturn(rentObject);

        replay(rentManagementDao);

        Rent actual = rentManagementService.getRentById(1L);
        assertEquals(rentObject,actual);
    }

//TODO
   public void getRentByFilterOptions() throws Exception {
        List<Rent> rents = new ArrayList<>();
        rents.add(rentObject);
        rents.add(anotherRentObject);
        expect(rentManagementDao.getRents()).andReturn(rents);

        replay(rentManagementDao);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateRequest = null;
        Date endDateRequest = null;
        try {
            startDateRequest = format.parse("2017-02-02");
            endDateRequest = format.parse("2017-03-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SearchRentRequest searchRentRequest = new SearchRentRequest(1, 0,1,startDateRequest,endDateRequest);

        List<Rent> expected = new ArrayList<>();
        expected.add(rentObject);
        List<Rent> actual = rentManagementService.getRentByFilterOptions(searchRentRequest);

        assertEquals(expected,actual);
    }

    @Test
    public void getRents() throws Exception {
        List<Rent> expected = new ArrayList<>();
        expected.add(rentObject);
        expected.add(anotherRentObject);
        expect(rentManagementDao.getRents()).andReturn(expected);

        replay(rentManagementDao);

        List<Rent> actual = rentManagementService.getRents();
        assertEquals(expected, actual);

    }

    //TODO
    public void updateRent() throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateExpected = null;
        Date endDateExpected = null;
        try {
            startDateExpected = format.parse("2017-05-01");
            endDateExpected = format.parse("2017-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rentAfterUpdate = new Rent();
        rentAfterUpdate.setId(1L);
        rentAfterUpdate.setCustomerId(300L);
        rentAfterUpdate.setCompanyId(7L);
        rentAfterUpdate.setVehicleId(2L);
        rentAfterUpdate.setStartDate(startDateExpected);
        rentAfterUpdate.setEndDate(endDateExpected);

        expect(rentManagementDao.getRentById(anyLong())).andReturn(rentObject).anyTimes();
        expect(rentManagementDao.createRent(anyObject(Rent.class))).andReturn(rentObject);
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(true);
        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(vehicleManagementDao.exists(anyObject(Vehicle.class))).andReturn(true);
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);

        replay(rentManagementDao);
        replay(userManagementDao);
        replay(vehicleManagementDao);

        Rent actual = rentManagementService.updateRent(rentAfterUpdate);

        assertEquals(rentAfterUpdate, actual);
    }

    //TODO
    public void removeRent() throws Exception {
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(true);
        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(vehicleManagementDao.exists(anyObject(Vehicle.class))).andReturn(true);
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);

        rentManagementDao.deleteRent(rentObject);
        expectLastCall();

        replay(rentManagementDao);
        replay(userManagementDao);
        replay(vehicleManagementDao);

        rentManagementService.removeRent(rentObject);
    }

    @Test(expected = NegativeValueException.class)
    public void negativeNumberException() throws Exception {
        rentObject.setExtendedHours(-1);
        rentObject.setKmUsed(-1);
        rentObject.setKmFee(-1);
        rentObject.setDayFee(-1);
        rentObject.setOtherFee(-1);
        rentObject.setTotalFee(-1);

        rentManagementService.addNewRent(rentObject);

    }

    @Test(expected = WrongRentDateException.class)
    public void wrongDateException() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-03-01");
            endDate = format.parse("2017-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rentObject.setStartDate(startDate);
        rentObject.setEndDate(endDate);

        rentManagementService.addNewRent(rentObject);
    }

    @Test(expected = RentIdAlreadyExistsException.class)
    public void rentIdAlreadyExistsException() throws Exception {
        anotherRentObject.setId(1L);

        expect(rentManagementDao.exists(anotherRentObject)).andReturn(true);

        replay(rentManagementDao);

        rentManagementService.addNewRent(anotherRentObject);
    }

    @Test(expected = RentWrongTotalFeeException.class)
    public void wrongTotalFeeException() throws Exception {
        anotherRentObject.setTotalFee(1);

        rentManagementService.addNewRent(anotherRentObject);
    }

    //TODO
    public void userNotFoundException() throws Exception {
        anotherRentObject.setCustomerId(0L);
        anotherRentObject.setCompanyId(0L);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(false).anyTimes();

        replay(userManagementDao);

        try {
            rentManagementService.addNewRent(anotherRentObject);
        } catch(UserNotFoundException actual) {
            assertEquals("User Ids wrong: Customer - " + anotherRentObject.getCustomerId() + ",Company - " + anotherRentObject.getCompanyId() + ".",actual.getMessage());
        }
    }

    //TODO
    public void userCustomerNotFoundException() throws Exception {
        anotherRentObject.setCustomerId(15L);
        anotherRentObject.setCompanyId(0L);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(false).anyTimes();

        replay(userManagementDao);

        try {
            rentManagementService.addNewRent(anotherRentObject);
        } catch(UserNotFoundException actual) {
            assertEquals("Customer with Id: " + anotherRentObject.getCustomerId() + " does not exist.",actual.getMessage());
        }
    }

    //TODO
    public void userCompanyNotFoundException() throws Exception {
        anotherRentObject.setCustomerId(0L);
        anotherRentObject.setCompanyId(30L);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(false).anyTimes();

        replay(userManagementDao);

        try {
            rentManagementService.addNewRent(anotherRentObject);
        } catch(UserNotFoundException actual) {
            assertEquals("Company with Id: " + anotherRentObject.getCompanyId() + " does not exist.",actual.getMessage());
        }
    }

//TODO
   public void vehicleNotFoundException() throws Exception {
        anotherRentObject.setVehicleId(50L);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(vehicleManagementDao.getVehicleById(anyLong())).andThrow(new VehicleNotFoundException());
        replay(userManagementDao);

        rentManagementService.addNewRent(anotherRentObject);
    }

    @Test(expected = RentNotFoundException.class)
    public void rentNotFoundException() throws Exception {
        rentManagementService.updateRent(anotherRentObject);
    }
}