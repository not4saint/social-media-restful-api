package com.projects.notasaint.socialmediaRESTAPI.security;

import com.projects.notasaint.socialmediaRESTAPI.models.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class SecurityUser implements org.springframework.security.core.userdetails.UserDetails {
    @Serial
    private static final long serialVersionUID = 0L;
    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(users.getRole().getRole().name()));
    }

    @Override
    public String getPassword() {
        return this.users.getPassword();
    }

    @Override
    public String getUsername() {
        return this.users.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.users.isNonLocked();
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
