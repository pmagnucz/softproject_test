package hu.uni.miskolc.iit.repositories;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rozgonyi on 2017. 10. 06..
 */

public interface VehicleRepository extends CrudRepository<VehicleEntity, Long> {

}
