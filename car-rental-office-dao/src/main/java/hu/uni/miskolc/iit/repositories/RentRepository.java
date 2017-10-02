package hu.uni.miskolc.iit.repositories;

import hu.uni.miskolc.iit.entity.RentEntity;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<RentEntity, Long> {
}
