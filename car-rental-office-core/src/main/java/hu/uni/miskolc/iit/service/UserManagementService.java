package hu.uni.miskolc.iit.service;

import hu.uni.miskolc.iit.exception.UserNotFoundException;
import hu.uni.miskolc.iit.exception.UserTypeDoesNotExistException;
import hu.uni.miskolc.iit.model.CreateUserRequest;
import hu.uni.miskolc.iit.model.SearchUserRequest;
import hu.uni.miskolc.iit.model.UpdateUserRequest;
import hu.uni.miskolc.iit.model.User;

import java.util.List;


/**
 * Created by rozgonyi on 2017. 09. 27..
 */
public interface UserManagementService {
    /**
     * Létrehoz egy új felhasználót.
     * @param createUserRequest
     * @return User object
     * */
    User createNewUser(CreateUserRequest createUserRequest) throws UserTypeDoesNotExistException;
    /**
     * A paraméterben megadott id-jú felhasználóval tér vissza.
     * @param id
     * @return User object
     * */
    User getUserById(long id) throws UserNotFoundException;

    /**
     * A szűrési paramétereknek megfelelő felhasználót adja vissza.
     * @param searchUserRequest filter options ()
     * @return List of User objects
     * */
    List<User> getUserByFilterOptions(SearchUserRequest searchUserRequest) throws UserNotFoundException;

    /**
     * Az összes felhasználót vissza adja.
     * @return List of User objects
     * */
    List<User> getUsers();

    /**
     * Egy felhasználó tulajdonságait módosítja.
     * @param user
     * @return User object
     * */
    User updateUser(UpdateUserRequest user) throws UserNotFoundException;

    /**Törli a paraméterben kapott felhasználót.
     * @param user
     */
    void deleteUser(User user) throws UserNotFoundException;
}
