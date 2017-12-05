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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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


    // TODO: implementálni kell!!
}
