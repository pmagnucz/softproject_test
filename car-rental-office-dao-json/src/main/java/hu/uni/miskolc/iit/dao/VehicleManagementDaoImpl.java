package hu.uni.miskolc.iit.dao;
import hu.uni.miskolc.iit.model.*;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.uni.miskolc.iit.dao.beans.VehicleDaoBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public class VehicleManagementDaoImpl implements VehicleManagementDao {
    private File database;

    public VehicleManagementDaoImpl(File file)
    {
        this.database = file;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        List<Vehicle> vehicles = readDatabase();
        vehicles.add(vehicle);
        writeDatabase(vehicles);
        return readDatabase().get(vehicles.indexOf(vehicle));
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        List<Vehicle> vehicles = readDatabase();
        for (Vehicle vehicle : vehicles)
        {
            if (vehicle.getId() == id)
            {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public Collection<Vehicle> getVehicles() {
        return readDatabase();
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        List<Vehicle> vehicles = readDatabase();
        vehicles.remove(vehicle);
        writeDatabase(vehicles);
    }

    @Override
    public boolean exists(Vehicle vehicle) {
        List<Vehicle> vehicles = readDatabase();
        return vehicles.contains(vehicle);
    }

    @Override
    public void clear() {
        writeDatabase(new ArrayList<Vehicle>());
    }

    private List<Vehicle> readDatabase(){
        List<Vehicle> result = new ArrayList<Vehicle>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<VehicleDaoBean> beans = Arrays.asList(mapper.readValue(database, VehicleDaoBean[].class));
            for (VehicleDaoBean bean : beans)
            {
                result.add(bean.extract());
            }
        } catch (IOException e) {
            System.out.println(database.getAbsolutePath());
            e.printStackTrace();
        }
        return result;
    }

    private void writeDatabase(List<Vehicle> vehicles)
    {
        List<VehicleDaoBean> beans = new ArrayList<VehicleDaoBean>();
        for (Vehicle vehicle : vehicles){
            beans.add(convert(vehicle));
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(database, beans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VehicleDaoBean convert(Vehicle vehicle)
    {
        VehicleDaoBean bean = new VehicleDaoBean();

        if (vehicle instanceof Car){
            Car car = (Car) vehicle;
            bean.setId(car.getId());
            bean.setPlateNumber(car.getPlateNumber());
            bean.setVehicleIdentificationNumber(car.getVehicleIdentificationNumber());
            bean.setDrawBar(car.isDrawBar());
            bean.setType(car.getType());
            bean.setManufacturer(car.getManufacturer());
            bean.setYearOfManufacture(car.getYearOfManufacture());
            bean.setRentCost(car.getRentCost());
            bean.setPersons(car.getPersons());
            bean.setPerformance(car.getPerformance());
            bean.setVehicleStatus(car.getVehicleStatus());

        } else {
            Ship ship = (Ship) vehicle;
            bean.setId(ship.getId());
            bean.setShipId(ship.getShipId());
            bean.setLength(ship.getLength());
            bean.setWithTrailer(ship.isWithTrailer());
            bean.setType(ship.getType());
            bean.setManufacturer(ship.getManufacturer());
            bean.setYearOfManufacture(ship.getYearOfManufacture());
            bean.setRentCost(ship.getRentCost());
            bean.setPersons(ship.getPersons());
            bean.setPerformance(ship.getPerformance());
            bean.setVehicleStatus(ship.getVehicleStatus());
        }

        return bean;
    }
}
