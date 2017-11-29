package hu.uni.miskolc.iit;

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
import static org.junit.Assert.*;

/**
 * Created by zsoltme on 2017.10.23..
 */
public class RentManagementServiceImplTest {

    private RentManagementService rentManagementService;
    private RentRepository rentRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;
    private Rent rent;
    private Rent rent2;

    @Before
    public void setUp() throws Exception {
        rentRepository = mock(RentRepository.class);
        userRepository = mock(UserRepository.class);
        vehicleRepository = mock(VehicleRepository.class);
        rentManagementService = new RentManagementServiceImpl(rentRepository,userRepository,vehicleRepository);

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

        rent.setId(5L);
        rent.setCustomerId(1L);
        rent.setCompanyId(2L);
        rent.setVehicleId(3L);
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
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewRent() throws Exception {
        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
        /*when(rentRepository.save(any(RentEntity.class))).thenReturn(mockEntity);

        when(userRepository.exists(any(Long.class))).thenReturn(true);
        when(vehicleRepository.exists(any(Long.class))).thenReturn(true);*/

        Rent actual = rentManagementService.addNewRent(rent);
        assertEquals(rent,actual);
    }

    @Test
    public void getRentById() throws Exception {
        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
/*
        when(rentRepository.findOne(any(Long.class))).thenReturn(mockEntity);
*/

        Rent actual = rentManagementService.getRentById(5);
        assertEquals(rent,actual);
    }

    @Test
    public void getRentByFilterOptions() throws Exception {
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
/*
        when(rentRepository.findAll()).thenReturn(expectedEntities);
*/

        List<Rent> expected = new ArrayList<>();
        expected.add(rent);
        List<Rent> actual = rentManagementService.getRentByFilterOptions(searchRentRequest);

        assertEquals(expected,actual);
    }

    @Test
    public void getRents() throws Exception {
        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        rents.add(rent2);

        List<RentEntity> expectedEntities = RentMapper.mapRentListToRentEntityList(rents);
/*
        when(rentRepository.findAll()).thenReturn(expectedEntities);
*/

        List<Rent> actual = rentManagementService.getRents();
        assertEquals(rents, actual);

    }

    @Test
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

        rent2.setId(rent.getId());
        rent2.setCustomerId(300L);
        rent2.setCompanyId(7L);
        rent2.setVehicleId(9L);
        rent2.setStartDate(startDateExpected);
        rent2.setEndDate(endDateExpected);


        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
       /* when(rentRepository.findOne(any(Long.class))).thenReturn(mockEntity);
        when(rentRepository.save(any(RentEntity.class))).thenReturn(mockEntity);

        when(rentRepository.exists(any(Long.class))).thenReturn(true);
        when(userRepository.exists(any(Long.class))).thenReturn(true);
        when(vehicleRepository.exists(any(Long.class))).thenReturn(true);*/

        rentManagementService.updateRent(rent2);

        Rent actual = RentMapper.mapEntityToModel(mockEntity);

        assertEquals(rent2,actual);
    }

    @Test
    public void removeRent() throws Exception {
       /* when(rentRepository.exists(any(Long.class))).thenReturn(true);
        when(userRepository.exists(any(Long.class))).thenReturn(true);
        when(vehicleRepository.exists(any(Long.class))).thenReturn(true);

        rentManagementService.removeRent(rent);

        verify(rentRepository,times(1)).delete(Long.valueOf(rent.getId()));
*/
    }

    @Test(expected = NegativeValueException.class)
    public void negativeNumberException() throws Exception {
        rent.setExtendedHours(-1);
        rent.setKmUsed(-1);
        rent.setKmFee(-1);
        rent.setDayFee(-1);
        rent.setOtherFee(-1);
        rent.setTotalFee(-1);

        rentManagementService.addNewRent(rent);

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

        rentManagementService.addNewRent(rent);
    }

    @Test(expected = RentIdAlreadyExistsException.class)
    public void rentIdAlreadyExistsException() throws Exception {
        rent.setId(1L);

/*
        when(rentRepository.exists(1L)).thenReturn(true);
*/

        rentManagementService.addNewRent(rent);
    }

    @Test(expected = RentWrongTotalFeeException.class)
    public void wrongTotalFeeException() throws Exception {
        rent.setTotalFee(1);

        rentManagementService.addNewRent(rent);
    }

    @Test
    public void userNotFoundException() throws Exception {
        rent.setCustomerId(0L);
        rent.setCompanyId(0L);

        try {
            rentManagementService.addNewRent(rent);
        } catch(UserNotFoundException actual) {
            assertEquals("User Ids wrong: Customer - " + rent.getCustomerId() + ",Company - " + rent.getCompanyId() + ".",actual.getMessage());
        }
    }

    @Test
    public void userCustomerNotFoundException() throws Exception {
        rent.setCustomerId(15L);
        rent.setCompanyId(0L);

        try {
            rentManagementService.addNewRent(rent);
        } catch(UserNotFoundException actual) {
            assertEquals("Customer with Id: " + rent.getCustomerId() + " does not exist.",actual.getMessage());
        }
    }

    @Test
    public void userCompanyNotFoundException() throws Exception {
        rent.setCustomerId(0L);
        rent.setCompanyId(30L);
        try {
            rentManagementService.addNewRent(rent);
        } catch(UserNotFoundException actual) {
            assertEquals("Company with Id: " + rent.getCompanyId() + " does not exist.",actual.getMessage());
        }
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleNotFoundException() throws Exception {
        rent.setVehicleId(50L);

/*
        when(userRepository.exists(any(Long.class))).thenReturn(true);
*/

        rentManagementService.addNewRent(rent);
    }

    @Test(expected = RentNotFoundException.class)
    public void rentNotFoundException() throws Exception {
        rentManagementService.updateRent(rent);
    }
}