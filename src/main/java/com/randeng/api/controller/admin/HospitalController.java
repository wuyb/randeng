package com.randeng.api.controller.admin;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Hospital;
import com.randeng.api.service.HospitalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("hospitalController")
@RequestMapping("admin/hospital")
public class HospitalController extends BaseController {

    @Resource(name = "hospitalServiceImpl")
    private HospitalService hospitalService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Hospital request) {
        if (StringUtils.isEmpty(request.getName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The name is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (request.getProvinceId() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The provinceId is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (request.getCityId() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The cityId is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (request.getRegionId() == null) {
            return ResponseEntity.badRequest().body(WebResponse.error("The regionId is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (StringUtils.isEmpty(request.getIntroduction())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The introduction is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (StringUtils.isEmpty(request.getContact())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The contact is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (StringUtils.isEmpty(request.getAddress())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The address is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The mobile is required.", ErrorCode.INVALID_HOSPITAL));
        }
        if (StringUtils.isEmpty(request.getPhotoUrl())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The photoUrl is required.", ErrorCode.INVALID_HOSPITAL));
        }

        hospitalService.save(request);

        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<Hospital> hospitalPage = hospitalService.findPage(new Pageable(pageNumber, pageSize));
        return ResponseEntity.ok(WebResponse.success(hospitalPage));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(hospitalService.find(id)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Hospital request) {
        Hospital hospital = hospitalService.find(id);
        if (hospital == null) {
            return ResponseEntity.notFound().build();
        }
        if (!StringUtils.isEmpty(request.getName())) {
            hospital.setName(request.getName());
        }
        if (request.getProvinceId() != null) {
            hospital.setProvinceId(request.getProvinceId());
        }
        if (request.getCityId() != null) {
            hospital.setCityId(request.getCityId());
        }
        if (request.getRegionId() != null) {
            hospital.setRegionId(request.getRegionId());
        }
        if (!StringUtils.isEmpty(request.getIntroduction())) {
            hospital.setIntroduction(request.getIntroduction());
        }
        if (!StringUtils.isEmpty(request.getContact())) {
            hospital.setContact(request.getContact());
        }
        if (!StringUtils.isEmpty(request.getAddress())) {
            hospital.setAddress(request.getAddress());
        }
        if (!StringUtils.isEmpty(request.getMobile())) {
            hospital.setMobile(request.getMobile());
        }
        if (!StringUtils.isEmpty(request.getPhotoUrl())) {
            hospital.setPhotoUrl(request.getPhotoUrl());
        }
        if (!StringUtils.isEmpty(request.getStory())) {
            hospital.setStory(request.getStory());
        }
        hospitalService.update(hospital);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "{id}/disable", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> disable(@PathVariable Long id) {
        Hospital hospital = hospitalService.find(id);
        hospitalService.delete(hospital);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "{id}/enable", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> enable(@PathVariable Long id) {
        Hospital hospital = hospitalService.find(id);
        hospital.setDeleted(false);
        hospitalService.update(hospital);
        return ResponseEntity.ok(WebResponse.success(true));
    }
}
