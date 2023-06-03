package com.projects.notasaint.socialmediaRESTAPI.util;

import com.projects.notasaint.socialmediaRESTAPI.models.User;
import com.projects.notasaint.socialmediaRESTAPI.security.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public User getAuthenticate() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        return user.getUser();
    }
}
