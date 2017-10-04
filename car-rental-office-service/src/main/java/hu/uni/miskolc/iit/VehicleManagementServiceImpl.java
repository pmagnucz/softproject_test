package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.model.Car;
import hu.uni.miskolc.iit.model.SearchVehicleRequest;
import hu.uni.miskolc.iit.model.Ship;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.service.VehicleManagementService;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public class VehicleManagementServiceImpl implements VehicleManagementService {
    // private VehicleMapper vehicleMapper;

    @Override
    public Vehicle addNewVehicle(Vehicle vehicle) {
        // Common check, all required field has value, the value fit to the regex
        if (vehicle instanceof Car)
        {
            // CAR
        } else if (vehicle instanceof Ship)
        {
            // SHIP
        } else {
            throw new NotSupportedVehicleTypeException(vehicle.getType().toString());
        }

        return null;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return null;
    }

    @Override
    public List<Vehicle> getVehicleByFilterOptions(SearchVehicleRequest searchVehicleRequest) {
        return null;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return null;
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {

    }
}