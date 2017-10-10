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

    @PostMapping(value = "/search")
    public ResponseEntity<List<Vehicle>> getVehicleByFilterOptions(@RequestBody SearchVehicleRequest searchVehicleRequest) {
        List<Vehicle> vehicles = vehicleManagementService.getVehicleByFilterOptions(searchVehicleRequest);
        return ResponseEntity.ok(vehicles);
    }
}

