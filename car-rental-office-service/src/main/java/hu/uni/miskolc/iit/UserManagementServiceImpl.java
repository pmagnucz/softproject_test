package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.dao.UserManagementDao;
import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
public class UserManagementServiceImpl implements UserManagementService {
    private UserManagementDao userManagementDao;

    public UserManagementServiceImpl(UserManagementDao userManagementDao) {
        this.userManagementDao = userManagementDao;
    }

    @Override
    public User createNewUser(CreateUserRequest createUserRequest) throws UserTypeDoesNotExistException {
        if (createUserRequest.getUserId() != null && !createUserRequest.getUserId().isEmpty()) {
            Customer customer = new Customer();
            if (createUserRequest.getId() != null){
                customer.setId(createUserRequest.getId());
            }
            customer.setUserId(createUserRequest.getUserId());
            customer.setYearOfBirth(createUserRequest.getYearOfBirth());
            customer.setDrivingLicenceNumber(createUserRequest.getDrivingLicenceNumber());
            customer.setPhoneNumber(createUserRequest.getPhoneNumber());
            customer.setUserName(createUserRequest.getUserName());
            customer.setAddress(createUserRequest.getAddress());
            return userManagementDao.addUser(customer);
        } else if (createUserRequest.getCompanyId() != null && !createUserRequest.getCompanyId().isEmpty()) {
            Company company = new Company();
            if (createUserRequest.getId() != null){
                company.setId(createUserRequest.getId());
            }
            company.setCompanyId(createUserRequest.getCompanyId());
            company.setBillingAddress(createUserRequest.getBillingAddress());
            company.setRepresentative(createUserRequest.getRepresentative());
            company.setPhoneNumber(createUserRequest.getPhoneNumber());
            company.setUserName(createUserRequest.getUserName());
            company.setAddress(createUserRequest.getAddress());
            return userManagementDao.addUser(company);
        } else {
            throw new UserTypeDoesNotExistException("Only Customer and Company user types are allowed.");
        }
    }

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        User result = userManagementDao.getUserById(id);
        if (result == null)
        {
            throw new UserNotFoundException("User not found with the given id: " + id);
        } else {
            return result;
        }
    }


    @Override
    public List<User> getUserByFilterOptions(SearchUserRequest searchUserRequest) throws UserNotFoundException {
        List<User> userList = (List<User>) userManagementDao.getUsers();
        List<User> result = new ArrayList<>();
        
        for (User user : userList) {
            if (user.getUserName().equals(searchUserRequest.getUserName()) ||
                    user.getPhoneNumber().equals(searchUserRequest.getPhoneNumber()) ||
                    user.getAddress().equals(searchUserRequest.getAddress())) {
                result.add(user);
            }
        }
        if (result.isEmpty()) {
            throw new UserNotFoundException("User not found to fit with a given criteria.", searchUserRequest);
        } else {
            return result;
        }
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userManagementDao.getUsers();
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        User user = userManagementDao.getUserById(updateUserRequest.getId());
        if (updateUserRequest.isCustomer()){
            Customer customer = (Customer) user;
            if (updateUserRequest.getUserId() != null && !updateUserRequest.getUserId().isEmpty())
            {
                customer.setUserId(updateUserRequest.getUserId());
            }
            if (updateUserRequest.getAddress() != null && !updateUserRequest.getAddress().isEmpty())
            {
                customer.setAddress(updateUserRequest.getAddress());
            }
            if (updateUserRequest.getDrivingLicenceNumber() != null && !updateUserRequest.getDrivingLicenceNumber().isEmpty())
            {
                customer.setDrivingLicenceNumber(updateUserRequest.getDrivingLicenceNumber());
            }
            if (updateUserRequest.getPhoneNumber() != null && !updateUserRequest.getPhoneNumber().isEmpty())
            {
                customer.setPhoneNumber(updateUserRequest.getPhoneNumber());
            }
            if (updateUserRequest.getUserName() != null && !updateUserRequest.getUserName().isEmpty())
            {
                customer.setUserName(updateUserRequest.getUserName());
            }
            return customer;
        } else if (updateUserRequest.isCompany()){
            Company company = (Company) user;
            if (updateUserRequest.getUserName() != null && !updateUserRequest.getUserName().isEmpty())
            {
                company.setUserName(updateUserRequest.getUserName());
            }
            if (updateUserRequest.getAddress() != null && !updateUserRequest.getAddress().isEmpty())
            {
                company.setAddress(updateUserRequest.getAddress());
            }
            if (updateUserRequest.getPhoneNumber() != null && !updateUserRequest.getPhoneNumber().isEmpty())
            {
                company.setPhoneNumber(updateUserRequest.getPhoneNumber());
            }
            if (updateUserRequest.getCompanyId() != null && !updateUserRequest.getCompanyId().isEmpty())
            {
                company.setCompanyId(updateUserRequest.getCompanyId());
            }
            if (updateUserRequest.getBillingAddress() != null && !updateUserRequest.getBillingAddress().isEmpty())
            {
                company.setBillingAddress(updateUserRequest.getBillingAddress());
            }
            if (updateUserRequest.getRepresentative() != null)
            {
                company.setRepresentative(updateUserRequest.getRepresentative());
            }

            return company;
        }
        throw new UserNotFoundException("The requested user not found! UserId = " + updateUserRequest.getId());
    }

    @Override
    public void deleteUser(User user) throws UserNotFoundException {
        if (userManagementDao.getUserById(user.getId()) == null)
        {
            throw new UserNotFoundException("User: " + user.toString() + "not found!");
        }
        userManagementDao.deleteUser(user);
    }

    @Override
    public int countUser() {
        return userManagementDao.getUsers().size();
    }
}