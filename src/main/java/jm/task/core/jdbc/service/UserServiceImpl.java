package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl userDao;

    public UserServiceImpl() {

        userDao = new UserDaoHibernateImpl();
    }


    public void createUsersTable() {
        userDao.createUsersTable();

    }

    public void dropUsersTable() {
        userDao.dropUsersTable();

    }

    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();

    }


    public void cleanUsersTable() {
        userDao.cleanUsersTable();

    }
}
