package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.VehicleController;
import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.exception.ExistingVehiclePlateNumber;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
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

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

public class VehicleControllerTest {
    private VehicleController vehicleController;
    private VehicleManagementService vehicleService;
    private VehicleRepository vehicleRepository;


    private Car vehicle;

    private CreateVehicleRequest vehicleRequest;

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











}