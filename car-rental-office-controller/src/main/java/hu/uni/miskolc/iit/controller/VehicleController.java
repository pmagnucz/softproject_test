package hu.uni.miskolc.iit.controller;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.VehicleManagementService;

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
    private VehicleManagementService vehicleManagementService;

    @Autowired
    public void setVehicleManagementService(VehicleManagementService vehicleManagementService) {
        this.vehicleManagementService = vehicleManagementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Vehicle> addNewVehice(@RequestBody CreateVehicleRequest createVehicleRequest){
        if (createVehicleRequest.getType() == VehichleType.CAR){
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

            return ResponseEntity.ok(vehicleManagementService.addNewVehicle(car));
        }
        else if(createVehicleRequest.getType() == VehichleType.SHIP){
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

            return ResponseEntity.ok(vehicleManagementService.addNewVehicle(ship));
        }
        else {
            Vehicle other = new Vehicle();

            other.setId(createVehicleRequest.getId());
            other.setType(createVehicleRequest.getType());
            other.setManufacturer(createVehicleRequest.getManufacturer());
            other.setYearOfManufacture(createVehicleRequest.getYearOfManufacture());
            other.setRentCost(createVehicleRequest.getRentCost());
            other.setPersons(createVehicleRequest.getPersons());
            other.setPerformance(createVehicleRequest.getPerformance());
            other.setVehicleStatus(createVehicleRequest.getVehicleStatus());

            return ResponseEntity.ok(vehicleManagementService.addNewVehicle(other));
        }
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public ResponseEntity<Vehicle> getVehicleById(@RequestBody int id){
        return ResponseEntity.ok(vehicleManagementService.getVehicleById(id));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Vehicle>> getVehicleByFilterOptions(@RequestBody SearchVehicleRequest searchVehicleRequest) {
        List<Vehicle> vehicles = vehicleManagementService.getVehicleByFilterOptions(searchVehicleRequest);
        return ResponseEntity.ok(vehicles);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> getVehicles(){
        List<Vehicle> vehicles = vehicleManagementService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle){
        Vehicle result = null;
        try {
            result = vehicleManagementService.updateVehicle(vehicle);
        } catch (Exception e) {}

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removeVehicle(@RequestBody Vehicle vehicle){
        vehicleManagementService.removeVehicle(vehicle);
    }
}