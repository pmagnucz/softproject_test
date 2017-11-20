package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.exception.NotValidPlateNumberFormatException;
import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.mapper.VehicleMapper;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */

@Service
public class VehicleManagementServiceImpl implements VehicleManagementService {
    private VehicleRepository vehicleRepository;

    public VehicleManagementServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Autowired
    public void VehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle addNewVehicle(Vehicle vehicle) {
        VehicleEntity vehicleEntity = VehicleMapper.mapModelToEntity(vehicle);
        // Common check, all required field has value, the value fit to the regex
        if (vehicle.getType() == VehicleType.CAR) {
            Vehicle storedCar = VehicleMapper.mapEntityToModel(vehicleRepository.save(vehicleEntity));
            return storedCar;
            // CAR
        } else if (vehicle.getType() == VehicleType.SHIP) {
            Vehicle storedShip = VehicleMapper.mapEntityToModel(vehicleRepository.save(vehicleEntity));
            return storedShip;
            // SHIP
        } else {
            throw new NotSupportedVehicleTypeException(vehicle.getType().toString());
        }
    }

    @Override
    public Vehicle getVehicleById(Long id) {
                return VehicleMapper.mapEntityToModel(vehicleRepository.findOne(id));
    }

    @Override
    public List<Vehicle> getVehicleByFilterOptions(SearchVehicleRequest searchVehicleRequest) {
        List<Vehicle> vehicleList = VehicleMapper.mapEntityListToModelList(((List)this.vehicleRepository.findAll()));
        List<Vehicle> requestedVehicles = new ArrayList<Vehicle>();

        for(Vehicle vehicle : vehicleList) {
            if(vehicle.getType() == searchVehicleRequest.getType()
                    || vehicle.getManufacturer() == searchVehicleRequest.getManufacturer()
                    || vehicle.getYearOfManufacture() == searchVehicleRequest.getYearOfManufacture()
                    || vehicle.getRentCost() == searchVehicleRequest.getRentCost()) {
                requestedVehicles.add(vehicle);
            }
        }

        return requestedVehicles;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return VehicleMapper.mapEntityListToModelList((List<VehicleEntity>) vehicleRepository.findAll());
    }

    @Override
    public Vehicle updateVehicle(UpdateVehicleRequest updateVehicleRequest) throws VehicleNotFoundException{
        VehicleEntity vehicleEntity = vehicleRepository.findOne(updateVehicleRequest.getId());
            if (updateVehicleRequest.isCar()) {
                //Car
                vehicleEntity.setPlateNumber(updateVehicleRequest.getPlateNumber());
                vehicleEntity.setVehicleIdentificationNumber(updateVehicleRequest.getVehicleIdentificationNumber());
                vehicleEntity.setManufacturer(updateVehicleRequest.getManufacturer());
                vehicleEntity.setPerformance(Double.toString(updateVehicleRequest.getPerformance()));
                vehicleEntity.setPersons(Integer.toString(updateVehicleRequest.getPersons()));
                vehicleEntity.setRentCost(Double.toString(updateVehicleRequest.getRentCost()));
                vehicleEntity.setType(updateVehicleRequest.getType().toString());
                vehicleEntity.setVehicleStatus(updateVehicleRequest.getVehicleStatus().toString());
                vehicleEntity.setYearOfManufacture(updateVehicleRequest.getYearOfManufacture().toString());
                vehicleEntity.setDrawBar(Boolean.toString(updateVehicleRequest.isDrawBar()));

                VehicleEntity updatedVehicleCar = vehicleRepository.save(vehicleEntity);
                return VehicleMapper.mapEntityToModel(updatedVehicleCar);
            } else if (updateVehicleRequest.isShip()) {
                //Ship
                vehicleEntity.setLength(Double.toString(updateVehicleRequest.getLength()));
                vehicleEntity.setWithTrailer(Boolean.toString(updateVehicleRequest.isWithTrailer()));
                vehicleEntity.setManufacturer(updateVehicleRequest.getManufacturer());
                vehicleEntity.setPerformance(Double.toString(updateVehicleRequest.getPerformance()));
                vehicleEntity.setPersons(Integer.toString(updateVehicleRequest.getPersons()));
                vehicleEntity.setRentCost(Double.toString(updateVehicleRequest.getRentCost()));
                vehicleEntity.setType(updateVehicleRequest.getType().toString());
                vehicleEntity.setVehicleStatus(updateVehicleRequest.getVehicleStatus().toString());
                vehicleEntity.setYearOfManufacture(updateVehicleRequest.getYearOfManufacture().toString());

                VehicleEntity updatedVehicleShip = vehicleRepository.save(vehicleEntity);
                return VehicleMapper.mapEntityToModel(updatedVehicleShip);
            }
        throw new VehicleNotFoundException("Vehicle not found with the given parameters! ID = " + updateVehicleRequest.getId());
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        vehicleRepository.delete(vehicle.getId());
    }
}
