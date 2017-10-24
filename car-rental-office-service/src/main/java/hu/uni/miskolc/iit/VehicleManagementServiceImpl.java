package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.Car;
import hu.uni.miskolc.iit.model.SearchVehicleRequest;
import hu.uni.miskolc.iit.model.Ship;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import hu.uni.miskolc.iit.entity.VehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */

@Service
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
        return VehicleMapper.mapEntityListToModelList((List<VehicleEntity>)vehicleRepository.findAll());
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        List<VehicleEntity> entities = (List<VehicleEntity>) vehicleRepository.findAll();
        for(VehicleEntity vehicleEntity : entities){
            if(Long.toString(vehicleEntity.getId()).equals(vehicle.getId())){
                if (vehicle instanceof Car){
                    //Car
                    Car car = (Car) vehicle;
                    vehicleEntity.setPlateNumber(car.getPlateNumber());
                    vehicleEntity.setVehicleIdentificationNumber(car.getVehicleIdentificationNumber());
                    vehicleEntity.setManufacturer(car.getManufacturer());
                    vehicleEntity.setPerformance(Double.toString(car.getPerformance()));
                    vehicleEntity.setPersons(Integer.toString(car.getPersons()));
                    vehicleEntity.setRentCost(Double.toString(car.getRentCost()));
                    vehicleEntity.setType(car.getType().toString());
                    vehicleEntity.setVehicleStatus(car.getVehicleStatus().toString());
                    vehicleEntity.setYearOfManufacture(car.getYearOfManufacture().toString());
                    vehicleEntity.setDrawBar(Boolean.toString(car.isDrawBar()));

                    VehicleEntity updatedVehicleCar = vehicleRepository.save(vehicleEntity);
                    return VehicleMapper.mapEntityToModel(updatedVehicleCar);
                } else if (vehicle instanceof Ship){
                    //Ship
                    Ship ship = (Ship) vehicle;
                    vehicleEntity.setLength(Double.toString(ship.getLength()));
                    vehicleEntity.setWithTrailer(Boolean.toString(ship.isWithTrailer()));
                    vehicleEntity.setManufacturer(ship.getManufacturer());
                    vehicleEntity.setPerformance(Double.toString(ship.getPerformance()));
                    vehicleEntity.setPersons(Integer.toString(ship.getPersons()));
                    vehicleEntity.setRentCost(Double.toString(ship.getRentCost()));
                    vehicleEntity.setType(ship.getType().toString());
                    vehicleEntity.setVehicleStatus(ship.getVehicleStatus().toString());
                    vehicleEntity.setYearOfManufacture(ship.getYearOfManufacture().toString());

                    VehicleEntity updatedVehicleShip = vehicleRepository.save(vehicleEntity);
                    return VehicleMapper.mapEntityToModel(updatedVehicleShip);
                }
            }
        }

        return null;
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        VehicleEntity vehicleEntity = VehicleMapper.mapModelToEntity(vehicle);
        vehicleRepository.delete(vehicleEntity);
    }
}