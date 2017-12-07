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
import java.time.LocalDate;
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
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        rentManagementDao = mock(RentManagementDao.class);
        userManagementDao = mock(UserManagementDao.class);
        vehicleManagementDao = mock(VehicleManagementDao.class);
        rentManagementService = new RentManagementServiceImpl(rentManagementDao,userManagementDao,vehicleManagementDao);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        Date anotherStartDate = null;
        Date anotherEndDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
            anotherStartDate = format.parse("2017-05-01");
            anotherEndDate = format.parse("2017-06-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rentObject = new Rent();
        rentObject.setId(1L);
        rentObject.setCustomerId(1L);
        rentObject.setCompanyId(0L);
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
        anotherRentObject.setCustomerId(0L);
        anotherRentObject.setCompanyId(2L);
        anotherRentObject.setVehicleId(2L);
        anotherRentObject.setStartDate(anotherStartDate);
        anotherRentObject.setEndDate(anotherEndDate);
        anotherRentObject.setDurationExtendable(false);
        anotherRentObject.setExtendedHours(48);
        anotherRentObject.setKmUsed(200);
        anotherRentObject.setDayFee(150000.0);
        anotherRentObject.setKmFee(200000.0);
        anotherRentObject.setOtherFee(0.0);
        anotherRentObject.setTotalFee(350000.0);
        anotherRentObject.setPaid(true);

        customer = new Customer();

        customer.setId(1L);
        customer.setUserName("testUser");
        customer.setAddress("test");
        customer.setPhoneNumber("test");
        customer.setUserId("1");
        customer.setYearOfBirth(1985);
        customer.setDrivingLicenceNumber("AAA-111");

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
    }

    @Test
    public void addNewRent() throws Exception {
        expect(rentManagementDao.createRent(anyObject(Rent.class))).andReturn(rentObject);
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        replay(rentManagementDao);

        expect(userManagementDao.getUserById(anyLong())).andReturn(customer).anyTimes();
        replay(userManagementDao);

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

   @Test
   public void getRentByFilterOptions() throws Exception {
       DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       Date startDate = null;
       Date endDate = null;
       try {
           startDate = format.parse("2017-03-02");
           endDate = format.parse("2017-04-02");
       } catch (ParseException e) {
           e.printStackTrace();
       }

        List<Rent> rents = new ArrayList<>();
        rents.add(rentObject);
        rents.add(anotherRentObject);
        expect(rentManagementDao.getRents()).andReturn(rents);

        replay(rentManagementDao);

        SearchRentRequest searchRentRequest = new SearchRentRequest(1, 0,1,startDate,endDate);

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

    @Test
    public void updateRent() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-05-01");
            endDate = format.parse("2017-07-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rentAfterUpdate = new Rent();
        rentAfterUpdate.setId(1L);
        rentAfterUpdate.setCustomerId(300L);
        rentAfterUpdate.setCompanyId(7L);
        rentAfterUpdate.setVehicleId(2L);
        rentAfterUpdate.setStartDate(startDate);
        rentAfterUpdate.setEndDate(endDate);

        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(true);
        expect(rentManagementDao.createRent(anyObject(Rent.class))).andReturn(rentAfterUpdate);

        expect(userManagementDao.getUserById(anyLong())).andReturn(customer).anyTimes();

        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);

        replay(rentManagementDao);
        replay(userManagementDao);
        replay(vehicleManagementDao);

        Rent actual = rentManagementService.updateRent(rentAfterUpdate);

        assertEquals(rentAfterUpdate, actual);
    }

    @Test
    public void removeRent() throws Exception {
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(true);

        expect(userManagementDao.getUserById(anyLong())).andReturn(customer).anyTimes();

        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car).anyTimes();

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
        rentObject.setId(1L);

        expect(rentManagementDao.exists(rentObject)).andReturn(true);

        replay(rentManagementDao);

        rentManagementService.addNewRent(rentObject);
    }

    @Test(expected = RentWrongTotalFeeException.class)
    public void wrongTotalFeeException() throws Exception {
        rentObject.setTotalFee(1);

        rentManagementService.addNewRent(rentObject);
    }

    @Test(expected = UserNotFoundException.class)
    public void userCustomerNotFoundException() throws Exception {
        rentObject.setCustomerId(15L);
        rentObject.setCompanyId(0L);

        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        replay(rentManagementDao);

        expect(userManagementDao.getUserById(anyLong())).andReturn(null).anyTimes();
        replay(userManagementDao);

        rentManagementService.addNewRent(rentObject);
    }

    @Test(expected = UserNotFoundException.class)
    public void userCompanyNotFoundException() throws Exception {
        rentObject.setCustomerId(0L);
        rentObject.setCompanyId(30L);

        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        replay(rentManagementDao);

        expect(userManagementDao.getUserById(anyLong())).andReturn(null).anyTimes();
        replay(userManagementDao);


        rentManagementService.addNewRent(anotherRentObject);
    }

    @Test(expected = VehicleNotFoundException.class)
   public void vehicleNotFoundException() throws Exception {
       rentObject.setVehicleId(50L);

        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        replay(rentManagementDao);

        expect(userManagementDao.getUserById(anyLong())).andReturn(customer).anyTimes();
        replay(userManagementDao);

        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(null);
        replay(vehicleManagementDao);

        rentManagementService.addNewRent(rentObject);
    }

    @Test(expected = RentNotFoundException.class)
    public void rentNotFoundException() throws Exception {
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        replay(rentManagementDao);

        rentManagementService.updateRent(rentObject);
    }
}