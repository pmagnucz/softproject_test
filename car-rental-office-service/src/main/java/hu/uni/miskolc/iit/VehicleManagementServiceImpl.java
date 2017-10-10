package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.Car;
import hu.uni.miskolc.iit.model.SearchVehicleRequest;
import hu.uni.miskolc.iit.model.Ship;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import hu.uni.miskolc.iit.entity.VehicleEntity;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public class VehicleManagementServiceImpl implements VehicleManagementService {
     private VehicleMapper vehicleMapper;
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addNewVehicle(Vehicle vehicle) {
        VehicleEntity vehicleEntity = VehicleMapper.mapModelToEntity(vehicle);
        // Common check, all required field has value, the value fit to the regex
        if (vehicle instanceof Car)
        {
            Vehicle storedCar = VehicleMapper.mapEntityToModel(vehicleRepository.save(vehicleEntity));
            return storedCar;
            // CAR
        } else if (vehicle instanceof Ship)
        {
            Vehicle storedShip = VehicleMapper.mapEntityToModel(vehicleRepository.save(vehicleEntity));
            return storedShip;
            // SHIP
        } else {
            throw new NotSupportedVehicleTypeException(vehicle.getType().toString());
        }
    }

    @Override
    public Vehicle getVehicleById(int id) {
        List<VehicleEntity> elements = (List<VehicleEntity>) vehicleRepository.findAll();
        Vehicle vehicle = null;
        for (int i = 0; i < elements.size(); i++) {
            if (id == elements.get(i).getId()) {
                vehicle = VehicleMapper.mapEntityToModel(elements.get(i));
            }
        }
        return vehicle;
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
        VehicleEntity vehicleEntity = VehicleMapper.mapModelToEntity(vehicle);
        vehicleRepository.delete(vehicleEntity);
    }
}