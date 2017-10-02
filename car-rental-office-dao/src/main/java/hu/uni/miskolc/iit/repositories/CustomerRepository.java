package hu.uni.miskolc.iit.repositories;

import hu.uni.miskolc.iit.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Repository
public interface CustomerRepository  extends CrudRepository<CustomerEntity, Long> {
}
