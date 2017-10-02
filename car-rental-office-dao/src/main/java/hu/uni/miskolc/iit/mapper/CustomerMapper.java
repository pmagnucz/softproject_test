package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.CustomerEntity;
import hu.uni.miskolc.iit.model.Customer;
import org.mapstruct.Mapper;

import java.util.Date;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Mapper
public interface CustomerMapper {
    CustomerEntity mapModelToEntity(Customer customer);

    Customer mapEntityToModel(CustomerEntity customerEntity);
}
