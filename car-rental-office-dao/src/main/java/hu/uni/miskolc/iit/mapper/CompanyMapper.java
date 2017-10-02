package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.UserEntity;
import hu.uni.miskolc.iit.model.Company;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
public interface CompanyMapper {
    UserEntity mapModelToEntity(Company company);

    Company mapEntityToModel(UserEntity companyEntity);
}
