package hu.uni.miskolc.iit.service;

import hu.uni.miskolc.iit.exception.*;
import hu.uni.miskolc.iit.model.Rent;
import hu.uni.miskolc.iit.model.SearchRentRequest;

import java.util.List;

public interface RentManagementService {

    /**
     * Létrehoz egy új kölcsön objektumot
     * @param rent
     * @return Rent object
     * */
    Rent addNewRent(Rent rent) throws WrongRentDateException, NegativeValueException, RentWrongTotalFeeException, RentIdAlreadyExistsException, UserNotFoundException, VehicleNotFoundException;

    /**
     * A paraméterben adott azonosítójú kölcsön objektummal tér vissza
     * @param id
     * @return Rent object
     * */
    Rent getRentById(Long id);

    /**
     * A szűrési paramétereknek megfelelő kölcsönöket adja vissza
     * @param searchRentRequest filter options (customerId,companyId,vehicleId,startDate,endDate)
     * @return List of Rent object
     * */
    List<Rent> getRentByFilterOptions(SearchRentRequest searchRentRequest);

    /**
     * Visszaadja a kölcsön objektumok listáját
     * @return List of Rents
     * */
    List<Rent> getRents();

    /**
     * Egy kölcsön tulajdonságait módosítja.
     * @param rent object
     * @return Rent object
     * */
    Rent updateRent(Rent rent) throws UserNotFoundException, NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, VehicleNotFoundException, RentNotFoundException;

    /**Törli a paraméterben kapott kölcsönt.
     * @param rent
     */
    void removeRent(Rent rent) throws UserNotFoundException, NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, VehicleNotFoundException, RentNotFoundException;


    /**
     * Returns the number of rents
     * @return int
     */
    int rentCount();

    /**Testing a Rent for Exceptions
     * @param rent
     */
    void validate(Rent rent) throws NegativeValueException, WrongRentDateException, RentWrongTotalFeeException, UserNotFoundException, VehicleNotFoundException;
}

