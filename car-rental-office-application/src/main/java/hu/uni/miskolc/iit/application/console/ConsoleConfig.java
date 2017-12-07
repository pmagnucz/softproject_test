package hu.uni.miskolc.iit.application.console;

import hu.uni.miskolc.iit.RentManagementServiceImpl;
import hu.uni.miskolc.iit.UserManagementServiceImpl;
import hu.uni.miskolc.iit.VehicleManagementServiceImpl;
import hu.uni.miskolc.iit.controller.RentController;
import hu.uni.miskolc.iit.controller.UserManagementController;
import hu.uni.miskolc.iit.controller.VehicleController;
import hu.uni.miskolc.iit.dao.*;
import hu.uni.miskolc.iit.service.RentManagementService;
import hu.uni.miskolc.iit.service.UserManagementService;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created by Lenovo11 on 2017. 12. 07..
 */

@Configuration
public class ConsoleConfig {
    private static final String USER_DB_PATH = "userDBpath";
    private static final String VEHICLE_DB_PATH = "vehicleDBpath";
    private static final String RENT_DB_PATH = "rentDBpath";

    @Bean(value = "userManagementController")
    public UserManagementController getUserManagementController(){
        return new UserManagementController(getUserManagementService());
    }

    @Bean
    public UserManagementService getUserManagementService() {
        return new UserManagementServiceImpl(getUserManagementDao());
    }

    @Bean
    public UserManagementDao getUserManagementDao(){
        return new UserManagementDaoImpl(new File(System.getProperty(USER_DB_PATH)));
    }

    // Vehicle beans
    @Bean
    public VehicleController getVehicleController(){
        return new VehicleController(getVehicleManagementService());
    }

    @Bean
    public VehicleManagementService getVehicleManagementService() {
        return new VehicleManagementServiceImpl(getVehicleManagementDao());
    }

    @Bean
    public VehicleManagementDaoImpl getVehicleManagementDao(){
        return new VehicleManagementDaoImpl(new File(System.getProperty(VEHICLE_DB_PATH)));
    }

    // Rent beans
    @Bean
    public RentController getRentController(){
        return new RentController(getRentManagementService());
    }

    @Bean
    public RentManagementService getRentManagementService() {
        return new RentManagementServiceImpl(getRentManagementDao(), getUserManagementDao(), getVehicleManagementDao());
    }

    @Bean
    public RentManagementDao getRentManagementDao(){
        return new RentManagementDaoImpl(new File(System.getProperty(RENT_DB_PATH)));
    }

}
