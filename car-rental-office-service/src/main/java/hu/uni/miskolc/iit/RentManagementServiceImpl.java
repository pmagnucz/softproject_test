package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.RentEntity;
import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.mapper.RentMapper;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.repositories.RentRepository;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.repositories.VehicleRepository;
import hu.uni.miskolc.iit.service.RentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltme on 2017.10.17..
 */

@Service
public class RentManagementServiceImpl implements RentManagementService {

    private RentRepository rentRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;
    private RentMapper rentMapper;

    @Autowired
    public RentManagementServiceImpl(RentRepository rentRepository,UserRepository userRepository,VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Rent addNewRent(Rent rent) throws WrongRentDateException, NegativeValueException, RentWrongTotalFeeException, RentIdAlreadyExistsException, UserNotFoundException, VehicleNotFoundException {
        if(rentRepository.exists(Long.valueOf(rent.getId()))) {
            throw new RentIdAlreadyExistsException(String.valueOf(rent.getId()));
        }
        validate(rent);
        RentEntity rentEntity = rentMapper.mapModelToEntity(rent);
        Rent storedRent = rentMapper.mapEntityToModel(this.rentRepository.save(rentEntity));
        return storedRent;
    }

    @Override
    public Rent getRentById(int id) {
        return rentMapper.mapEntityToModel(rentRepository.findOne(Long.valueOf(id)));
    }

    @Override
    public List<Rent> getRentByFilterOptions(SearchRentRequest searchRentRequest) {
        List<Rent> rentList = rentMapper.mapRentEntityListToModelList(((List)this.rentRepository.findAll()));
        List<Rent> requestedRents = new ArrayList<Rent>();

        for(Rent rent : rentList) {
            if(rent.getCustomerId() == searchRentRequest.getCustomerId()
                    || rent.getCompanyId() == searchRentRequest.getCompanyId()
                    || rent.getVehicleId() == searchRentRequest.getVehicleId()
                    || rent.getStartDate() == searchRentRequest.getStartDate()
                    || rent.getEndDate() == searchRentRequest.getEndDate()) {
                requestedRents.add(rent);
            }
        }

        return requestedRents;
    }

    @Override
    public List<Rent> getRents() {
        return RentMapper.mapRentEntityListToModelList((List<RentEntity>)rentRepository.findAll());
    }

    @Override
    public Rent updateRent(Rent rent) throws UserNotFoundException, NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, VehicleNotFoundException, RentNotFoundException {
        if(!rentRepository.exists(Long.valueOf(rent.getId()))){
            throw new RentNotFoundException(String.valueOf(rent.getId()));
        }
        validate(rent);
        RentEntity mappedEntity = rentMapper.mapModelToEntity(rent);

        this.rentRepository.findOne(Long.valueOf(rent.getId())).setCustomerId(mappedEntity.getCustomerId());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setCompanyId(mappedEntity.getCompanyId());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setVehicleId(mappedEntity.getVehicleId());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setStartDate(mappedEntity.getStartDate());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setEndDate(mappedEntity.getEndDate());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setDurationExtendable(mappedEntity.getDurationExtendable());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setExtendedHours(mappedEntity.getExtendedHours());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setKmUsed(mappedEntity.getKmUsed());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setDayFee(mappedEntity.getDayFee());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setKmFee(mappedEntity.getKmFee());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setOtherFee(mappedEntity.getOtherFee());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setTotalFee(mappedEntity.getTotalFee());
        this.rentRepository.findOne(Long.valueOf(rent.getId())).setPaid(mappedEntity.getPaid());

        Rent updatedRent = rentMapper.mapEntityToModel(this.rentRepository.save(rentRepository.findOne(Long.valueOf(rent.getId()))));
        return updatedRent;
    }

    @Override
    public void removeRent(Rent rent) throws UserNotFoundException, NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, VehicleNotFoundException, RentNotFoundException {
        if(!rentRepository.exists(Long.valueOf(rent.getId()))) {
            throw new RentNotFoundException(String.valueOf(rent.getId()));
        }
        validate(rent);
        this.rentRepository.delete(Long.valueOf(rent.getId()));
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

        if(!userRepository.exists(Long.valueOf(rent.getCustomerId())) && !userRepository.exists(Long.valueOf(rent.getCompanyId()))){
            if(rent.getCustomerId() > 0) {
                throw new UserNotFoundException("Customer with Id: " + rent.getCustomerId() + " does not exist.");
            } else if (rent.getCompanyId() > 0){
                throw new UserNotFoundException("Company with Id: " + rent.getCompanyId() + " does not exist.");
            } else {
                throw new UserNotFoundException("User Ids wrong: Customer - " + rent.getCustomerId() + ",Company - " + rent.getCompanyId() + ".");
            }
        }

        if(!vehicleRepository.exists(rent.getId())){
            throw new VehicleNotFoundException("Vehicle with Id: " + rent.getVehicleId() + " does not exist.");
        }
    }
}
