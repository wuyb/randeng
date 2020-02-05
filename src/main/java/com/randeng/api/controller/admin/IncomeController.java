package com.randeng.api.controller.admin;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Income;
import com.randeng.api.service.IncomeService;
import com.randeng.tools.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller("incomeController")
@RequestMapping("admin/income")
public class IncomeController extends BaseController {

    @Resource(name = "incomeServiceImpl")
    private IncomeService incomeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Income income) {
        if (income.getAmount() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The amount is required.", ErrorCode.INVALID_INCOME));
        }
        if (income.getBookingDate() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The booking date is required.", ErrorCode.INVALID_INCOME));
        }
        if (income.getIncomeType() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The income type is required.", ErrorCode.INVALID_INCOME));
        }
        if (StringUtils.isEmpty(income.getSerialNumber())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The serial number is required.", ErrorCode.INVALID_INCOME));
        }
        if (StringUtils.isEmpty(income.getSource())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The source is required.", ErrorCode.INVALID_INCOME));
        }
        if (income.getPhotos() == null || income.getPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The photos is required.", ErrorCode.INVALID_INCOME));
        }

        income.setOperatorId(currentUser().getId());
        incomeService.save(income);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (startTime != null) {
            pageable.getFilters().add(Filter.ge("bookingDate", DateUtils.startOfDay(startTime)));
        }
        if (endTime != null) {
            pageable.getFilters().add(Filter.le("bookingDate", DateUtils.endOfDay(endTime)));
        }
        Page<Income> incomePage = incomeService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(incomePage));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(incomeService.find(id)));
    }
}
