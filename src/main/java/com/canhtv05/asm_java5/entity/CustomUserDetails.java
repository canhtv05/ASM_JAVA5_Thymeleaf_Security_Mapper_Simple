package com.canhtv05.asm_java5.entity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private final String ten;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String ten) {
        super(username, password, authorities);
        this.ten = ten;
    }
}
