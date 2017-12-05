package hu.uni.miskolc.iit.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.uni.miskolc.iit.dao.beans.UserDaoBean;
import hu.uni.miskolc.iit.model.Company;
import hu.uni.miskolc.iit.model.Customer;
import hu.uni.miskolc.iit.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public class UserManagementDaoImpl implements UserManagementDao{
    private File database;

    public UserManagementDaoImpl(File file)
    {
        this.database = file;
    }

    @Override
    public User addUser(User user) {
        List<User> users = readDatabase();
        users.add(user);
        writeDatabase(users);
        return readDatabase().get(users.indexOf(user));
    }

    @Override
    public User getUserById(Long id) {
        List<User> users = readDatabase();
        for (User user : users)
        {
            if (user.getId() == id)
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public Collection<User> getUsers() {
        return readDatabase();
    }

    @Override
    public void deleteUser(User user) {
        List<User> users = readDatabase();
        users.remove(user);

        writeDatabase(users);
    }

    @Override
    public boolean exists(User user) {
        List<User> users = readDatabase();
        return users.contains(user);
    }

    @Override
    public void clear() {
        writeDatabase(new ArrayList<User>());
    }

    private List<User> readDatabase(){
        List<User> result = new ArrayList<User>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<UserDaoBean> beans = Arrays.asList(mapper.readValue(database, UserDaoBean[].class));
            for (UserDaoBean bean : beans)
            {
                result.add(bean.extract());
            }
        } catch (IOException e) {
            System.out.println(database.getAbsolutePath());
            e.printStackTrace();
        }
        return result;
    }

    private void writeDatabase(List<User> users)
    {
        List<UserDaoBean> beans = new ArrayList<UserDaoBean>();
        for (User user : users){
            beans.add(convert(user));
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(database, beans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserDaoBean convert(User user)
    {
        UserDaoBean bean = new UserDaoBean();
        bean.setId(user.getId());
        bean.setUserName(user.getUserName());
        bean.setAddress(user.getAddress());
        bean.setPhoneNumber(user.getPhoneNumber());

        if (user instanceof Customer){
            Customer customer = (Customer) user;
            bean.setCustomerId(customer.getUserId());
            bean.setDrivingLicenseNumber(customer.getDrivingLicenceNumber());
            bean.setYearOfBirth(customer.getYearOfBirth());
        } else {
            Company company = (Company) user;
            bean.setCompanyId(company.getCompanyId());
            bean.setRepresentative(company.getRepresentative().getUserId());
            bean.setBillingAddress(company.getBillingAddress());
        }

        return bean;
    }
}
