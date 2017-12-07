package hu.uni.miskolc.iit.dao;

import hu.uni.miskolc.iit.model.Rent;

import java.util.Collection;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public interface RentManagementDao {
    Rent createRent(Rent rent);
    Rent getRentById(Long id);
    Collection<Rent> getRents();
    void deleteRent(Rent rent);
    boolean exists(Rent rent);
    void clear();
}
