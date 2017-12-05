package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.UserEntity;
import hu.uni.miskolc.iit.mapper.UserMapper;
import hu.uni.miskolc.iit.model.*;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


public class UserManagementServiceImplTest {
    private UserManagementService userManagementService;
    private UserRepository userRepository;
    private User user;
    private Customer customer;
    private Company company;
    
    @Before
    public void setUp() throws Exception {
        userRepository = mock(UserRepository.class);
        userManagementService = new UserManagementServiceImpl(userRepository);
        user = new User();
        customer = new Customer();
        
        
        user.setId(1L);
        user.setPhoneNumber("+363231231231");
        user.setAddress("Miskolc");
        user.setUserName("Jóska István");
        
        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId(1L);
        customer.setYearOfBirth(1990);
        customer.setDrivingLincenceNumber("21213565");
           
    }
    
    @After
     public void tearDown() throws Exception {
     }
    
    @Test
    public void addNewUserCustomer() throws Exception {
        customer = new Customer();

        customer.setId(1L);
        customer.setPhoneNumber("+363231231231");
        customer.setAddress("Miskolc");
        customer.setUserName("Jóska István");
        customer.setUserId(1L);
        customer.setYearOfBirth(1990);
        customer.setDrivingLincenceNumber("21213565");

        UserEntity mockedUserEntity = UserMapper.mapModelToEntity(customer);

        expect(userRepository.save(anyObject(UserEntity.class))).andReturn(mockedUserEntity);

        replay(userRepository);

        User actual = userManagementService.addNewUser(customer);
        assertEquals(customer, actual);
}
    
    @Test
    public void addNewUserCompany() throws Exception {
        company = new Company();

        
        company.setId(2L);
        company.setPhoneNumber("+363231231231");
        company.setAddress("Miskolc);
        company.setDrivingLicenceNumber("21213565");
        company.setCompanyId(1L);
        company.setBillingAddress("Debrecen");
        

        UserEntity mockedUserEntity = UserMapper.mapModelToEntity(company);
        expect(userRepository.save(anyObject(UserEntity.class))).andReturn(mockedUserEntity);
        expect(userRepository.exists(anyLong())).andReturn(true);

        replay(userRepository);

        User actual = userManagementService.addNewUser(company);

        assertEquals(company, actual);

}
    @Test
    public void getUserById() throws Exception {
        UserEntity mockEntity = UserMapper.mapModelToEntity(customer);

        expect(userRepository.findOne(anyLong())).andReturn(mockEntity);

        replay(userRepository);

        User actual = userManagementService.getUserById(2L);
        assertEquals(customer,actual);
}
    @Test
    public void getUserByFilterOptions() throws Exception {
        
        Company companyModel = new Company();
        companyModel.setId(2L);
        companyModel.setPhoneNumber("+363231231231");
        companyModel.setAddress("Miskolc);
        companyModel.setDrivingLicenceNumber("21213565");
        companyModel.setCompanyId(1L);
        companyModel.setBillingAddress("Debrecen");

        SearchUserRequest searchUserRequest =
                new SearchUserRequest("Jóska István","+363744746","Miskolc","646445465");

        List<User> users = new ArrayList<>();
        users.add(customer);
        users.add(companyModel);

        List<UserEntity> expectedEntities = UserMapper.mapModelListToEntityList(users);

        expect(userRepository.findAll()).andReturn(expectedEntities);

        replay(userRepository);

        List<User> expected = new ArrayList<>();
        expected.add(companyModel);
        List<User> actual = userManagementService.getUserByFilterOptions(searchUserRequest);

        assertEquals(expected,actual);
}


    // TODO: implementálni kell!!
}
