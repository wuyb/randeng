package com.randeng.api.controller.user;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Order;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.controller.dto.UserDonationStatsResponse;
import com.randeng.api.model.Donation;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.User;
import com.randeng.api.service.DonationService;
import com.randeng.api.service.FundraisingService;
import com.randeng.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller("userDonationController")
@RequestMapping("user/donation")
public class DonationController extends BaseController {

    @Resource(name = "donationServiceImpl")
    private DonationService donationService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "fundraisingServiceImpl")
    private FundraisingService fundraisingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam Long userId) {
        User user = userService.find(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Pageable pageable = new Pageable(pageNumber, pageSize);
        pageable.getFilters().add(Filter.eq("user.id", userId));
        return ResponseEntity.ok(WebResponse.success(donationService.findPage(pageable)));
    }

    @RequestMapping(value = "recent", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> listRecent(@RequestParam(defaultValue = "10") Integer count) {
        return ResponseEntity.ok(WebResponse.success(donationService.findList(count, null, Arrays.asList(Order.desc("time")))));
    }

    @RequestMapping(value = "fundraising/{fundraisingId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> listByFundraising(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @PathVariable Long fundraisingId) {
        Fundraising fundraising = fundraisingService.find(fundraisingId);
        if (fundraising == null) {
            return ResponseEntity.notFound().build();
        }
        Pageable pageable = new Pageable(pageNumber, pageSize);
        pageable.getFilters().add(Filter.eq("fundraising.id", fundraisingId));
        return ResponseEntity.ok(WebResponse.success(donationService.findPage(pageable)));
    }

    @RequestMapping(value = "{id}/comment", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> setComment(@PathVariable Long id, @RequestBody Donation request) {
        Donation donation = donationService.find(id);
        if (donation == null) {
            return ResponseEntity.notFound().build();
        }
        if (!donation.getUser().getId().equals(currentUser().getId())) {
            return ResponseEntity.badRequest().body("User can only comment his own donation.");
        }
        donation.setComment(request.getComment());
        donationService.update(donation);
        return ResponseEntity.ok(WebResponse.success());
    }

    @RequestMapping(value = "mine", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> userDonation() {
        User self = currentUser();
        long userDonationCount = donationService.count(Filter.eq("user", self));

        UserDonationStatsResponse response = new UserDonationStatsResponse();
        response.setCount(userDonationCount);
        response.setAmount(self.getDonationAmount());
        return ResponseEntity.ok(WebResponse.success(response));
    }
}
