package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.RentManagementDao;
import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.dao.VehicleManagementDao;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.model.Vehicle;
import hu.uni.miskolc.iit.service.RentManagementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltme on 2017.10.17..
 */

public class RentManagementServiceImpl implements RentManagementService {

    private RentManagementDao rentManagementDao;
    private UserManagementDao userManagementDao;
    private VehicleManagementDao vehicleManagementDao;

    public RentManagementServiceImpl(RentManagementDao rentManagementDao, UserManagementDao userManagementDao, VehicleManagementDao vehicleManagementDao) {
        this.rentManagementDao = rentManagementDao;
        this.userManagementDao = userManagementDao;
        this.vehicleManagementDao = vehicleManagementDao;
    }

    @Override
    public Rent addNewRent(Rent rent) throws WrongRentDateException, NegativeValueException, RentWrongTotalFeeException,
            RentIdAlreadyExistsException, UserNotFoundException, VehicleNotFoundException {
        if(rent.getId() != null) {
            if(rentManagementDao.exists(rent)) {
                throw new RentIdAlreadyExistsException(String.valueOf(rent.getId()));
            }
        }

        validate(rent);
        Rent storedRent = rentManagementDao.createRent(rent);
        return storedRent;
    }

    @Override
    public Rent getRentById(Long id) {
        return rentManagementDao.getRentById(id);
    }

    @Override
    public List<Rent> getRentByFilterOptions(SearchRentRequest searchRentRequest) {
        List<Rent> rentList = (List<Rent>) rentManagementDao.getRents();
        List<Rent> requestedRents = new ArrayList<Rent>();

        for(Rent rent : rentList) {
            if(rent.getCustomerId() == searchRentRequest.getCustomerId()
                    || rent.getCompanyId() == searchRentRequest.getCompanyId()
                    || rent.getVehicleId() == searchRentRequest.getVehicleId()
                    || rent.getStartDate().equals(searchRentRequest.getStartDate())
                    || rent.getEndDate().equals(searchRentRequest.getEndDate())) {
                requestedRents.add(rent);
            }
        }

        return requestedRents;
    }

    @Override
    public List<Rent> getRents() {
        return (List<Rent>) rentManagementDao.getRents();
    }

    @Override
    public Rent updateRent(Rent rent) throws UserNotFoundException, NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, VehicleNotFoundException, RentNotFoundException {
        if(!rentManagementDao.exists(rent)){
            throw new RentNotFoundException(String.valueOf(rent.getId()));
        }
        validate(rent);
        return rentManagementDao.createRent(rent);
    }

    @Override
    public void removeRent(Rent rent) throws UserNotFoundException, NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, VehicleNotFoundException, RentNotFoundException {
        if(!rentManagementDao.exists(rent)) {
            throw new RentNotFoundException(String.valueOf(rent.getId()));
        }
        validate(rent);
        this.rentManagementDao.deleteRent(rent);
    }

    @Override
    public int rentCount() {
        return Math.toIntExact(rentManagementDao.getRents().size());
    }

    @Override
    public void validate(Rent rent) throws NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, UserNotFoundException, VehicleNotFoundException {
        String negativeValueExceptionMessage = "";
        boolean negativeValueException = false;

        if(rent.getExtendedHours() < 0){
            negativeValueExceptionMessage.concat(" ,extendedHours");
            negativeValueException = true;
        }
        if(rent.getKmUsed() < 0) {
            negativeValueExceptionMessage.concat(" ,kmUsed");
            negativeValueException = true;
        }
        if (rent.getKmFee() < 0) {
            negativeValueExceptionMessage.concat(" ,,kmFee");
            negativeValueException = true;
        }
        if (rent.getDayFee() < 0) {
            negativeValueExceptionMessage.concat(" ,dayFee");
            negativeValueException = true;
        }
        if (rent.getOtherFee() < 0) {
            negativeValueExceptionMessage.concat(" ,otherFee");
            negativeValueException = true;
        }
        if (rent.getTotalFee() < 0) {
            negativeValueExceptionMessage.concat(" ,totalFee");
            negativeValueException = true;
        }

        if (negativeValueException == true) {
                throw new NegativeValueException(negativeValueExceptionMessage);
        }

        if(rent.getStartDate().after(rent.getEndDate())) {
            throw new WrongRentDateException("EndDate cannot be before startDate.");
        }

        if(rent.getTotalFee() != (rent.getDayFee() + rent.getKmFee() + rent.getOtherFee())) {
            throw new RentWrongTotalFeeException(String.valueOf(rent.getTotalFee() + ", should be: " + (rent.getDayFee()+rent.getKmFee()+rent.getOtherFee())));
        }

        if (userManagementDao.getUserById(rent.getCustomerId()) == null && userManagementDao.getUserById(rent.getCompanyId()) == null) {
            if(rent.getCustomerId() > 0) {
                throw new UserNotFoundException("Customer with Id: " + rent.getCustomerId() + " does not exist.");
            } else if (rent.getCompanyId() > 0){
                throw new UserNotFoundException("Company with Id: " + rent.getCompanyId() + " does not exist.");
            } else {
                throw new UserNotFoundException("User Ids wrong: Customer - " + rent.getCustomerId() + ",Company - " + rent.getCompanyId() + ".");
            }
        }

        if(vehicleManagementDao.getVehicleById(rent.getVehicleId()) == null) {
            throw new VehicleNotFoundException("Vehicle with Id: " + rent.getVehicleId() + " does not exist.");
        }
    }
}
