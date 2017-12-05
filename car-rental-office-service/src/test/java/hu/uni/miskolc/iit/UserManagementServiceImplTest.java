package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDaoImpl;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class UserManagementServiceImplTest {
    private UserManagementService userManagementService;
// TODO UserManagementServiceImplTest
    @Before
    public void before()
    {
        UserManagementDao userManagementDao = new UserManagementDaoImpl(new File("src/test/resources/database.json"));
        userManagementService = new UserManagementServiceImpl(userManagementDao);
    }

    @Test
    public void readDatabase()
    {
        Assert.assertNotNull(userManagementService.getUsers());
    }

    @Test
    public void createNewUser() throws Exception {

    }

    @Test
    public void getUserById() throws Exception {

    }

    @Test
    public void getUserByFilterOptions() throws Exception {

    }

    @Test
    public void getUsers() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void countUser() throws Exception {

    }
}