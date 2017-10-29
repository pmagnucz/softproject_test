package hu.uni.miskolc.iit.controller;

import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.service.RentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rozgo on 2017. 10. 18..
 */

@RestController
@RequestMapping(value = "/rent")
public class RentController {
    private RentManagementService rentManagementService;

    @Autowired
    public void setRentManagementService(RentManagementService rentManagementService) {
        this.rentManagementService = rentManagementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Rent> createRent(@RequestBody Rent rent) throws WrongRentDateException, NegativeValueException, UserNotFoundException, RentIdAlreadyExistsException, VehicleNotFoundException, RentWrongTotalFeeException {
        return ResponseEntity.ok(rentManagementService.addNewRent(rent));
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public ResponseEntity<Rent> getRentById(@RequestBody int id){
        return ResponseEntity.ok(rentManagementService.getRentById(id));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<Rent>> getRentByFilterOptions(@RequestBody SearchRentRequest searchRentRequest){
        return null;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Rent>> getAllRent(){
        return ResponseEntity.ok(rentManagementService.getRents());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Rent> updateRent(@RequestBody Rent rent) {
        Rent result = null;
        try {
            result = rentManagementService.updateRent(rent);
        } catch (Exception e) {}

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteRent(@RequestBody Rent rent) throws NegativeValueException, RentWrongTotalFeeException, WrongRentDateException, UserNotFoundException, VehicleNotFoundException, RentNotFoundException {
        rentManagementService.removeRent(rent);
    }
}