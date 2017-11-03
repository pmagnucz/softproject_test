package hu.uni.miskolc.iit.controller;

import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.model.Customer;

import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@RestController
@RequestMapping(value = "/user")
public class UserManagementController {
    private static final Logger LOGGER = Logger.getLogger(UserManagementController.class);
    private UserManagementService userManagementService;

    @Autowired
    public void setUserManagementService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            return ResponseEntity.ok(userManagementService.createNewUser(createUserRequest));
        } catch (UserTypeDoesNotExistException e) {
            LOGGER.error("UserTypeNotExistException raised at createUser", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userManagementService.getUsers());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest user) {
        try {
            User result = userManagementService.updateUser(user);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException e){
            LOGGER.error("UserNotFoundException raised at updateUser", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) {
        try {
            userManagementService.deleteUser(user);
        } catch (UserNotFoundException e) {
            LOGGER.error("UserNotFoundException raised at deleteUser", e);
        }
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public ResponseEntity<User> getUserById(@RequestBody long id) {
        try {
            User user = userManagementService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            LOGGER.error("UserNotFoundException raised at getUserById", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUserByFilterOptions(@RequestBody SearchUserRequest searchUserRequest) {
        try {
            return ResponseEntity.ok(userManagementService.getUserByFilterOptions(searchUserRequest));
        } catch (UserNotFoundException e) {
            LOGGER.error("UserNotFoundException raised at getUserBySearchRequest", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
