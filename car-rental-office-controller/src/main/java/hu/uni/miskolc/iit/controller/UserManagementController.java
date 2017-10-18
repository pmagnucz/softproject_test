package hu.uni.miskolc.iit.controller;
import hu.uni.miskolc.iit.model.Customer;

import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.UserManagementService;
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
    private UserManagementService userManagementService;

    @Autowired
    public void setUserManagementService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest){
        if (createUserRequest.getUserId() != null && !createUserRequest.getUserId().isEmpty()){
            Customer customer = new Customer();
            customer.setUserId(createUserRequest.getUserId());
            customer.setYearOfBirth(createUserRequest.getYearOfBirth());
            customer.setDrivingLicenceNumber(createUserRequest.getDrivingLicenceNumber());
            customer.setPhoneNumber(createUserRequest.getPhoneNumber());
            customer.setUserName(createUserRequest.getUserName());
            customer.setAddress(createUserRequest.getAddress());

            return ResponseEntity.ok(userManagementService.createNewUser(customer));
        } else {
            Company company = new Company();
            company.setCompanyId(createUserRequest.getCompanyId());
            company.setBillingAddress(createUserRequest.getBillingAddress());
            company.setRepresentative(createUserRequest.getRepresentative());
            company.setPhoneNumber(createUserRequest.getPhoneNumber());
            company.setUserName(createUserRequest.getUserName());
            company.setAddress(createUserRequest.getAddress());

            return ResponseEntity.ok(userManagementService.createNewUser(company));
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userManagementService.getUsers());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User result = null;
        try {
           result = userManagementService.updateUser(user);
        } catch (UserNotFoundException e) {}

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user){
        userManagementService.deleteUser(user);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public ResponseEntity<User> getUserById(@RequestBody long id){
        User user = userManagementService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUserByFilterOptions(@RequestBody SearchUserRequest searchUserRequest){
        return null;
    }
}
