package hu.uni.miskolc.iit;

import ch.qos.logback.core.CoreConstants;
import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.mapper.CarMapper;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;



public class VehicleManagementServiceImplTest {

    private VehicleManagementService vehicleManagementService;
    private VehicleRepository vehicleRepository ;

    @Before
    public void setUp() throws Exception {
        vehicleRepository = mock(VehicleRepository.class);
        vehicleManagementService = new VehicleManagementServiceImpl(vehicleRepository);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewVehicleCar() throws Exception {
        Car car = new Car();

        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2007-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        car.setId(1);
        car.setType(VehichleType.CAR);
        car.setManufacturer("Ford");
        car.setYearOfManufacture(date);
        car.setRentCost(15000);
        car.setPersons(5);
        car.setPerformance(1500.24);
        car.setVehicleStatus(VehicleStatusType.FREE);
        car.setPlateNumber("AAA-111");
        car.setVehicleIdentificationNumber("245354sd");
        car.setDrawBar(true);

        VehicleEntity mockedVehicleEntity = VehicleMapper.mapModelToEntity(car);
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(mockedVehicleEntity);

        Vehicle actual = vehicleManagementService.addNewVehicle(car);
        assertEquals(car, actual);
    }

    @Test
    public void addNewVehicleShip() throws Exception {
        Ship ship = new Ship();
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
        date = format.parse("2010-07");
        } catch (ParseException e) {
        e.printStackTrace();
        }

        ship.setId(2);
        ship.setLength(2000.0);
        ship.setWithTrailer(true);
        ship.setType(VehichleType.SHIP);
        ship.setShipId("M");
        ship.setManufacturer("Lambo");
        ship.setPerformance(120);
        ship.setPersons(6);
        ship.setRentCost(13000);
        ship.setVehicleStatus(VehicleStatusType.RESERVED);
        ship.setYearOfManufacture(date);

        VehicleEntity mockedVehicleEntity = VehicleMapper.mapModelToEntity(ship);
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(mockedVehicleEntity);

        Vehicle actual = vehicleManagementService.addNewVehicle(ship);

        assertEquals(ship, actual);
        }

    @Test
    public void getVehicleById() throws Exception {
    }

    @Test
    public void getVehicleByFilterOptions() throws Exception {
    }

    @Test
    public void getVehicles() throws Exception {
    }

    @Test
    public void updateVehicle() throws Exception {
    }

    @Test
    public void removeVehicleCar() throws Exception {
        Car car = new Car();

        car.setId(1);

        when(vehicleRepository.findOne(any(String.class))).thenReturn(new VehicleEntity());
        vehicleManagementService.removeVehicle(car);

        verify(vehicleRepository,times(1)).delete(VehicleMapper.mapModelToEntity(car));
    }

    @Test
    public void removeVehicleShip() throws Exception {
        Ship ship = new Ship();

        ship.setId(4);

        when(vehicleRepository.findOne(any(String.class))).thenReturn(new VehicleEntity());
        vehicleManagementService.removeVehicle(ship);

        verify(vehicleRepository,times(1)).delete(VehicleMapper.mapModelToEntity(ship));
    }

}