package hu.uni.miskolc.iit.controller;

import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.model.CreateUserRequest;
import hu.uni.miskolc.iit.model.SearchUserRequest;
import hu.uni.miskolc.iit.model.UpdateUserRequest;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@RestController
@RequestMapping(value = "/user")
public class UserManagementController {
    private UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) throws UserTypeDoesNotExistException{
        return ResponseEntity.ok(userManagementService.createNewUser(createUserRequest));
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userManagementService.getUsers());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequest user) throws UserNotFoundException {
        User result = userManagementService.updateUser(user);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) throws UserNotFoundException {
        userManagementService.deleteUser(user);
    }

    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UserNotFoundException{
        return ResponseEntity.ok(userManagementService.getUserById(userId));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUserByFilterOptions(@RequestBody SearchUserRequest searchUserRequest) throws UserNotFoundException {
        return ResponseEntity.ok(userManagementService.getUserByFilterOptions(searchUserRequest));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok(userManagementService.countUser());
    }
}
