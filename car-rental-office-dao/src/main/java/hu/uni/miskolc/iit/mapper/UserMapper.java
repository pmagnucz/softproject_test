package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.UserEntity;
import hu.uni.miskolc.iit.model.Company;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
public class UserMapper {
    public static UserEntity mapModelToEntity(User user){
        UserEntity userEntity= new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setAddress(user.getAddress());
        userEntity.setPhoneNumber(user.getPhoneNumber());

        if (user instanceof Customer)
        {
            Customer customer = (Customer)user;
            userEntity.setCustomerId(customer.getUserId());
            userEntity.setDrivingLicenseNumber(customer.getDrivingLicenceNumber());
            userEntity.setYearOfBirth(Integer.toString(customer.getYearOfBirth()));
        } else if (user instanceof Company)
        {
            Company company = (Company) user;
            userEntity.setCompanyId(company.getCompanyId());
            userEntity.setRepresentative(company.getRepresentative().getUserId());
            userEntity.setBillingAddress(company.getBillingAddress());
        }

        return userEntity;
    }

    public static List<UserEntity> mapUserListToUserEntityList(List<User> userList){
        List<UserEntity> userEntities = new ArrayList<>(userList.size());
        for (User user : userList)
        {
            userEntities.add(mapModelToEntity(user));
        }
        return userEntities;
    }

    public static User mapUserEntityToModel(UserEntity userEntity){
        if (userEntity.getCustomerId() != null && !userEntity.getCustomerId().isEmpty())
        {
            // If customerId exist the entity is Customer
            Customer customer = new Customer();
            customer.setPhoneNumber(userEntity.getPhoneNumber());
            customer.setUserName(userEntity.getUserName());
            customer.setAddress(userEntity.getAddress());
            customer.setUserId(userEntity.getCustomerId());
            customer.setYearOfBirth(Integer.valueOf(userEntity.getYearOfBirth()));
            customer.setDrivingLicenceNumber(userEntity.getDrivingLicenseNumber());
            return customer;
        } else if (userEntity.getCompanyId() != null && !userEntity.getCompanyId().isEmpty())
        {
            // If companyId exist the entity is Company
            Company company = new Company();
            company.setPhoneNumber(userEntity.getPhoneNumber());
            company.setUserName(userEntity.getUserName());
            company.setAddress(userEntity.getAddress());
            company.setCompanyId(userEntity.getCompanyId());
            company.setBillingAddress(userEntity.getBillingAddress());
            Customer representative = new Customer();
            representative.setUserId(userEntity.getRepresentative());
            company.setRepresentative(representative);
            return company;
        } else {
            User user = new User();
            user.setPhoneNumber(userEntity.getPhoneNumber());
            user.setUserName(userEntity.getUserName());
            user.setAddress(userEntity.getAddress());
            return user;
        }
    }

    public static List<User> mapUserEntitiesToModelList(List<UserEntity> userEntities){
        List<User> userList = new ArrayList<>(userEntities.size());
        for (UserEntity userEntity : userEntities) {
            userList.add(mapUserEntityToModel(userEntity));
        }
        return userList;

    }
}