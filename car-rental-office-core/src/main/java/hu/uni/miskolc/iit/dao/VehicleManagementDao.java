package hu.uni.miskolc.iit.dao;

import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.model.Vehicle;

import java.util.Collection;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public interface VehicleManagementDao {
    Vehicle addVehicle(Vehicle user);
    Vehicle getVehicleById(Long id) throws VehicleNotFoundException;
    Collection<Vehicle> getVehicles();
    void deleteVehicle(Vehicle user) throws VehicleNotFoundException;
    boolean exists(Vehicle id);
    void clear();
}
