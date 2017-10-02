package hu.uni.miskolc.iit.controller;

import hu.uni.miskolc.iit.model.SearchUserRequest;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@RestController
@RequestMapping("/user")
public class UserManagementController {
    private UserManagementService userManagementService;

    @Autowired
    public void setUserManagementService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<User>> getUserByFilterOptions(@RequestBody SearchUserRequest searchUserRequest){
        List<User> users = userManagementService.getUserByFilterOptions(searchUserRequest);
        return ResponseEntity.ok(users);
    }

}
