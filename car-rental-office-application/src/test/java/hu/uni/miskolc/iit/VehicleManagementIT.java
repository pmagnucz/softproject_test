package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.controller.VehicleController;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.VehicleManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by rozgonyi on 2017. 11. 25..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class VehicleManagementIT {

    private VehicleController controller;

    @Autowired
    private VehicleRepository repository;

    @Before
    public void setUp(){
        VehicleManagementService service = new VehicleManagementServiceImpl(repository);
        controller = new VehicleController(service);
    }

    @After
    public void tearDown() throws Exception{
        repository.deleteAll();
    }

    @Test
    public void createVehicleTest() {

    }
}