package hu.uni.miskolc.iit.controller;
import hu.uni.miskolc.iit.model.SearchVehicleRequest;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.service.VehicleManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addNewVehice(@RequestBody Vehicle vehicle){
        Vehicle returned = vehicleManagementService.addNewVehicle(vehicle);
        return returned.toString() + "car added.";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getVehicleById(@PathVariable int id){
        Vehicle vehicle = vehicleManagementService.getVehicleById(id);
        return vehicle.toString();
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Vehicle>> getVehicleByFilterOptions(@RequestBody SearchVehicleRequest searchVehicleRequest) {
        List<Vehicle> vehicles = vehicleManagementService.getVehicleByFilterOptions(searchVehicleRequest);
        return ResponseEntity.ok(vehicles);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getVehicles(){
        List<Vehicle> vehicles = vehicleManagementService.getVehicles();
        return vehicles.toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateVehicle(@RequestBody Vehicle vehicle){
        Vehicle returned = vehicleManagementService.updateVehicle(vehicle);
        return vehicle.toString() + "changed to: " + returned.toString();
    }

    /*itt 200-as kellene majd küldeni, ha jó lesz, ha nem valami hibát*/
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeVehicle(@RequestBody Vehicle vehicle){
        vehicleManagementService.removeVehicle(vehicle);
        return "Lefutott.";
    }
}