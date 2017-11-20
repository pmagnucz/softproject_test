package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.VehicleEntity;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;



public class VehicleManagementServiceImplTest {

    private VehicleManagementService vehicleManagementService;
    private VehicleRepository vehicleRepository ;
    private Vehicle vehicle;
    private Ship ship;
    private Car car;

    @Before
    public void setUp() throws Exception {
        vehicleRepository = mock(VehicleRepository.class);
        vehicleManagementService = new VehicleManagementServiceImpl(vehicleRepository);
        vehicle = new Vehicle();
        car = new Car();

        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2017-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        vehicle.setId(1L);
        vehicle.setType(VehicleType.CAR);
        vehicle.setManufacturer("Ford");
        vehicle.setYearOfManufacture(date);
        vehicle.setRentCost(15000);
        vehicle.setPersons(5);
        vehicle.setPerformance(1500.24);
        vehicle.setVehicleStatus(VehicleStatusType.FREE);


        car.setId(1L);
        car.setType(VehicleType.CAR);
        car.setManufacturer("Ford");
        car.setYearOfManufacture(date);
        car.setRentCost(15000);
        car.setPersons(5);
        car.setPerformance(1500.24);
        car.setVehicleStatus(VehicleStatusType.FREE);
        car.setPlateNumber("AAA-111");
        car.setVehicleIdentificationNumber("245354sd");
        car.setDrawBar(true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewVehicleCar() throws Exception {
        car = new Car();

        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse("2017-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        car.setId(1L);
        car.setType(VehicleType.CAR);
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
        ship = new Ship();
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
        date = format.parse("2010-07");
        } catch (ParseException e) {
        e.printStackTrace();
        }

        ship.setId(2L);
        ship.setLength(2000.0);
        ship.setWithTrailer(true);
        ship.setType(VehicleType.SHIP);
        ship.setShipId("M");
        ship.setManufacturer("Lambo");
        ship.setPerformance(120);
        ship.setPersons(6);
        ship.setRentCost(13000);
        ship.setVehicleStatus(VehicleStatusType.RESERVED);
        ship.setYearOfManufacture(date);

        VehicleEntity mockedVehicleEntity = VehicleMapper.mapModelToEntity(ship);
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(mockedVehicleEntity);
        when(vehicleRepository.exists(any(Long.class))).thenReturn(true);

        Vehicle actual = vehicleManagementService.addNewVehicle(ship);

        assertEquals(ship, actual);

        }

    @Test
    public void getVehicleById() throws Exception {
        VehicleEntity mockEntity = VehicleMapper.mapModelToEntity(car);
        when(vehicleRepository.findOne(any(Long.class))).thenReturn(mockEntity);

        Vehicle actual = vehicleManagementService.getVehicleById(5L);
        assertEquals(car,actual);
    }

    @Test
    public void getVehicleByFilterOptions() throws Exception {
    }

    @Test
    public void getVehicles() throws Exception {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);

        List<VehicleEntity> expectedEntities = VehicleMapper.mapModelListToEntityList(vehicles);
        when(vehicleRepository.findAll()).thenReturn(expectedEntities);

        List<Vehicle> actual = vehicleManagementService.getVehicles();
        assertEquals(vehicles, actual);
    }

    @Test
    public void updateVehicle() throws Exception {
    }

    @Test
    public void removeVehicleCar() throws Exception {
        when(vehicleRepository.exists(any(Long.class))).thenReturn(true);

        vehicleManagementService.removeVehicle(car);

        verify(vehicleRepository,times(1)).delete(car.getId());
    }

    @Test
    public void removeVehicleShip() throws Exception {
        ship = new Ship();
        ship.setId(1L);

        when(vehicleRepository.exists(any(Long.class))).thenReturn(true);

        vehicleManagementService.removeVehicle(ship);

        verify(vehicleRepository,times(1)).delete(ship.getId());
    }

}
