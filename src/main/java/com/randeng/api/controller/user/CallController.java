package com.randeng.api.controller.user;

import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.EnterpriseCall;
import com.randeng.api.model.HospitalNeedsCall;
import com.randeng.api.model.ProductSourceCall;
import com.randeng.api.service.EnterpriseService;
import com.randeng.api.service.HospitalNeedService;
import com.randeng.api.service.ProductSourceCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller("userCallController")
@RequestMapping("user/call")
public class CallController extends BaseController {

    @Resource(name = "productSourceCallServiceImpl")
    private ProductSourceCallService productSourceCallService;

    @Resource(name = "hospitalNeedServiceImpl")
    private HospitalNeedService hospitalNeedService;

    @Resource(name = "enterpriseServiceImpl")
    private EnterpriseService enterpriseService;

    @RequestMapping(value = "product_source", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createProductSourceCall(@RequestBody ProductSourceCall call) {
        if (StringUtils.isEmpty(call.getContact())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The contact is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getMobile())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The mobile is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getProductName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The product is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getProductSpec())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The spec is required.", ErrorCode.INVALID_CALL));
        }
        if (call.getPhotos() == null || call.getPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The photos is required.", ErrorCode.INVALID_CALL));
        }
        productSourceCallService.save(call);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "hospital_needs", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createHospitalNeedsCall(@RequestBody HospitalNeedsCall call) {
        if (StringUtils.isEmpty(call.getContact())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The contact is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getMobile())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The mobile is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getProductName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The product is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getHospitalName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The hospital name is required.", ErrorCode.INVALID_CALL));
        }
        if (call.getPhotos() == null || call.getPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The photos is required.", ErrorCode.INVALID_CALL));
        }
        hospitalNeedService.save(call);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "enterprise", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createEnterpriseCall(@RequestBody EnterpriseCall call) {
        if (StringUtils.isEmpty(call.getContact())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The contact is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getMobile())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The mobile is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getEnterpriseName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The enterprise name is required.", ErrorCode.INVALID_CALL));
        }
        if (StringUtils.isEmpty(call.getDonationsName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The donation name is required.", ErrorCode.INVALID_CALL));
        }
        if (call.getPhotos() == null || call.getPhotos().size() == 0) {
            return ResponseEntity.badRequest().body(WebResponse.error("The photos is required.", ErrorCode.INVALID_CALL));
        }
        enterpriseService.save(call);
        return ResponseEntity.ok(WebResponse.success(true));
    }
}
