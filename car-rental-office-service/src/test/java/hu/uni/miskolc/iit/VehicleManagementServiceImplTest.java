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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class VehicleManagementServiceImplTest {

    private VehicleManagementService vehicleManagementService;
    private VehicleRepository vehicleRepository ;
    private Vehicle vehicle;
    private Ship ship;
    private Car car;

//TODO EasyMock megoldása a tesztekben

    @Before
    public void setUp() throws Exception {
        vehicleRepository = mock(VehicleRepository.class);
        vehicleManagementService = new VehicleManagementServiceImpl(vehicleRepository);
        vehicle = new Vehicle();
        car = new Car();

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2017-08", dateTimeFormatter);

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

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2017-08", dateTimeFormatter);

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

        expect(vehicleRepository.save(anyObject(VehicleEntity.class))).andReturn(mockedVehicleEntity);

        replay(vehicleRepository);

        Vehicle actual = vehicleManagementService.addNewVehicle(car);
        assertEquals(car, actual);
    }

    @Test
    public void addNewVehicleShip() throws Exception {
        ship = new Ship();

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2010-07", dateTimeFormatter);

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
        expect(vehicleRepository.save(anyObject(VehicleEntity.class))).andReturn(mockedVehicleEntity);
        expect(vehicleRepository.exists(anyLong())).andReturn(true);

        replay(vehicleRepository);

        Vehicle actual = vehicleManagementService.addNewVehicle(ship);

        assertEquals(ship, actual);

        }

    @Test
    public void getVehicleById() throws Exception {
        VehicleEntity mockEntity = VehicleMapper.mapModelToEntity(car);

        expect(vehicleRepository.findOne(anyLong())).andReturn(mockEntity);

        replay(vehicleRepository);

        Vehicle actual = vehicleManagementService.getVehicleById(5L);
        assertEquals(car,actual);
    }

    @Test
    public void getVehicleByFilterOptions() throws Exception {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate yearOfManufactureRequest = LocalDate.parse("2001-09", dateTimeFormatter);

        Ship shipModel = new Ship();
        shipModel.setId(5L);
        shipModel.setLength(1000.0);
        shipModel.setWithTrailer(true);
        shipModel.setType(VehicleType.SHIP);
        shipModel.setShipId("AAA");
        shipModel.setManufacturer("manufacturer");
        shipModel.setPerformance(300);
        shipModel.setPersons(4);
        shipModel.setRentCost(20000.0);
        shipModel.setVehicleStatus(VehicleStatusType.FREE);
        shipModel.setYearOfManufacture(yearOfManufactureRequest);

        SearchVehicleRequest searchVehicleRequest =
                new SearchVehicleRequest(VehicleType.SHIP,"manufacturer2",yearOfManufactureRequest,100000.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(shipModel);

        List<VehicleEntity> expectedEntities = VehicleMapper.mapModelListToEntityList(vehicles);

        expect(vehicleRepository.findAll()).andReturn(expectedEntities);

        replay(vehicleRepository);

        List<Vehicle> expected = new ArrayList<>();
        expected.add(shipModel);
        List<Vehicle> actual = vehicleManagementService.getVehicleByFilterOptions(searchVehicleRequest);

        assertEquals(expected,actual);
    }

    @Test
    public void getVehicles() throws Exception {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);

        List<VehicleEntity> expectedEntities = VehicleMapper.mapModelListToEntityList(vehicles);

        expect(vehicleRepository.findAll()).andReturn(expectedEntities);

        replay(vehicleRepository);

        List<Vehicle> actual = vehicleManagementService.getVehicles();
        assertEquals(vehicles, actual);
    }

    @Test
    public void updateVehicle() throws Exception {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate date = LocalDate.parse("2017-08", dateTimeFormatter);

        VehicleEntity mockEntity = VehicleMapper.mapModelToEntity(car);

        expect(vehicleRepository.findOne(anyLong())).andReturn(mockEntity);
        expect(vehicleRepository.save(anyObject(VehicleEntity.class))).andReturn(mockEntity);

        replay(vehicleRepository);

        UpdateVehicleRequest updateVehicleRequest = new UpdateVehicleRequest();

        updateVehicleRequest.setId(1L);
        updateVehicleRequest.setType(VehicleType.CAR);
        updateVehicleRequest.setManufacturer("Ford2222");
        updateVehicleRequest.setYearOfManufacture(date);
        updateVehicleRequest.setRentCost(25000);
        updateVehicleRequest.setPersons(2);
        updateVehicleRequest.setCar(true);
        updateVehicleRequest.setPerformance(2500.24);
        updateVehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        updateVehicleRequest.setPlateNumber("AAA-222");
        updateVehicleRequest.setVehicleIdentificationNumber("222222sd");
        updateVehicleRequest.setDrawBar(true);

        vehicleManagementService.updateVehicle(updateVehicleRequest);

        Vehicle actual = VehicleMapper.mapEntityToModel(mockEntity);

        assertNotEquals(car,actual);
    }

    @Test
    public void removeVehicleCar() throws Exception {

        expect(vehicleRepository.exists(anyLong())).andReturn(true);

        vehicleRepository.delete(car.getId());

        replay(vehicleRepository);

        vehicleManagementService.removeVehicle(car);
    }

    @Test
    public void removeVehicleShip() throws Exception {
        ship = new Ship();
        ship.setId(1L);

        expect(vehicleRepository.exists(anyLong())).andReturn(true);

        vehicleRepository.delete(ship.getId());

        replay(vehicleRepository);

        vehicleManagementService.removeVehicle(ship);
    }


}
