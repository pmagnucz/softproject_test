package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.VehichleType;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.model.VehicleStatusType;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    public void addNewVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse("2017-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String a = "CAR";

        vehicle.setId(1);
        vehicle.setType(VehichleType.CAR);
        vehicle.setManufacturer("Ford");
        vehicle.setPerformance(1500.24);
        vehicle.setPersons(5);
        vehicle.setRentCost(15000);
        vehicle.setVehicleStatus(VehicleStatusType.FREE);
        vehicle.setYearOfManufacture(date);

        VehicleEntity mockedVehicleEntity= VehicleMapper.mapModelToEntity(vehicle);
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(mockedVehicleEntity);

        Vehicle actual = vehicleManagementService.addNewVehicle(vehicle);
        assertEquals(vehicle, actual);
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
    public void removeVehicle() throws Exception {
    }

}