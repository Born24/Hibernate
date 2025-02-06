package jm.task.core.jdbc;

import org.hibernate.SessionFactory;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = Util.getSessionFactory();
            UserDao userDao = new UserDaoHibernateImpl();

            userDao.createUsersTable();
            System.out.println("Таблица 'users' успешно создана (или уже существует).");
            System.out.println();

            userDao.saveUser("Name1", "LastName1", (byte) 20);
            userDao.saveUser("Name2", "LastName2", (byte) 25);
            userDao.saveUser("Name3", "LastName3", (byte) 31);
            userDao.saveUser("Name4", "LastName4", (byte) 38);
            System.out.println("Пользователи успешно добавлены.");
            System.out.println();

            userDao.removeUserById(1);
            System.out.println("Пользователь с ID=1 успешно удалён.");
            System.out.println();

            List<User> users = userDao.getAllUsers();
            System.out.println("Список всех пользователей:");
            System.out.println();
            for (User user : users) {
                System.out.println(user);
            }

            userDao.cleanUsersTable();
            System.out.println("Таблица 'users' успешно очищена.");
            System.out.println();

            userDao.dropUsersTable();
            System.out.println("Таблица 'users' успешно удалена.");
            System.out.println();

        } catch (Exception e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}

