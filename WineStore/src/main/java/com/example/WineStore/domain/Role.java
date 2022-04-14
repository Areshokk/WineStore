package com.example.WineStore.domain;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    /**
     * Site customer role.
     */
    USER,

    /**
     * Site administrator role.
     *
     */
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}