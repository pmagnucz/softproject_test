package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleMapper {
    public static VehicleEntity mapModelToEntity(Vehicle vehicle) {
        //converting format for Date to String, yearOfManufacture from Vehicle
        DateFormat format = new SimpleDateFormat("yyyy-MM");

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setVehicleId(String.valueOf(vehicle.getId()));
        vehicleEntity.setType(vehicle.getType().toString());
        vehicleEntity.setManufacturer(vehicle.getManufacturer());
        vehicleEntity.setYearOfManufacture(format.format(vehicle.getYearOfManufacture()));
        vehicleEntity.setRentCost(String.valueOf(vehicle.getRentCost()));
        vehicleEntity.setPersons(String.valueOf(vehicle.getPersons()));
        vehicleEntity.setPerformance(String.valueOf(vehicle.getPerformance()));
        vehicleEntity.setVehicleStatus(vehicle.getVehicleStatus().toString());

        if(vehicle instanceof Car) {
            Car car = (Car)vehicle;
            vehicleEntity.setPlateNumber(car.getPlateNumber());
            vehicleEntity.setVehicleIdentificationNumber(car.getVehicleIdentificationNumber());
            vehicleEntity.setDrawBar(String.valueOf(car.isDrawBar()));
        } else if (vehicle instanceof Ship) {
            Ship ship = (Ship)vehicle;
            vehicleEntity.setShipId(ship.getShipId());
            vehicleEntity.setLength(String.valueOf(ship.getLength()));
            vehicleEntity.setWithTrailer(String.valueOf(ship.isWithTrailer()));
        }
        return vehicleEntity;
    }

    public static List<VehicleEntity> mapModelListToEntityList(List<Vehicle> vehicleList) {
        List<VehicleEntity> vehicleEntityList = new ArrayList<>(vehicleList.size());
        for (Vehicle vehicle : vehicleList)
        {
            vehicleEntityList.add(mapModelToEntity(vehicle));
        }
        return vehicleEntityList;
    }

    public static Vehicle mapEntityToModel(VehicleEntity vehicleEntity) {
        //converting String to Date, yearOfManufacture from VehicleEntity
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date yearOfManufactureDate = null;
        try {
            yearOfManufactureDate = format.parse(vehicleEntity.getYearOfManufacture());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(vehicleEntity.getPlateNumber() != null && !vehicleEntity.getPlateNumber().isEmpty()) {
            //vehicle has a PlateNumber means its a Car
            Car car = new Car();
            car.setId(Integer.parseInt(vehicleEntity.getVehicleId()));
            car.setType(VehichleType.CAR);
            car.setManufacturer(vehicleEntity.getManufacturer());
            car.setYearOfManufacture(yearOfManufactureDate);
            car.setRentCost(Double.parseDouble(vehicleEntity.getRentCost()));
            car.setPersons(Integer.parseInt(vehicleEntity.getPersons()));
            car.setPerformance(Double.parseDouble(vehicleEntity.getPerformance()));
            car.setVehicleStatus(VehicleStatusType.valueOf(vehicleEntity.getVehicleStatus()));
            car.setPlateNumber(vehicleEntity.getPlateNumber());
            car.setVehicleIdentificationNumber(vehicleEntity.getVehicleIdentificationNumber());
            car.setDrawBar(Boolean.valueOf(vehicleEntity.getDrawBar()));
            return car;
        } else if(vehicleEntity.getShipId() != null && !vehicleEntity.getShipId().isEmpty()) {
            //vehicle has a ShipId means its a Ship
            Ship ship = new Ship();
            ship.setId(Integer.parseInt(vehicleEntity.getVehicleId()));
            ship.setType(VehichleType.SHIP);
            ship.setManufacturer(vehicleEntity.getManufacturer());
            ship.setYearOfManufacture(yearOfManufactureDate);
            ship.setRentCost(Double.parseDouble(vehicleEntity.getRentCost()));
            ship.setPersons(Integer.parseInt(vehicleEntity.getPersons()));
            ship.setPerformance(Double.parseDouble(vehicleEntity.getPerformance()));
            ship.setVehicleStatus(VehicleStatusType.valueOf(vehicleEntity.getVehicleStatus()));
            ship.setShipId(vehicleEntity.getShipId());
            ship.setLength(Double.parseDouble(vehicleEntity.getLength()));
            ship.setWithTrailer(Boolean.valueOf(vehicleEntity.getWithTrailer()));
            return ship;
        } else {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(Integer.parseInt(vehicleEntity.getVehicleId()));
            vehicle.setType(VehichleType.OTHER);
            vehicle.setManufacturer(vehicleEntity.getManufacturer());
            vehicle.setYearOfManufacture(yearOfManufactureDate);
            vehicle.setRentCost(Double.parseDouble(vehicleEntity.getRentCost()));
            vehicle.setPersons(Integer.parseInt(vehicleEntity.getPersons()));
            vehicle.setPerformance(Double.parseDouble(vehicleEntity.getPerformance()));
            vehicle.setVehicleStatus(VehicleStatusType.valueOf(vehicleEntity.getVehicleStatus()));
            return vehicle;
        }

    }

    public static List<Vehicle> mapEntityListToModelList(List<VehicleEntity> vehicleEntityList)  {
        List<Vehicle> vehicleList = new ArrayList<>(vehicleEntityList.size());
        for (VehicleEntity vehicleEntity : vehicleEntityList) {
            vehicleList.add(mapEntityToModel(vehicleEntity));
        }
        return vehicleList;
    }
}
