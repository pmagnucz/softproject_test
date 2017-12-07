package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.VehicleController;
import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.exception.ExistingVehiclePlateNumber;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class VehicleControllerTest {
    private VehicleController vehicleController;
    private VehicleManagementService vehicleService;
    private VehicleRepository vehicleRepository;


    private Car vehicle;
    private Car vehicle2;

    private CreateVehicleRequest vehicleRequest;
    private UpdateVehicleRequest updateVehicleRequest;

    @Before
    public void setUp() throws ParseException {
        vehicleRepository = mock(VehicleRepository.class);

        vehicleService = new VehicleManagementServiceImpl(vehicleRepository);
        vehicleController = new VehicleController(vehicleService);

        DateFormat format = new SimpleDateFormat("yyyy-MM");

        vehicle = new Car();

        vehicle.setId(1L);
        vehicle.setType(VehicleType.CAR);
        vehicle.setManufacturer("egyes");
        vehicle.setRentCost(10000.0);
        vehicle.setPersons(1);
        vehicle.setPerformance(500.0);
        vehicle.setVehicleStatus(VehicleStatusType.FREE);
        vehicle.setYearOfManufacture(format.parse("1950-01"));
        vehicle.setPlateNumber("abc123");
        vehicle.setVehicleIdentificationNumber("egyid");
        vehicle.setDrawBar(true);

        vehicleRequest = new CreateVehicleRequest();
        vehicleRequest.setType(vehicle.getType());
        vehicleRequest.setManufacturer(vehicle.getManufacturer());
        vehicleRequest.setRentCost(vehicle.getRentCost());
        vehicleRequest.setPersons(vehicle.getPersons());
        vehicleRequest.setPerformance(vehicle.getPerformance());
        vehicleRequest.setVehicleStatus(vehicle.getVehicleStatus());
        vehicleRequest.setYearOfManufacture(vehicle.getYearOfManufacture());
        vehicleRequest.setPlateNumber(vehicle.getPlateNumber());
        vehicleRequest.setVehicleIdentificationNumber(vehicle.getVehicleIdentificationNumber());
        vehicleRequest.setDrawBar(vehicle.isDrawBar());
        vehicleRequest.setId(vehicle.getId());

        vehicle2 = new Car();

        vehicle2.setId(2L);
        vehicle2.setType(VehicleType.CAR);
        vehicle2.setManufacturer("kettes");
        vehicle2.setRentCost(20000.0);
        vehicle2.setPersons(2);
        vehicle2.setPerformance(100.0);
        vehicle2.setVehicleStatus(VehicleStatusType.RESERVED);
        vehicle2.setYearOfManufacture(format.parse("1960-01"));
        vehicle2.setPlateNumber("cba321");
        vehicle2.setVehicleIdentificationNumber("kettoid");
        vehicle2.setDrawBar(false);

        updateVehicleRequest = new UpdateVehicleRequest();
        updateVehicleRequest.setId(1L);
        updateVehicleRequest.setType(vehicle.getType());
        updateVehicleRequest.setCar(true);
        updateVehicleRequest.setShip(false);
        updateVehicleRequest.setManufacturer(vehicle2.getManufacturer());
        updateVehicleRequest.setRentCost(vehicle2.getRentCost());
        updateVehicleRequest.setPersons(vehicle2.getPersons());
        updateVehicleRequest.setPerformance(vehicle.getPerformance());
        updateVehicleRequest.setVehicleStatus(vehicle.getVehicleStatus());
        updateVehicleRequest.setYearOfManufacture(vehicle2.getYearOfManufacture());
        updateVehicleRequest.setPlateNumber(vehicle2.getPlateNumber());
        updateVehicleRequest.setVehicleIdentificationNumber(vehicle2.getVehicleIdentificationNumber());
        updateVehicleRequest.setDrawBar(vehicle2.isDrawBar());
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addNewVehicle() throws ExistingVehiclePlateNumber, NotSupportedVehicleTypeException, NotValidPlateNumberFormatException {
        Vehicle expected = vehicle;
        VehicleEntity mockEntity = VehicleMapper.mapModelToEntity(vehicle);
        expect(vehicleRepository.save(mockEntity)).andReturn(mockEntity);

        replay(vehicleRepository);

        Vehicle actual = vehicleController.addNewVehicle(vehicleRequest).getBody();

        assertEquals(expected, actual);
    }

    @Test
    public void updateVehicle() throws VehicleNotFoundException, NotValidPlateNumberFormatException{
        vehicle2.setId(vehicle.getId());
        vehicle2.setType(vehicle.getType());
        vehicle2.setPerformance(vehicle.getPerformance());
        vehicle2.setVehicleStatus(vehicle.getVehicleStatus());

        VehicleEntity mockEntity = VehicleMapper.mapModelToEntity(vehicle);

        expect(vehicleRepository.findOne(anyLong())).andReturn(mockEntity);
        expect(vehicleRepository.save(anyObject(VehicleEntity.class))).andReturn(mockEntity);

        replay(vehicleRepository);

        vehicleController.updateVehicle(updateVehicleRequest);

        Vehicle actual = VehicleMapper.mapEntityToModel(mockEntity);

        assertEquals(vehicle2, actual);
    }

    @Test
    public void removeVehicle() throws VehicleNotFoundException {
        vehicleRepository.delete(vehicle.getId());
        expectLastCall();

        replay(vehicleRepository);

        vehicleController.removeVehicle(vehicle);
    }

    @Test
    public void getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        vehicles.add(vehicle2);

        List<VehicleEntity> expectedEntities = VehicleMapper.mapModelListToEntityList(vehicles);

        expect(vehicleRepository.findAll()).andReturn(expectedEntities);

        replay(vehicleRepository);

        List<Vehicle> actual = vehicleController.getVehicles().getBody();
        assertEquals(vehicles, actual);
    }

    @Test
    public void getVehicleById() throws VehicleNotFoundException {
        VehicleEntity mockEntity = VehicleMapper.mapModelToEntity(vehicle);

        expect(vehicleRepository.findOne(anyLong())).andReturn(mockEntity);

        replay(vehicleRepository);

        Vehicle actual = vehicleController.getVehicleById(vehicle.getId()).getBody();
        assertEquals(vehicle, actual);
    }

    @Test
    public void getVehiclesByFilterOption() throws NotValidPlateNumberFormatException, ParseException {
        SearchVehicleRequest searchVehicleRequest = new SearchVehicleRequest();

        DateFormat format = new SimpleDateFormat("yyyy-MM");

        searchVehicleRequest.setManufacturer("kettes");
        searchVehicleRequest.setRentCost(20000.0);
        searchVehicleRequest.setYearOfManufacture(format.parse("1960-01"));

        List<Vehicle> vehicles =new ArrayList<>();
        vehicles.add(vehicle);
        vehicles.add(vehicle2);

        List<VehicleEntity> expectedEntities = VehicleMapper.mapModelListToEntityList(vehicles);

        expect(vehicleRepository.findAll()).andReturn(expectedEntities);

        replay(vehicleRepository);

        List<Vehicle> expected = new ArrayList<>();
        expected.add(vehicle2);

        List<Vehicle> actual = vehicleController.getVehicleByFilterOptions(searchVehicleRequest).getBody();

        assertEquals(expected, actual);
    }

}