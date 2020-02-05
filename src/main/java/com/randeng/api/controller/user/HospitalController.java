package com.randeng.api.controller.user;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("userHospitalController")
@RequestMapping("user/hospital")
public class HospitalController extends BaseController {

    @Resource(name = "hospitalServiceImpl")
    private HospitalService hospitalService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) Long province,
                           @RequestParam(required = false) Long city,
                           @RequestParam(required = false) Long region
    ) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        if (province != null || city != null || region != null) {
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
            pageable.getFilters().add(Filter.eq(attribute, attrValue));
            return ResponseEntity.ok(WebResponse.success(hospitalService.findPage(pageable)));
        } else {
            return ResponseEntity.ok(WebResponse.success(hospitalService.findPage(pageable)));
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(WebResponse.success(hospitalService.find(id)));
    }
}
