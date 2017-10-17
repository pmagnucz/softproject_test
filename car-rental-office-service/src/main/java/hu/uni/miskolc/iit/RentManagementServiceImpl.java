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
        return null;
    }

    @Override
    public Rent getRentById(int id) {
        return null;
    }

    @Override
    public List<Rent> getRentByFilterOptions(SearchRentRequest searchRentRequest) {
        return null;
    }

    @Override
    public List<Rent> getRents() {
        return null;
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
