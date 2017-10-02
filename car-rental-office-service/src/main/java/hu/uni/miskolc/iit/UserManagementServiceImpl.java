package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.mapper.CompanyMapper;
import hu.uni.miskolc.iit.mapper.CustomerMapper;
import hu.uni.miskolc.iit.model.SearchUserRequest;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.repositories.CompanyRepository;
import hu.uni.miskolc.iit.repositories.CustomerRepository;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {
    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;
    private CompanyMapper companyMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public UserManagementServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.companyMapper = Mappers.getMapper(CompanyMapper.class);
        this.customerMapper = Mappers.getMapper(CustomerMapper.class);
    }

    @Override
    public User createNewUser(User user) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getUserByFilterOptions(SearchUserRequest searchUserRequest) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }
}
