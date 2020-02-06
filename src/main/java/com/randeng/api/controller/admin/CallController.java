package com.randeng.api.controller.admin;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.*;
import com.randeng.api.service.EnterpriseService;
import com.randeng.api.service.HospitalNeedService;
import com.randeng.api.service.ProductSourceCallService;
import com.randeng.tools.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller("callController")
@RequestMapping("admin/call")
public class CallController extends BaseController {

    @Resource(name = "productSourceCallServiceImpl")
    private ProductSourceCallService productSourceCallService;

    @Resource(name = "hospitalNeedServiceImpl")
    private HospitalNeedService hospitalNeedService;

    @Resource(name = "enterpriseServiceImpl")
    private EnterpriseService enterpriseService;

    @RequestMapping(value = "product_source", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> listProductSourceCall(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (startTime != null) {
            pageable.getFilters().add(Filter.ge("createDate", DateUtils.startOfDay(startTime)));
        }
        if (endTime != null) {
            pageable.getFilters().add(Filter.le("createDate", DateUtils.endOfDay(endTime)));
        }
        Page<ProductSourceCall> productSourceCallPage = productSourceCallService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(productSourceCallPage));
    }

    @RequestMapping(value = "product_source/{id}", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> addRecordToProductSourceCall(@PathVariable Long id, @RequestBody ProductSourceCallRecord record) {
        ProductSourceCall call = productSourceCallService.find(id);
        if (call == null) {
            return ResponseEntity.notFound().build();
        }
        record.setCall(call);
        record.setOperator(currentUser());
        call.getRecords().add(record);
        productSourceCallService.update(call);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "hospital_needs", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> listHospitalNeedsCall(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (startTime != null) {
            pageable.getFilters().add(Filter.ge("createDate", DateUtils.startOfDay(startTime)));
        }
        if (endTime != null) {
            pageable.getFilters().add(Filter.le("createDate", DateUtils.endOfDay(endTime)));
        }
        Page<HospitalNeedsCall> hospitalNeedServicePage = hospitalNeedService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(hospitalNeedServicePage));
    }

    @RequestMapping(value = "hospital_needs/{id}", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> addRecordToHospitalNeedsCall(@PathVariable Long id, @RequestBody HospitalNeedsCallRecord record) {
        HospitalNeedsCall call = hospitalNeedService.find(id);
        if (call == null) {
            return ResponseEntity.notFound().build();
        }
        record.setCall(call);
        record.setOperator(currentUser());
        call.getRecords().add(record);
        hospitalNeedService.update(call);
        return ResponseEntity.ok(WebResponse.success(true));
    }


    @RequestMapping(value = "enterprise", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> listEnterpriseCall(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (startTime != null) {
            pageable.getFilters().add(Filter.ge("createDate", DateUtils.startOfDay(startTime)));
        }
        if (endTime != null) {
            pageable.getFilters().add(Filter.le("createDate", DateUtils.endOfDay(endTime)));
        }
        Page<EnterpriseCall> enterpriseCallPage = enterpriseService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(enterpriseCallPage));
    }

    @RequestMapping(value = "enterprise/{id}", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> addRecordToEnterpriseCall(@PathVariable Long id, @RequestBody EnterpriseCallRecord record) {
        EnterpriseCall call = enterpriseService.find(id);
        if (call == null) {
            return ResponseEntity.notFound().build();
        }
        record.setCall(call);
        record.setOperator(currentUser());
        call.getRecords().add(record);
        enterpriseService.update(call);
        return ResponseEntity.ok(WebResponse.success(true));
    }
}
