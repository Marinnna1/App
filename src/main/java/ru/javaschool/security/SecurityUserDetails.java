package ru.javaschool.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.javaschool.entities.User;


import java.util.Collection;
import java.util.Collections;

// Entity for spring security so in easy words just my User class but for Spring Security
public class SecurityUserDetails implements UserDetails{

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static SecurityUserDetails fromUserEntityToMyUserDetails(User userEntity) {
        SecurityUserDetails c = new SecurityUserDetails();
        c.login = userEntity.getName();
        c.password = userEntity.getPassword();
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getPosition()));
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
