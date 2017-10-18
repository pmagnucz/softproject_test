package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.VehicleEntity;
import hu.uni.miskolc.iit.model.*;

/**
 * Created by rozgo on 2017. 10. 18..
 */

public interface ShipMapper {
    VehicleEntity mapModelToEntity(Ship ship);

    Ship mapEntityToModel(VehicleEntity shipEntity);
}