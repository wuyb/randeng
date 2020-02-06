package com.randeng.api.controller.user;

import com.randeng.api.common.Order;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.controller.dto.user.LeaderboardResponse;
import com.randeng.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller("userLeaderboardController")
@RequestMapping("user/leaderboard")
public class LeaderboardController extends BaseController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> search(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        pageable.setOrderProperty("donationAmount");
        pageable.setOrderDirection(Order.Direction.desc);
        LeaderboardResponse response = new LeaderboardResponse();
        response.setSelf(currentUser());
        response.setUsers(userService.findPage(pageable));
        response.setSelfPosition(userService.getPosition(currentUser()));
        return ResponseEntity.ok(WebResponse.success(response));
    }

}
