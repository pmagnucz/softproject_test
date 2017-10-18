package hu.uni.miskolc.iit.controller;
import hu.uni.miskolc.iit.model.SearchVehicleRequest;
import hu.uni.miskolc.iit.model.VehichleType;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.model.VehicleStatusType;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Vehicle addNewVehice(@RequestBody Vehicle vehicle){
        Vehicle returned = vehicleManagementService.addNewVehicle(vehicle);
        return returned;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Vehicle getVehicleById(@PathVariable int id){
        Vehicle vehicle = vehicleManagementService.getVehicleById(id);
        return vehicle;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Vehicle>> getVehicleByFilterOptions(@RequestBody SearchVehicleRequest searchVehicleRequest) {
        List<Vehicle> vehicles = vehicleManagementService.getVehicleByFilterOptions(searchVehicleRequest);
        return ResponseEntity.ok(vehicles);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> getVehicles(){
        List<Vehicle> vehicles = vehicleManagementService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle){
        Vehicle returned = vehicleManagementService.updateVehicle(vehicle);
        return returned;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeVehicle(@RequestBody Vehicle vehicle){
        vehicleManagementService.removeVehicle(vehicle);
        return "Ok.";
    }
}