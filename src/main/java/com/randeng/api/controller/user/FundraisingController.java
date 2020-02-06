package com.randeng.api.controller.user;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;
import com.randeng.api.service.FundraisingService;
import com.randeng.api.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller("userFundraisingController")
@RequestMapping("user/fundraising")
public class FundraisingController extends BaseController {

    @Resource(name = "fundraisingServiceImpl")
    private FundraisingService fundraisingService;

    @Resource(name = "hospitalServiceImpl")
    private HospitalService hospitalService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false) Fundraising.FundRaisingStatus status) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (status != null) {
            pageable.getFilters().add(new Filter("status", Filter.Operator.eq, status));
        }
        Page<Fundraising> fundraisingPage = fundraisingService.findPage(pageable);
        return ResponseEntity.ok(WebResponse.success(fundraisingPage));
    }

    @RequestMapping(value = "byDistrict", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> listByDistrict(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Fundraising.FundRaisingStatus status,
            @RequestParam(required = false) Long hospitalId,
            @RequestParam(required = false) Long province,
            @RequestParam(required = false) Long city,
            @RequestParam(required = false) Long region) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (status != null) {
            pageable.getFilters().add(new Filter("status", Filter.Operator.eq, status));
        }
        // if hospital is specified, ignore province/city/region restrictions
        if (hospitalId != null) {
            Hospital hospital = hospitalService.find(hospitalId);
            if (hospital == null) {
                return ResponseEntity.notFound().build();
            }
            Page<Fundraising> fundraisingPage = fundraisingService.findPageByHospital(hospital, pageable);
            return ResponseEntity.ok(WebResponse.success(fundraisingPage));
        } else if (province != null || city != null || region != null) {
            String attribute = null;
            Long attrValue = null;
            if (province != null) {
                attribute = "provinceId";
                attrValue = province;
            }
            if (city != null) {
                attribute = "cityId";
                attrValue = city;
            }
            if (region != null) {
                attribute = "regionId";
                attrValue = region;
            }
            List<Hospital> hospitals = hospitalService.findList(null, Arrays.asList(new Filter(attribute, Filter.Operator.eq, attrValue)), null);
            if (hospitals.size() == 0) {
                return ResponseEntity.notFound().build();
            }
            Page<Fundraising> fundraisingPage = fundraisingService.findPageByHospitals(hospitals, pageable);
            return ResponseEntity.ok(WebResponse.success(fundraisingPage));
        } else {
            Page<Fundraising> fundraisingPage = fundraisingService.findPage(pageable);
            return ResponseEntity.ok(WebResponse.success(fundraisingPage));
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> search(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false) Fundraising.FundRaisingStatus status,
                           @RequestParam(required = true) String keyword) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (status != null) {
            pageable.getFilters().add(new Filter("status", Filter.Operator.eq, status));
        }
        Page<Fundraising> fundraisingPage = fundraisingService.search(keyword, pageable);
        return ResponseEntity.ok(WebResponse.success(fundraisingPage));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(fundraisingService.find(id)));
    }

}
