package com.randeng.api.controller.admin;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.controller.dto.user.RegisterRequest;
import com.randeng.api.model.Role;
import com.randeng.api.model.User;
import com.randeng.api.service.RoleService;
import com.randeng.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller("operatorController")
@RequestMapping("admin/operator")
public class OperatorController extends BaseController {

    private static final String ROLE_OPERATOR = "operator";

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody RegisterRequest request) {
        if (StringUtils.isEmpty(request.getUsername())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The username is required for registration.", ErrorCode.REGISTER_MOBILE_NOT_PROVIDED));
        }
        if (StringUtils.isEmpty(request.getName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The name is required for registration.", ErrorCode.REGISTER_NAME_NOT_PROVIDED));
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The password is required for registration.", ErrorCode.REGISTER_PASSWORD_NOT_PROVIDED));
        }
        User user = userService.findByUsername(request.getUsername());
        if (user != null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The username has been used by an existing user.", ErrorCode.REGISTER_MOBILE_USED));
        }

        Role operatorRole = roleService.findByName(ROLE_OPERATOR);

        // find the operator role
        user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Arrays.asList(operatorRole));
        userService.save(user);

        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured("ROLE_admin")
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<User> users = userService.findByRole("operator", new Pageable(pageNumber, pageSize));
        return ResponseEntity.ok(WebResponse.success(users));
    }}
