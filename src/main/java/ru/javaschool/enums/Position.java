package ru.javaschool.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Position implements GrantedAuthority {
    DOCTOR,
    NURSE;

    @Override
    public String getAuthority() {
        return name();
    }
}
