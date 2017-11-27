package hu.uni.miskolc.iit.controller;

import hu.uni.miskolc.iit.exception.ExistingVehiclePlateNumber;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.VehicleManagementService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by botty on 2017. 10. 10..
 */

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private static final Logger LOGGER = Logger.getLogger(VehicleController.class);

    private VehicleManagementService vehicleManagementService;

    @Autowired
    public VehicleController(VehicleManagementService vehicleManagementService) {
        this.vehicleManagementService = vehicleManagementService;
    }

    // TODO ki kell szervezni az ismétlődő kódot vagy a három eset három private metódus legyen és itt csak a megfelelőt kell hívni
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Vehicle> addNewVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
        if (createVehicleRequest.getType() == VehicleType.CAR) {
            Car car = new Car();
            car.setPlateNumber(createVehicleRequest.getPlateNumber());
            car.setVehicleIdentificationNumber(createVehicleRequest.getVehicleIdentificationNumber());
            car.setDrawBar(createVehicleRequest.isDrawBar());
            car.setId(createVehicleRequest.getId());
            car.setType(createVehicleRequest.getType());
            car.setManufacturer(createVehicleRequest.getManufacturer());
            car.setYearOfManufacture(createVehicleRequest.getYearOfManufacture());
            car.setRentCost(createVehicleRequest.getRentCost());
            car.setPersons(createVehicleRequest.getPersons());
            car.setPerformance(createVehicleRequest.getPerformance());
            car.setVehicleStatus(createVehicleRequest.getVehicleStatus());

            try {
                return ResponseEntity.ok(vehicleManagementService.addNewVehicle(car));
            } catch (ExistingVehiclePlateNumber existingVehiclePlateNumber) {
                LOGGER.error(existingVehiclePlateNumber);
                return ResponseEntity.badRequest().body(null);
            } catch (NotValidPlateNumberFormatException e) {
                LOGGER.error(e);
                return ResponseEntity.badRequest().body(null);
            }
        } else if (createVehicleRequest.getType() == VehicleType.SHIP) {
            Ship ship = new Ship();
            ship.setShipId(createVehicleRequest.getShipId());
            ship.setLength(createVehicleRequest.getLength());
            ship.setWithTrailer(createVehicleRequest.isWithTrailer());
            ship.setId(createVehicleRequest.getId());
            ship.setType(createVehicleRequest.getType());
            ship.setManufacturer(createVehicleRequest.getManufacturer());
            ship.setYearOfManufacture(createVehicleRequest.getYearOfManufacture());
            ship.setRentCost(createVehicleRequest.getRentCost());
            ship.setPersons(createVehicleRequest.getPersons());
            ship.setPerformance(createVehicleRequest.getPerformance());
            ship.setVehicleStatus(createVehicleRequest.getVehicleStatus());

            try {
                return ResponseEntity.ok(vehicleManagementService.addNewVehicle(ship));
            } catch (ExistingVehiclePlateNumber existingVehiclePlateNumber) {
                LOGGER.error(existingVehiclePlateNumber);
                return ResponseEntity.badRequest().body(null);
            } catch (NotValidPlateNumberFormatException e) {
                LOGGER.error(e);
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            Vehicle other = new Vehicle();

            other.setId(createVehicleRequest.getId());
            other.setType(createVehicleRequest.getType());
            other.setManufacturer(createVehicleRequest.getManufacturer());
            other.setYearOfManufacture(createVehicleRequest.getYearOfManufacture());
            other.setRentCost(createVehicleRequest.getRentCost());
            other.setPersons(createVehicleRequest.getPersons());
            other.setPerformance(createVehicleRequest.getPerformance());
            other.setVehicleStatus(createVehicleRequest.getVehicleStatus());

            try {
                return ResponseEntity.ok(vehicleManagementService.addNewVehicle(other));
            } catch (ExistingVehiclePlateNumber existingVehiclePlateNumber) {
                LOGGER.error(existingVehiclePlateNumber);
                return ResponseEntity.badRequest().body(null);
            } catch (NotValidPlateNumberFormatException e) {
                LOGGER.error(e);
                return ResponseEntity.badRequest().body(null);
            }
        }
    }

    @RequestMapping(value = "/getVehicle/{vehicleId}", method = RequestMethod.GET)
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long vehicleId) {
        try {
            return ResponseEntity.ok(vehicleManagementService.getVehicleById(vehicleId));
        } catch (VehicleNotFoundException e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<Vehicle>> getVehicleByFilterOptions(@RequestBody SearchVehicleRequest searchVehicleRequest) {
        try {
            return ResponseEntity.ok(vehicleManagementService.getVehicleByFilterOptions(searchVehicleRequest));
        } catch (NotValidPlateNumberFormatException e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> getVehicles() {
        List<Vehicle> vehicles = vehicleManagementService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody UpdateVehicleRequest updateVehicleRequest) {
        try {
            return ResponseEntity.ok(vehicleManagementService.updateVehicle(updateVehicleRequest));
        } catch (VehicleNotFoundException e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest().body(null);
        } catch (NotValidPlateNumberFormatException e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeVehicle(@RequestBody Vehicle vehicle) {
        try {
            vehicleManagementService.removeVehicle(vehicle);
        } catch (VehicleNotFoundException e) {
            LOGGER.error(e);
        }
    }
}