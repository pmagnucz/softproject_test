package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.RentEntity;
import hu.uni.miskolc.iit.exception.NegativeValueException;
import hu.uni.miskolc.iit.exception.WrongRentDateException;
import hu.uni.miskolc.iit.mapper.RentMapper;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.repositories.RentRepository;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

/**
 * Created by zsoltme on 2017.10.23..
 */
public class RentManagementServiceImplTest {

    private RentManagementService rentManagementService;
    private RentRepository rentRepository;

    @Before
    public void setUp() throws Exception {
        rentRepository = mock(RentRepository.class);
        rentManagementService = new RentManagementServiceImpl(rentRepository);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewRent() throws Exception {
        Rent rent = new Rent();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setCustomerId(1);
        rent.setCompanyId(2);
        rent.setVehicleId(3);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setKmFee(100000);
        rent.setDayFee(150000);
        rent.setOtherFee(0);
        rent.setTotalFee(250000);
        rent.setPaid(false);

        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
        when(rentRepository.save(any(RentEntity.class))).thenReturn(mockEntity);

        Rent actual = rentManagementService.addNewRent(rent);
        assertEquals(rent,actual);
    }

    @Test
    public void getRentById() throws Exception {
        Rent rent = new Rent();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setId(5);
        rent.setCustomerId(1);
        rent.setCompanyId(2);
        rent.setVehicleId(3);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setKmFee(100000);
        rent.setDayFee(150000);
        rent.setOtherFee(0);
        rent.setTotalFee(250000);
        rent.setPaid(false);

        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
        when(rentRepository.findOne(any(Long.class))).thenReturn(mockEntity);

        Rent actual = rentManagementService.getRentById(5);
        assertEquals(rent,actual);
    }

    @Test
    public void getRentByFilterOptions() throws Exception {
        Rent rent = new Rent();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        Date startDate2 = null;
        Date endDate2 = null;
        try {
            startDate = format.parse("2017-02-01");
            startDate2 = format.parse("2017-02-02");
            endDate = format.parse("2017-03-01");
            endDate2 = format.parse("2017-03-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setId(5);
        rent.setCustomerId(1);
        rent.setCompanyId(2);
        rent.setVehicleId(3);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setKmFee(100000);
        rent.setDayFee(150000);
        rent.setOtherFee(0);
        rent.setTotalFee(250000);
        rent.setPaid(false);

        Rent rent2 = new Rent();

        rent2.setId(6);
        rent2.setCustomerId(2);
        rent2.setCompanyId(2);
        rent2.setVehicleId(3);
        rent2.setStartDate(startDate);
        rent2.setEndDate(endDate);
        rent2.setDurationExtendable(true);
        rent2.setExtendedHours(24);
        rent2.setKmUsed(100);
        rent2.setKmFee(100000);
        rent2.setDayFee(150000);
        rent2.setOtherFee(0);
        rent2.setTotalFee(250000);
        rent2.setPaid(false);

        SearchRentRequest searchRentRequest = new SearchRentRequest(1,100,150,startDate2,endDate2);

        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        rents.add(rent2);

        List<RentEntity> expectedEntities = RentMapper.mapRentListToRentEntityList(rents);
        when(rentRepository.findAll()).thenReturn(expectedEntities);

        List<Rent> expected = new ArrayList<>();
        expected.add(rent);
        List<Rent> actual = rentManagementService.getRentByFilterOptions(searchRentRequest);

        assertEquals(expected,actual);
    }

    @Test
    public void getRents() throws Exception {
        Rent rent = new Rent();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setId(5);
        rent.setCustomerId(1);
        rent.setCompanyId(2);
        rent.setVehicleId(3);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setKmFee(100000);
        rent.setDayFee(150000);
        rent.setOtherFee(0);
        rent.setTotalFee(250000);
        rent.setPaid(false);

        Rent rent2 = new Rent();

        rent2.setId(6);
        rent2.setCustomerId(2);
        rent2.setCompanyId(2);
        rent2.setVehicleId(3);
        rent2.setStartDate(startDate);
        rent2.setEndDate(endDate);
        rent2.setDurationExtendable(true);
        rent2.setExtendedHours(24);
        rent2.setKmUsed(100);
        rent2.setKmFee(100000);
        rent2.setDayFee(150000);
        rent2.setOtherFee(0);
        rent2.setTotalFee(250000);
        rent2.setPaid(false);

        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        rents.add(rent2);

        List<RentEntity> expectedEntities = RentMapper.mapRentListToRentEntityList(rents);
        when(rentRepository.findAll()).thenReturn(expectedEntities);

        List<Rent> actual = rentManagementService.getRents();
        assertEquals(rents, actual);

    }

    @Test
    public void updateRent() throws Exception {
        Rent rent = new Rent();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setId(5);
        rent.setCustomerId(1);
        rent.setCompanyId(2);
        rent.setVehicleId(3);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setKmFee(100000);
        rent.setDayFee(150000);
        rent.setOtherFee(0);
        rent.setTotalFee(250000);
        rent.setPaid(false);

        Rent expected = new Rent();

        expected.setId(5);
        expected.setCustomerId(2);
        expected.setCompanyId(2);
        expected.setVehicleId(3);
        expected.setStartDate(startDate);
        expected.setEndDate(endDate);
        expected.setDurationExtendable(true);
        expected.setExtendedHours(24);
        expected.setKmUsed(100);
        expected.setKmFee(100000);
        expected.setDayFee(150000);
        expected.setOtherFee(0);
        expected.setTotalFee(250000);
        expected.setPaid(false);

        RentEntity mockEntity = RentMapper.mapModelToEntity(rent);
        when(rentRepository.findOne(any(Long.class))).thenReturn(mockEntity);

        rentManagementService.addNewRent(rent);
        rentManagementService.updateRent(expected);

        Rent actual = RentMapper.mapEntityToModel(mockEntity);

        assertEquals(expected.getCustomerId(),actual.getCustomerId());
    }

    @Test
    public void removeRent() throws Exception {
        Rent rent = new Rent();

        rent.setId(5);

        when(rentRepository.findOne(any(Long.class))).thenReturn(new RentEntity());
        rentManagementService.removeRent(rent);

        verify(rentRepository,times(1)).delete(Long.valueOf(rent.getId()));

    }

    @Test
    public void catchExceptions() throws Exception {
    }

    @Test(expected = NegativeValueException.class)
    public void negativeNumberException() throws Exception {

        Rent rent = new Rent();

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

        Rent rent = new Rent();

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
}