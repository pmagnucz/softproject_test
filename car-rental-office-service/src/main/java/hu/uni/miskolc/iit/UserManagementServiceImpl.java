package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.UserEntity;
import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.mapper.UserMapper;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {
    private static final Logger LOGGER = Logger.getLogger(UserManagementServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createNewUser(CreateUserRequest createUserRequest) throws UserTypeDoesNotExistException {
        if (createUserRequest.getUserId() != null && !createUserRequest.getUserId().isEmpty()) {
            Customer customer = new Customer();
            customer.setUserId(createUserRequest.getUserId());
            customer.setYearOfBirth(createUserRequest.getYearOfBirth());
            customer.setDrivingLicenceNumber(createUserRequest.getDrivingLicenceNumber());
            customer.setPhoneNumber(createUserRequest.getPhoneNumber());
            customer.setUserName(createUserRequest.getUserName());
            customer.setAddress(createUserRequest.getAddress());
            UserEntity userEntity = UserMapper.mapModelToEntity(customer);
            return UserMapper.mapUserEntityToModel(userRepository.save(userEntity));
        } else if (createUserRequest.getCompanyId() != null && !createUserRequest.getCompanyId().isEmpty()) {
            Company company = new Company();
            company.setCompanyId(createUserRequest.getCompanyId());
            company.setBillingAddress(createUserRequest.getBillingAddress());
            company.setRepresentative(createUserRequest.getRepresentative());
            company.setPhoneNumber(createUserRequest.getPhoneNumber());
            company.setUserName(createUserRequest.getUserName());
            company.setAddress(createUserRequest.getAddress());
            UserEntity userEntity = UserMapper.mapModelToEntity(company);
            return UserMapper.mapUserEntityToModel(userRepository.save(userEntity));
        } else {
            throw new UserTypeDoesNotExistException("Only Customer and Company user types are allowed.");
        }
    }

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        List<UserEntity> elements = (List<UserEntity>) userRepository.findAll();
        User user = null;

        for (int i = 0; i < elements.size(); i++) {
            if (id == elements.get(i).getId()) {
                user = UserMapper.mapUserEntityToModel(elements.get(i));
            }
        }

        return user;
    }

    @Override
    public List<User> getUserByFilterOptions(SearchUserRequest searchUserRequest) throws UserNotFoundException {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        List<UserEntity> result = new ArrayList<>();
        for (UserEntity userEntity : users) {
            if (userEntity.getUserName().equals(searchUserRequest.getUserName()) ||
                    userEntity.getPhoneNumber().equals(searchUserRequest.getPhoneNumber()) ||
                    userEntity.getAddress().equals(searchUserRequest.getAddress()) ||
                    userEntity.getDrivingLicenseNumber().equals(searchUserRequest.getDrivingLicenceNumber())) {
                result.add(userEntity);
            }
        }

        if (result.isEmpty()) {
            throw new UserNotFoundException("User not found to fit with a given criteria.", searchUserRequest);
        } else {
            return UserMapper.mapUserEntitiesToModelList(result);
        }
    }

    @Override
    public List<User> getUsers() {
        return UserMapper.mapUserEntitiesToModelList((List<UserEntity>) userRepository.findAll());
    }

    @Override
    public User updateUser(UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findOne(updateUserRequest.getId());
        if (userEntity.getUserName().equals(updateUserRequest.getUserName())) {
            userEntity.setAddress(updateUserRequest.getAddress());
            userEntity.setPhoneNumber(updateUserRequest.getPhoneNumber());

            if (updateUserRequest.isCustomer()) {
                //Customer
                userEntity.setCustomerId(updateUserRequest.getUserId());
                userEntity.setDrivingLicenseNumber(updateUserRequest.getDrivingLicenceNumber());
                userEntity.setYearOfBirth(Integer.toString(updateUserRequest.getYearOfBirth()));
                userEntity.setAddress(updateUserRequest.getAddress());
                userEntity.setPhoneNumber(updateUserRequest.getPhoneNumber());
            } else if (updateUserRequest.isCompany()) {
                //Company
                userEntity.setPhoneNumber(updateUserRequest.getPhoneNumber());
                userEntity.setCompanyId(updateUserRequest.getCompanyId());
                userEntity.setBillingAddress(updateUserRequest.getBillingAddress());
                userEntity.setAddress(updateUserRequest.getAddress());
                userEntity.setRepresentative(String.valueOf(updateUserRequest.getRepresentative()));
            }

            UserEntity updatedUser = userRepository.save(userEntity);
            return UserMapper.mapUserEntityToModel(updatedUser);
        }
        throw new UserNotFoundException("User: " + updateUserRequest.toString() + "not found!");
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user.getId());
    }
}