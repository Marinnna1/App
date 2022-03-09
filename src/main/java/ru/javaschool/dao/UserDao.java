package ru.javaschool.dao;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            return true;
        }
        catch (Exception e) {
            log.warn("invalid user data for saving in database");
            return false;
        }

    }



    public boolean find(String name, String password, PasswordEncoder passwordEncoder) {
        try {
            List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User as user where user.name =\'" + name + "\'").list();
            if (!users.isEmpty()) {
                return passwordEncoder.matches(password, users.get(0).getPassword());
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }

    }

    public Position getUserPosition(String name, String password) {
        User currentUser = (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User as user where user.name =\'" + name + "\'").list().get(0);
        return Position.valueOf(currentUser.getPosition());
    }

    public User findByUserName(String name) {
        User currentUser = (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User as user where user.name =\'" + name + "\'").list().get(0);
        return currentUser;
    }


}
