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
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) throws UserTypeDoesNotExistException{
        try {
            return ResponseEntity.ok(userManagementService.createNewUser(createUserRequest));
        } catch (UserTypeDoesNotExistException e) {
            LOGGER.error("UserTypeNotExistException raised at createUser", e);
            throw e;
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
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) throws UserNotFoundException {
        try {
            userManagementService.deleteUser(user);
        } catch (UserNotFoundException e) {
            LOGGER.error("UserNotFoundException raised at deleteUser", e);
            throw e;
        }
    }

    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UserNotFoundException{
        try {
            User user = userManagementService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            LOGGER.error("UserNotFoundException raised at getUserById", e);
            throw e;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUserByFilterOptions(@RequestBody SearchUserRequest searchUserRequest) throws UserNotFoundException {
        try {
            return ResponseEntity.ok(userManagementService.getUserByFilterOptions(searchUserRequest));
        } catch (UserNotFoundException e) {
            LOGGER.error("UserNotFoundException raised at getUserBySearchRequest", e);
            throw e;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok(userManagementService.countUser());
    }
}
