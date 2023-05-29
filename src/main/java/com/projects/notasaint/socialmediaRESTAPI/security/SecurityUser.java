package com.projects.notasaint.socialmediaRESTAPI.security;

import com.projects.notasaint.socialmediaRESTAPI.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class SecurityUser implements org.springframework.security.core.userdetails.UserDetails {
    @Serial
    private static final long serialVersionUID = 0L;
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return 
    }

    @Override
    public String getPassword() {
        return Arrays.toString(this.user.getPassword());
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isNonLocked();
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
