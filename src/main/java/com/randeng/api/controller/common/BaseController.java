package com.randeng.api.controller.common;

import com.randeng.api.model.User;
import com.randeng.api.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;

public abstract class BaseController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    protected User currentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByMobile(username);
    }
}
