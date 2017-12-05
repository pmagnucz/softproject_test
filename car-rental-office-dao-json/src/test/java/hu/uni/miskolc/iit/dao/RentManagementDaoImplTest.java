package hu.uni.miskolc.iit.dao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hu.uni.miskolc.iit.model.Rent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;


/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class RentManagementDaoImplTest {
    private RentManagementDao rentManagementDao;

 //TODO RentManagementDaoImplTest ExceptionalFlow

    @Before
    public void setUp() throws Exception {
        rentManagementDao = new RentManagementDaoImpl(new File("src/test/resources/rentDatabase.json"));
        rentManagementDao.clear();
    }

    @Test
    public void createRent() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent rentObject = new Rent();
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

        Rent actual = rentManagementDao.createRent(rentObject);

        Assert.assertNotNull(actual);
        Assert.assertEquals(rentObject, actual);
    }

    @Test
    public void getRentById() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent rentObject = new Rent();
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
        rentManagementDao.createRent(rentObject);

        Rent actual = rentManagementDao.getRentById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(rentObject, actual);
    }

    @Test
    public void getRents() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent rentObject = new Rent();
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
        rentManagementDao.createRent(rentObject);
        List<Rent> expected = new ArrayList<Rent>();
        expected.add(rentObject);

        List<Rent> actual = (List<Rent>) rentManagementDao.getRents();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteRent() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent rentObject = new Rent();
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
        rentManagementDao.createRent(rentObject);
        List<Rent> expected = new ArrayList<Rent>();

        rentManagementDao.deleteRent(rentObject);

        List<Rent> actual = (List<Rent>) rentManagementDao.getRents();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void exists() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Rent rentObject = new Rent();
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
        rentManagementDao.createRent(rentObject);

        boolean actual = rentManagementDao.exists(rentObject);

        Assert.assertNotNull(actual);
        Assert.assertTrue(actual);
    }

}