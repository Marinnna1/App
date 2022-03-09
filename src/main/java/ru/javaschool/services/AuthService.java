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
public class AuthService {


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
            return userDao.getUserPosition(name, password).toString();
        }
        return "wrong";
    }



    public User findUserByUsername(String userName) {
        return userDao.findByUserName(userName);
    }


    public String saveUser(String name, String password, Position position) {
        if(userDao.save(name, passwordEncoder.encode(password), position)) {
            return position.toString();
        }
        return "wrong";
    }


}
