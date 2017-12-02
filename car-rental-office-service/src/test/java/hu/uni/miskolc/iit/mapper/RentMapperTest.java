package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.RentEntity;
import hu.uni.miskolc.iit.model.Rent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by zsoltme on 2017.10.29..
 */
public class RentMapperTest {

    private Rent rent;
    private RentEntity rentEntity;

    @Before
    public void setUp() throws Exception {
        rent = new Rent();

        rent.setId(5L);
        rent.setCustomerId(1L);
        rent.setCompanyId(2L);
        rent.setVehicleId(3L);
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

        rentEntity = new RentEntity();
        rentEntity.setId(5L);
        rentEntity.setCustomerId(1L);
        rentEntity.setCompanyId(2L);
        rentEntity.setVehicleId(3L);
        rentEntity.setStartDate("2017-02-01");
        rentEntity.setEndDate("2017-03-01");
        rentEntity.setDurationExtendable("true");
        rentEntity.setExtendedHours("24");
        rentEntity.setKmUsed("100");
        rentEntity.setDayFee("150000.0");
        rentEntity.setKmFee("100000.0");
        rentEntity.setOtherFee("0.0");
        rentEntity.setTotalFee("250000.0");
        rentEntity.setPaid("false");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mapModelToEntity() throws Exception {
        RentEntity expected = rentEntity;
        RentEntity actual = RentMapper.mapModelToEntity(rent);
        assertEquals(expected,actual);
    }

    @Test
    public void mapRentListToRentEntityList() throws Exception {
        List<RentEntity> expected = new ArrayList<RentEntity>();
        expected.add(rentEntity);
        List<Rent> rentList = new ArrayList<Rent>();
        rentList.add(rent);
        List<RentEntity> actual = RentMapper.mapRentListToRentEntityList(rentList);
        assertEquals(expected,actual);

    }

    @Test
    public void mapEntityToModel() throws Exception {
        Rent expected = rent;
        Rent actual = RentMapper.mapEntityToModel(rentEntity);
        assertEquals(expected,actual);
    }

    @Test
    public void mapRentEntityListToModelList() throws Exception {
        List<Rent> expected = new ArrayList<Rent>();
        expected.add(rent);
        List<RentEntity> rentEntityList = new ArrayList<RentEntity>();
        rentEntityList.add(rentEntity);
        List<Rent> actual = RentMapper.mapRentEntityListToModelList(rentEntityList);
        assertEquals(expected,actual);
    }

}