package hu.uni.miskolc.iit.repositories;

import hu.uni.miskolc.iit.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
