package com.randeng.api.controller;

import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.controller.dto.user.ChangePasswordRequest;
import com.randeng.api.controller.dto.user.LoginRequest;
import com.randeng.api.controller.dto.user.LoginResponse;
import com.randeng.api.controller.dto.user.RegisterRequest;
import com.randeng.api.model.User;
import com.randeng.api.security.TokenUtils;
import com.randeng.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

@Controller("userController")
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "tokenUtils")
    private TokenUtils tokenUtils;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a user.
     * @param request the register request
     * @return the response
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> register(@RequestBody RegisterRequest request) {
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
            return ResponseEntity.badRequest().body(WebResponse.error("The mobile has been used by an existing user.", ErrorCode.REGISTER_MOBILE_USED));
        }

        user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);

        return ResponseEntity.ok(WebResponse.success(true));
    }

    /**
     * Login. Since the backend is fully stateless, here the jwt token is returned for further use.
     * @param loginRequest the login request
     * @return the response with jwt token
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.findByUsername(loginRequest.getUsername());
        String jwtToken = tokenUtils.generateToken(user);
        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setUser(user);
        return ResponseEntity.ok(WebResponse.success(response));
    }

    @RequestMapping(value = "password", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        if (!Objects.equals(request.getUserId(), currentUser().getId())) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.find(request.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            request.getOriginalPassword()
        ));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userService.update(user);
        return ResponseEntity.ok(WebResponse.success());
    }
}
