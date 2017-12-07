package hu.uni.miskolc.iit.dao;

import hu.uni.miskolc.iit.model.User;

import java.util.Collection;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public interface UserManagementDao {
    User addUser(User user);
    User getUserById(Long id);
    Collection<User> getUsers();
    void deleteUser(User user);
    boolean exists(User id);
    void clear();
}
