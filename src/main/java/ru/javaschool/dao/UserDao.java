package ru.javaschool.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javaschool.entities.User;
import ru.javaschool.enums.Position;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;

import java.util.List;


@Repository
public class UserDao {

    private static Logger log = Logger.getLogger(UserDao.class.getName());


    public boolean save(String name, String password, Position position) {
        try( Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(new User(name, password, position));
            tx1.commit();
            log.info("save new user in database");
            return true;
        }
        catch (Exception e) {
            log.warn("invalid user data for saving in database");
            return false;
        }

    }



    public boolean find(String name, String password) {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User as user where user.name =\'" + name + "\'" + " and password = \'" + password + "\'").list();
        return !users.isEmpty();
    }

    public Position getUserPosition(String name, String password) {
        User currentUser = (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User as user where user.name =\'" + name + "\'" + " and user.password = \'" + password + "\'").list().get(0);
        log.info("get position for current user from table \"users\"");
        return currentUser.getPosition();
    }

    public User findByUserName(String name) {
        User currentUser = (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User as user where user.name =\'" + name + "\'").list().get(0);
        return currentUser;
    }



}
