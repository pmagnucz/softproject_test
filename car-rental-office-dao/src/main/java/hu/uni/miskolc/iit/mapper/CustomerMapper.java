package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.UserEntity;
import hu.uni.miskolc.iit.model.Customer;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
public interface CustomerMapper {
    UserEntity mapModelToEntity(Customer customer);

    Customer mapEntityToModel(UserEntity customerEntity);
}
