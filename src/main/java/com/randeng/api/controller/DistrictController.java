package com.randeng.api.controller;

import com.randeng.api.common.Filter;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.District;
import com.randeng.api.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller("districtController")
@RequestMapping("district")
public class DistrictController extends BaseController {

    @Resource(name = "districtServiceImpl")
    private DistrictService districtService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getByParent(@RequestParam Long parentId) {
        List<District> districts = districtService.findList(null, Arrays.asList(Filter.eq("parentId", parentId)), null);
        return ResponseEntity.ok(WebResponse.success(districts));
    }

}
