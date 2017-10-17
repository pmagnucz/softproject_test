package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.mapper.RentMapper;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;
import hu.uni.miskolc.iit.repositories.RentRepository;
import hu.uni.miskolc.iit.service.RentManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zsoltme on 2017.10.17..
 */
public class RentManagementServiceImpl implements RentManagementService {

    private RentRepository rentRepository;
    private RentMapper rentMapper;

    @Autowired
    public RentManagementServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Rent addNewRent(Rent rent) {
        this.rentRepository.save(rentMapper.mapModelToEntity(rent));
        return rent;
    }

    @Override
    public Rent getRentById(int id) {
        List<RentEntity> elements = (List<RentEntity>) rentRepository.findAll();
        Rent rent = null;
        for (int i = 0; i < elements.size(); i++) {
            if (id == elements.get(i).getId()) {
                rent = RentMapper.mapEntityToModel(elements.get(i));
            }
        }
        return rent;
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
    public Rent updateRent(Rent rent) {
        return null;
    }

    @Override
    public Rent removeRent(Rent rent) {
        return null;
    }
}
