package com.randeng.api.controller.admin;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Expense;
import com.randeng.api.service.ExpenseService;
import com.randeng.tools.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller("expenseController")
@RequestMapping("admin/expense")
public class ExpenseController extends BaseController {

    @Resource(name = "expenseServiceImpl")
    private ExpenseService expenseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Expense expense) {
        if (expense.getAmount() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The amount is required.", ErrorCode.INVALID_INCOME));
        }
        if (expense.getBookingDate() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The booking date is required.", ErrorCode.INVALID_INCOME));
        }
        if (expense.getExpenseType() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The expense type is required.", ErrorCode.INVALID_INCOME));
        }
        if (StringUtils.isEmpty(expense.getSerialNumber())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The serial number is required.", ErrorCode.INVALID_INCOME));
        }
        if (expense.getPhotos() == null || expense.getPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The photos is required.", ErrorCode.INVALID_INCOME));
        }

        expense.setOperatorId(currentUser().getId());
        expenseService.save(expense);
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
        Page<Expense> expensePage = expenseService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(expensePage));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(expenseService.find(id)));
    }
}
