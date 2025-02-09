package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users (" +
            "id INT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(50), " +
            "lastName VARCHAR(50), " +
            "age INT)";

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS users";
    private static final String TRUNCATE_TABLE_QUERY = "TRUNCATE TABLE users";

    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                session.createNativeQuery(CREATE_TABLE_QUERY).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                session.createNativeQuery(DROP_TABLE_QUERY).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);

                if (user != null) {
                    session.delete(user);
                } else {
                    System.out.println("Пользователь с ID=" + id + " не найден.");
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                session.createSQLQuery(TRUNCATE_TABLE_QUERY).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

}

