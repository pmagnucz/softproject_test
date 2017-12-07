package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.RentController;
import hu.uni.miskolc.iit.dao.RentManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.model.Vehicle;
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

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by botty on 2017. 11. 28..
 */

public class RentControllerTest {
    private RentController rentController;
    private RentManagementService rentService;
    private RentManagementDao rentManagementDao;
    private UserManagementDao userManagementDao;
    private VehicleManagementDao vehicleManagementDao;

    private Rent rent;
    private Rent rent2;

    @Before
    public void setUp() {
        rentManagementDao = mock(RentManagementDao.class);
        userManagementDao = mock(UserManagementDao.class);
        vehicleManagementDao = mock(VehicleManagementDao.class);

        rentService = new RentManagementServiceImpl(rentManagementDao,userManagementDao,vehicleManagementDao);
        rentController = new RentController(rentService);

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

        rent2 = new Rent();

        rent2.setId(10L);
        rent2.setCustomerId(4L);
        rent2.setCompanyId(5L);
        rent2.setVehicleId(6L);
        rent2.setStartDate(startDate);
        rent2.setEndDate(endDate);
        rent2.setDurationExtendable(false);
        rent2.setExtendedHours(48);
        rent2.setKmUsed(200);
        rent2.setDayFee(150000.0);
        rent2.setKmFee(200000.0);
        rent2.setOtherFee(0.0);
        rent2.setTotalFee(350000.0);
        rent2.setPaid(true);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createRent() throws NegativeValueException, RentIdAlreadyExistsException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException, RentWrongTotalFeeException {
        Rent expected = rent;
        expect(rentManagementDao.createRent(rent)).andReturn(expected);

        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(vehicleManagementDao.exists(anyObject(Vehicle.class))).andReturn(true);
        expect(userManagementDao.getUserById(anyLong())).andReturn(new User()).anyTimes();
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(new Vehicle()).anyTimes();

        replay(rentManagementDao);
        replay(userManagementDao);
        replay(vehicleManagementDao);

        Rent actual = rentController.createRent(rent).getBody();

        assertEquals(expected,actual);

    }

    @Test
    public void updateRent() throws NegativeValueException, RentWrongTotalFeeException, RentNotFoundException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException {
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
        rentUpdate.setId(1L);
        rentUpdate.setCustomerId(4L);
        rentUpdate.setCompanyId(5L);
        rentUpdate.setVehicleId(6L);
        rentUpdate.setStartDate(startDate);
        rentUpdate.setEndDate(endDate);
        rentUpdate.setDurationExtendable(false);
        rentUpdate.setExtendedHours(48);
        rentUpdate.setKmUsed(200);
        rentUpdate.setDayFee(150000.0);
        rentUpdate.setKmFee(200000.0);
        rentUpdate.setOtherFee(0.0);
        rentUpdate.setTotalFee(350000.0);
        rentUpdate.setPaid(true);


        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(true);
        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(vehicleManagementDao.exists(anyObject(Vehicle.class))).andReturn(true);
        expect(rentManagementDao.getRentById(anyLong())).andReturn(rent).anyTimes();
        expect(rentManagementDao.createRent(rentUpdate)).andReturn(rentUpdate);
        expect(userManagementDao.getUserById(anyLong())).andReturn(new User()).anyTimes();
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(new Vehicle()).anyTimes();

        replay(rentManagementDao);
        replay(userManagementDao);
        replay(vehicleManagementDao);

        Rent actual = rentController.updateRent(rentUpdate).getBody();

        assertEquals(rentUpdate, actual);

    }

    @Test
    public void deleteRent() throws NegativeValueException, RentWrongTotalFeeException, RentNotFoundException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException {
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(true);
        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        expect(vehicleManagementDao.exists(anyObject(Vehicle.class))).andReturn(true);
        expect(userManagementDao.getUserById(anyLong())).andReturn(new User()).anyTimes();
        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(new Vehicle()).anyTimes();

        rentManagementDao.deleteRent(rent);
        expectLastCall();

        replay(rentManagementDao);
        replay(userManagementDao);
        replay(vehicleManagementDao);

        rentController.deleteRent(rent);
    }

    @Test
    public void getRentById() {
        expect(rentManagementDao.getRentById(anyLong())).andReturn(rent);

        replay(rentManagementDao);

        Rent actual = rentController.getRentById(Math.toIntExact(rent.getId())).getBody();
        assertEquals(rent,actual);
    }

    @Test
    public void getAllRent() {
        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        rents.add(rent2);

        expect(rentManagementDao.getRents()).andReturn(rents);

        replay(rentManagementDao);

        List<Rent> actual = rentController.getAllRent().getBody();
        assertEquals(rents, actual);

    }

    @Test
    public void getRentCount() {
        List<Rent> expectedList = new ArrayList<>();
        expectedList.add(new Rent());
        expectedList.add(new Rent());
        expect(rentManagementDao.getRents()).andReturn(expectedList);
        replay(rentManagementDao);

        int count = rentController.getRentCount().getBody();
        assertEquals(2, count);
    }

    @Test
    public void getRentsByFilterOptions() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateRequest = null;
        Date endDateRequest = null;
        try {
            startDateRequest = format.parse("2017-02-02");
            endDateRequest = format.parse("2017-03-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SearchRentRequest searchRentRequest = new SearchRentRequest(1,100,150,startDateRequest,endDateRequest);

        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        rents.add(rent2);

        expect(rentManagementDao.getRents()).andReturn(rents);

        replay(rentManagementDao);

        List<Rent> expected = new ArrayList<>();
        expected.add(rent);
        List<Rent> actual = rentController.getRentByFilterOptions(searchRentRequest).getBody();

        assertEquals(expected,actual);

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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-03-01");
            endDate = format.parse("2017-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setStartDate(startDate);
        rent.setEndDate(endDate);

        rentController.createRent(rent);
    }

    @Test(expected = RentIdAlreadyExistsException.class)
    public void rentIdAlreadyExistsException() throws Exception {
        rent.setId(1L);

        expect(rentManagementDao.exists(rent)).andReturn(true);

        replay(rentManagementDao);

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
        rent.setCompanyId(0L);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(false).anyTimes();
        expect(userManagementDao.getUserById(anyLong())).andReturn(null).anyTimes();
        replay(userManagementDao);

        rentController.createRent(rent);
    }

    @Test(expected = UserNotFoundException.class)
    public void userCompanyNotFoundException() throws Exception {
        rent.setCustomerId(0L);
        rent.setCompanyId(30L);

        expect(userManagementDao.exists(anyObject(User.class))).andReturn(false).anyTimes();
        expect(userManagementDao.getUserById(anyLong())).andReturn(null).anyTimes();
        replay(userManagementDao);

        rentController.createRent(rent);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleNotFoundException() throws Exception {
        rent.setVehicleId(50L);
        expect(userManagementDao.getUserById(anyLong())).andReturn(new User()).anyTimes();
        expect(rentManagementDao.exists(anyObject(Rent.class))).andReturn(false);
        expect(userManagementDao.exists(anyObject(User.class))).andReturn(true);
        replay(userManagementDao);

        rentController.createRent(rent);
    }
}
