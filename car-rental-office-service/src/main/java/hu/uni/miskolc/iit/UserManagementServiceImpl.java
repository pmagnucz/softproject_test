package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.entity.UserEntity;
import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.WrongUserIdFormatException;
import hu.uni.miskolc.iit.mapper.UserMapper;
import hu.uni.miskolc.iit.model.SearchUserRequest;
import hu.uni.miskolc.iit.model.User;
import hu.uni.miskolc.iit.repositories.UserRepository;
import hu.uni.miskolc.iit.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pmagnucz on 2017. 09. 28..
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {
    private UserRepository userRepository;

    @Autowired
    public UserManagementServiceImpl( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createNewUser(User user) {
        UserEntity userEntity = UserMapper.mapModelToEntity(user);
        User storedUser = UserMapper.mapUserEntityToModel(userRepository.save(userEntity));
        return storedUser;
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
        UserEntity userEntity = UserMapper.mapModelToEntity(user);
        userRepository.delete(userEntity);
    }


}