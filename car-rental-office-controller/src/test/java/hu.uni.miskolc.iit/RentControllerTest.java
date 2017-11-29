package hu.uni.miskolc.iit;
import hu.uni.miskolc.iit.controller.RentController;
import hu.uni.miskolc.iit.entity.RentEntity;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.mapper.RentMapper;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.repositories.RentRepository;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
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
import static org.junit.Assert.assertEquals;

/**
 * Created by botty on 2017. 11. 28..
 */

public class RentControllerTest {
    private RentController rentController;
    private RentManagementService rentService;
    private RentRepository rentRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;

    Rent rent;
    Rent rent2;

    @Before
    public void setUp() {
        rentRepository = mock(RentRepository.class);
        userRepository = mock(UserRepository.class);
        vehicleRepository = mock(VehicleRepository.class);

        rentService = new RentManagementServiceImpl(rentRepository,userRepository,vehicleRepository);
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
        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
        expect(rentRepository.save(mockEntity)).andReturn(mockEntity);

        expect(rentRepository.exists(anyLong())).andReturn(false);
        expect(userRepository.exists(anyLong())).andReturn(true);
        expect(vehicleRepository.exists(anyLong())).andReturn(true);

        replay(rentRepository);
        replay(userRepository);
        replay(vehicleRepository);

        Rent actual = rentController.createRent(rent).getBody();

        assertEquals(expected,actual);

    }

    @Test
    public void updateRent() {
        rent2.setId(rent.getId());

        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);

        expect(rentRepository.exists(anyLong())).andReturn(true);
        expect(userRepository.exists(anyLong())).andReturn(true);
        expect(vehicleRepository.exists(anyLong())).andReturn(true);
        expect(rentRepository.findOne(anyLong())).andReturn(mockEntity).anyTimes();
        expect(rentRepository.save(anyObject(RentEntity.class))).andReturn(mockEntity);

        replay(rentRepository);
        replay(userRepository);
        replay(vehicleRepository);

        rentController.updateRent(rent2);

        Rent actual = RentMapper.mapEntityToModel(mockEntity);

        assertEquals(rent2,actual);

    }

    @Test
    public void deleteRent() throws NegativeValueException, RentWrongTotalFeeException, RentNotFoundException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException {
        expect(rentRepository.exists(anyLong())).andReturn(true);
        expect(userRepository.exists(anyLong())).andReturn(true);
        expect(vehicleRepository.exists(anyLong())).andReturn(true);

        rentRepository.delete(rent.getId());
        expectLastCall();

        replay(rentRepository);
        replay(userRepository);
        replay(vehicleRepository);

        rentController.deleteRent(rent);
    }

    @Test
    public void getRentById() {
        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);

        expect(rentRepository.findOne(anyLong())).andReturn(mockEntity);

        replay(rentRepository);

        Rent actual = rentController.getRentById(Math.toIntExact(rent.getId())).getBody();
        assertEquals(rent,actual);
    }

    @Test
    public void getAllRent() {
        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        rents.add(rent2);

        List<RentEntity> expectedEntities = RentMapper.mapRentListToRentEntityList(rents);

        expect(rentRepository.findAll()).andReturn(expectedEntities);

        replay(rentRepository);

        List<Rent> actual = rentController.getAllRent().getBody();
        assertEquals(rents, actual);

    }

    @Test
    public void getRentCount() {
        expect(rentRepository.count()).andReturn(2L);

        replay(rentRepository);

        int count = rentController.getRentCount().getBody();
        assertEquals(2,count);
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

        List<RentEntity> expectedEntities = RentMapper.mapRentListToRentEntityList(rents);

        expect(rentRepository.findAll()).andReturn(expectedEntities);

        replay(rentRepository);

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

        expect(rentRepository.exists(1L)).andReturn(true);

        replay(rentRepository);

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

        expect(userRepository.exists(anyLong())).andReturn(false).anyTimes();

        replay(userRepository);

        try {
            rentController.createRent(rent);
        } catch(UserNotFoundException actual) {
            assertEquals("User Ids wrong: Customer - " + rent.getCustomerId() + ",Company - " + rent.getCompanyId() + ".",actual.getMessage());
        }
    }

    @Test
    public void userCustomerNotFoundException() throws Exception {
        rent.setCustomerId(15L);
        rent.setCompanyId(0L);

        expect(userRepository.exists(anyLong())).andReturn(false).anyTimes();

        replay(userRepository);

        try {
            rentController.createRent(rent);
        } catch(UserNotFoundException actual) {
            assertEquals("Customer with Id: " + rent.getCustomerId() + " does not exist.",actual.getMessage());
        }
    }

    @Test
    public void userCompanyNotFoundException() throws Exception {
        rent.setCustomerId(0L);
        rent.setCompanyId(30L);

        expect(userRepository.exists(anyLong())).andReturn(false).anyTimes();

        replay(userRepository);

        try {
            rentController.createRent(rent);
        } catch(UserNotFoundException actual) {
            assertEquals("Company with Id: " + rent.getCompanyId() + " does not exist.",actual.getMessage());
        }
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleNotFoundException() throws Exception {
        rent.setVehicleId(50L);

        expect(userRepository.exists(anyLong())).andReturn(true);
        replay(userRepository);

        rentController.createRent(rent);
    }

}
