package hu.uni.miskolc.iit.service;

import hu.uni.miskolc.iit.model.SearchVehicleRequest;
import hu.uni.miskolc.iit.model.Vehicle;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public interface VehicleManagementService {
    /**
     * Létrehoz egy új jármű objektumot
     * @param vehicle
     * @return Vehicle object
     * */
    Vehicle addNewVehicle(Vehicle vehicle);

    /**
     * A paraméterben adott azonosítójú jármű objektummal tér vissza
     * @param id
     * @return Vehicle object
     * */
    Vehicle getVehicleById(int id);

    /**
     * A szűrési paramétereknek megfelelő járműveket adja vissza
     * @param searchVehicleRequest filter options (gyártmány, típus, évjárat, ár)
     * @return List of Vehicle objects
     * */
    List<Vehicle> getVehicleByFilterOptions(SearchVehicleRequest searchVehicleRequest);

    /**
     * Az összes járművet vissza adja
     * @return List of Vehicle objects
     * */
    List<Vehicle> getVehicles();

    /**
     * Egy jármű tulajdonságait módosítja.
     * @param vehicle object
     * @return Vehicle object
     * */
    Vehicle updateVehicle(Vehicle vehicle);

    /**Törli a paraméterben kapott járművet.
     * @param vehicle
     */
    void removeVehicle(Vehicle vehicle);
}
