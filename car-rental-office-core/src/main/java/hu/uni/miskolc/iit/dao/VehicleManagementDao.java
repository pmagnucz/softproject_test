package hu.uni.miskolc.iit.dao;

import hu.uni.miskolc.iit.model.Vehicle;

import java.util.Collection;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public interface VehicleManagementDao {
    Vehicle addVehicle(Vehicle user);
    Vehicle getVehicleById(Long id);
    Collection<Vehicle> getVehicles();
    void deleteVehicle(Vehicle user);
    boolean exists(Vehicle id);
    void clear();
}
