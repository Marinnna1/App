package ru.javaschool.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.javaschool.entities.User;
import ru.javaschool.services.AuthService;


@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthService userService;

    @Override
    public SecurityUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(login);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + login + "' not found");
        }

        return SecurityUserDetails.fromUserEntityToMyUserDetails(user);
    }
}

