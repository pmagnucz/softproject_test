package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.*;
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
        //vehicleRepository = mock(VehicleRepository.class);
        //vehicleManagementService = new VehicleManagementServiceImpl(vehicleRepository);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNewVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        Car car = new Car();
        Ship ship = new Ship();

        if (vehicle instanceof Car){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse("2007-08-21");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            car.setDrawBar(true);
            car.setPlateNumber("AAA-111");
            car.setVehicleIdentificationNumber("245354sd");
            car.setId(1);
            car.setType(VehichleType.CAR);
            car.setManufacturer("Ford");
            car.setPerformance(1500.24);
            car.setPersons(5);
            car.setRentCost(15000);
            car.setVehicleStatus(VehicleStatusType.FREE);
            car.setYearOfManufacture(date);

            VehicleEntity mockedVehicleEntity= VehicleMapper.mapModelToEntity(car);
            when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(mockedVehicleEntity);

            Vehicle actual = vehicleManagementService.addNewVehicle(car);

            assertEquals(car, actual);
        } else if(vehicle instanceof Ship){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse("2010-07-18");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ship.setId(2);
            ship.setLength(2000);
            ship.setWithTrailer(true);
            ship.setType(VehichleType.SHIP);
            ship.setManufacturer("Lambo");
            ship.setPerformance(120);
            ship.setPersons(6);
            ship.setRentCost(13000);
            ship.setVehicleStatus(VehicleStatusType.RESERVED);
            ship.setYearOfManufacture(date);

            VehicleEntity mockedVehicleEntity= VehicleMapper.mapModelToEntity(ship);
            when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(mockedVehicleEntity);

            Vehicle actual = vehicleManagementService.addNewVehicle(ship);

            assertEquals(ship, actual);
        }

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