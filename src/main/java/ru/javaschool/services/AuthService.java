package ru.javaschool.services;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaschool.entities.User;
import ru.javaschool.enums.Position;

import java.util.HashSet;
import java.util.Objects;


@Service
public class AuthService implements UserDetailsService {

    private static Logger log = Logger.getLogger(AuthService.class.getName());

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;



    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }



    public String findUserPosition(String name, String password) {
        if (userDao.find(name, password, passwordEncoder)) {
            log.info("found user by name and password");
            return userDao.getUserPosition(name, password).toString();
        }
        log.info("not found user by name and password");
        return "wrong";
    }



    public User findUserByUsername(String userName) {
        return userDao.findByUserName(userName);
    }


    public String saveUser(String name, String password, Position position) {
        if(userDao.save(name, passwordEncoder.encode(password), position)) {
            log.info("save new user successfully");
            return position.toString();
        }
        log.info("don't save new user");
        return "wrong";
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User u = userDao.findByUserName(name);
        return new org.springframework.security.core.userdetails.User(u.getName(), u.getPassword(), true, true, true, true, new HashSet<>());
    }
}
