package ru.javaschool.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaschool.enums.Position;


@Service
public class AuthService {

    private static Logger log = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private UserDao userDao;

    public AuthService() {
        this.userDao = new UserDao();
    }



    public String findUserPosition(String name, String password) {
        if(userDao.find(name, password)) {
            log.info("found user by name and password");
            return userDao.getUserPosition(name, password).toString();
        }
        log.info("not found user by name and password");
        return "wrong";
    }



    public String saveUser(String name, String password, Position position) {
        if(userDao.save(name, password, position)) {
            log.info("save new user successfully");
            return position.toString();
        }
        log.info("don't save new user");
        return "wrong";
    }



}
