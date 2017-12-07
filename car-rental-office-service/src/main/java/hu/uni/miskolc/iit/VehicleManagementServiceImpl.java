package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.exception.NotSupportedVehicleTypeException;
import hu.uni.miskolc.iit.exception.VehicleNotFoundException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.VehicleManagementService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public class VehicleManagementServiceImpl implements VehicleManagementService {
    private VehicleManagementDao vehicleManagementDao;

    public VehicleManagementServiceImpl(VehicleManagementDao vehicleManagementDao) {
        this.vehicleManagementDao = vehicleManagementDao;
    }

    @Override
    public Vehicle addNewVehicle(Vehicle vehicle) throws NotSupportedVehicleTypeException {
        // Common check, all required field has value, the value fit to the regex
        if (vehicle.getType() == VehicleType.CAR) {
            Vehicle storedCar = vehicleManagementDao.addVehicle(vehicle);
            return storedCar;
            // CAR
        } else if (vehicle.getType() == VehicleType.SHIP) {
            Vehicle storedShip = vehicleManagementDao.addVehicle(vehicle);
            return storedShip;
            // SHIP
        } else {
            throw new NotSupportedVehicleTypeException("Not supported vehicle type.");
        }
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleManagementDao.getVehicleById(id);
    }

    @Override
    public List<Vehicle> getVehicleByFilterOptions(SearchVehicleRequest searchVehicleRequest) {
        List<Vehicle> vehicleList = (List) vehicleManagementDao.getVehicles();
        List<Vehicle> requestedVehicles = new ArrayList<Vehicle>();

        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getType() == searchVehicleRequest.getType()
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
        return (List<Vehicle>) vehicleManagementDao.getVehicles();
    }

    @Override
    public Vehicle updateVehicle(UpdateVehicleRequest updateVehicleRequest) throws VehicleNotFoundException{
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        if (updateVehicleRequest.isCar()) {
            //Car
            Car oldCar = (Car) vehicleManagementDao.getVehicleById(updateVehicleRequest.getId());
            oldCar.setPlateNumber(updateVehicleRequest.getPlateNumber());
            oldCar.setVehicleIdentificationNumber(updateVehicleRequest.getVehicleIdentificationNumber());
            oldCar.setManufacturer(updateVehicleRequest.getManufacturer());
            oldCar.setPerformance(updateVehicleRequest.getPerformance());
            oldCar.setPersons(updateVehicleRequest.getPersons());
            oldCar.setRentCost(updateVehicleRequest.getRentCost());
            oldCar.setType(updateVehicleRequest.getType());
            oldCar.setVehicleStatus(updateVehicleRequest.getVehicleStatus());
            oldCar.setYearOfManufacture(updateVehicleRequest.getYearOfManufacture());
            oldCar.setDrawBar(updateVehicleRequest.isDrawBar());

            return vehicleManagementDao.addVehicle(oldCar);
        } else if (updateVehicleRequest.isShip()) {
            //Ship
            Ship oldShip = (Ship) vehicleManagementDao.getVehicleById(updateVehicleRequest.getId());
            oldShip.setLength(updateVehicleRequest.getLength());
            oldShip.setWithTrailer(updateVehicleRequest.isWithTrailer());
            oldShip.setManufacturer(updateVehicleRequest.getManufacturer());
            oldShip.setPerformance(updateVehicleRequest.getPerformance());
            oldShip.setPersons(updateVehicleRequest.getPersons());
            oldShip.setRentCost(updateVehicleRequest.getRentCost());
            oldShip.setType(updateVehicleRequest.getType());
            oldShip.setVehicleStatus(updateVehicleRequest.getVehicleStatus());
            oldShip.setYearOfManufacture(updateVehicleRequest.getYearOfManufacture());

            return vehicleManagementDao.addVehicle(oldShip);
        }
        throw new VehicleNotFoundException("Vehicle not found with the given parameters! ID = " + updateVehicleRequest.getId());
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        vehicleManagementDao.deleteVehicle(vehicle);
    }
}
