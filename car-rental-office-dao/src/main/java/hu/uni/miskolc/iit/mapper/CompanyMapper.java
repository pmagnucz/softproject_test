package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.CompanyEntity;
import hu.uni.miskolc.iit.model.Company;
import org.mapstruct.Mapper;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Mapper
public interface CompanyMapper {
    CompanyEntity mapModelToEntity(Company company);

    Company mapEntityToModel(CompanyEntity companyEntity);
}
