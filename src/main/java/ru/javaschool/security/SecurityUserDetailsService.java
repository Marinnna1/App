package ru.javaschool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import ru.javaschool.dao.UserDao;
import ru.javaschool.entities.User;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public SecurityUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new SecurityUserDetails(user);
    }

}
