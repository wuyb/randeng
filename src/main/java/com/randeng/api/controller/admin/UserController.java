package com.randeng.api.controller.admin;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.User;
import com.randeng.api.service.DonationService;
import com.randeng.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("adminUserController")
@RequestMapping("admin/user")
public class UserController extends BaseController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "donationServiceImpl")
    private DonationService donationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) Long userId,
                           @RequestParam(required = false) String mobile,
                           @RequestParam(required = false) String name) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (userId != null) {
            pageable.getFilters().add(Filter.eq("id", userId));
        }
        if (mobile != null) {
            pageable.getFilters().add(Filter.like("mobile", mobile));
        }
        if (name != null) {
            pageable.getFilters().add(Filter.like("name", name));
        }
        Page<User> expensePage = userService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(expensePage));
    }

    @RequestMapping(value = "{userId}/donations", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @PathVariable Long userId) {
        User user = userService.find(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Pageable pageable = new Pageable(pageNumber, pageSize);
        pageable.getFilters().add(Filter.eq("user.id", userId));
        return ResponseEntity.ok(WebResponse.success(donationService.findPage(pageable)));
    }

}
