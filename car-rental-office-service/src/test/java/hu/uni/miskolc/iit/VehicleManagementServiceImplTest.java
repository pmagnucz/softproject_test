package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.model.Car;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.model.VehicleStatusType;
import hu.uni.miskolc.iit.model.VehicleType;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.easymock.EasyMock;
import org.easymock.internal.IMocksControlState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by pmagnucz on 2017. 12. 04..
 */
public class VehicleManagementServiceImplTest {
    private VehicleManagementDao vehicleManagementDao;
    private VehicleManagementService vehicleManagementService;
    // TODO VehicleManagementServiceImplTest fix the missing tests

    @Before
    public void setUp() throws Exception {
        vehicleManagementDao = EasyMock.mock(VehicleManagementDao.class);
        vehicleManagementService = new VehicleManagementServiceImpl(vehicleManagementDao);
    }

    @Test
    public void addNewVehicle() throws Exception {
        Car car = new Car();
        car.setPlateNumber("AAA-123");
        car.setVehicleIdentificationNumber("ID123123AS");
        car.setDrawBar(false);
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Opel");
        car.setYearOfManufacture(new Date());
        car.setRentCost(40.0D);
        car.setPersons(5);
        car.setPerformance(75.0D);
        car.setVehicleStatus(VehicleStatusType.FREE);

        expect(vehicleManagementDao.addVehicle(anyObject(Vehicle.class))).andReturn(car);
        replay(vehicleManagementDao);

        Vehicle actual = vehicleManagementService.addNewVehicle(car);

        Assert.assertNotNull(actual);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void getVehicleById() throws Exception {
        Car car = new Car();
        car.setPlateNumber("AAA-123");
        car.setVehicleIdentificationNumber("ID123123AS");
        car.setDrawBar(false);
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Opel");
        car.setYearOfManufacture(new Date());
        car.setRentCost(40.0D);
        car.setPersons(5);
        car.setPerformance(75.0D);
        car.setVehicleStatus(VehicleStatusType.FREE);

        expect(vehicleManagementDao.getVehicleById(anyLong())).andReturn(car);
        replay(vehicleManagementDao);

        Vehicle actual = vehicleManagementService.getVehicleById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(car, actual);
    }

    @Test
    public void getVehicles() throws Exception {
        Car car = new Car();
        car.setPlateNumber("AAA-123");
        car.setVehicleIdentificationNumber("ID123123AS");
        car.setDrawBar(false);
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Opel");
        car.setYearOfManufacture(new Date());
        car.setRentCost(40.0D);
        car.setPersons(5);
        car.setPerformance(75.0D);
        car.setVehicleStatus(VehicleStatusType.FREE);

        List<Vehicle> expected = new ArrayList<>();
        expected.add(car);

        expect(vehicleManagementDao.getVehicles()).andReturn(expected);
        replay(vehicleManagementDao);

        List<Vehicle> actual = vehicleManagementService.getVehicles();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateVehicle() throws Exception {

    }

    // TODO fix it
    public void removeVehicleCar() throws Exception {
        Car car = new Car();
        car.setPlateNumber("AAA-123");
        car.setVehicleIdentificationNumber("ID123123AS");
        car.setDrawBar(false);
        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Opel");
        car.setYearOfManufacture(new Date());
        car.setRentCost(40.0D);
        car.setPersons(5);
        car.setPerformance(75.0D);
        car.setVehicleStatus(VehicleStatusType.FREE);

        expect(vehicleManagementDao.exists(anyObject())).andReturn(true);
        expectLastCall();
        replay(vehicleManagementDao);

        vehicleManagementService.removeVehicle(car);
    }

}