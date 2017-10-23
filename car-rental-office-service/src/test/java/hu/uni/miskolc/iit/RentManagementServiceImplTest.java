package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.RentEntity;
import hu.uni.miskolc.iit.exception.NegativeValueException;
import hu.uni.miskolc.iit.exception.WrongRentDateException;
import hu.uni.miskolc.iit.mapper.RentMapper;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.repositories.RentRepository;
import hu.uni.miskolc.iit.service.RentManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    }

    @Test
    public void getRents() throws Exception {
    }

    @Test
    public void updateRent() throws Exception {
    }

    @Test
    public void removeRent() throws Exception {
    }

    @Test
    public void catchExceptions() throws Exception {
    }

    @Test(expected = NegativeValueException.class)
    public void negativeNumberException() throws Exception {

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
        rent.setExtendedHours(-1);
        rent.setKmUsed(-1);
        rent.setKmFee(-1);
        rent.setDayFee(-1);
        rent.setOtherFee(-1);
        rent.setTotalFee(-1);
        rent.setPaid(false);

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

        rentManagementService.addNewRent(rent);

    }
}