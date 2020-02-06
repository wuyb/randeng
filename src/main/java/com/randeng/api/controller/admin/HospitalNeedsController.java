package com.randeng.api.controller.admin;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.controller.dto.HospitalNeedsRequest;
import com.randeng.api.model.HospitalNeeds;
import com.randeng.api.service.HospitalNeedService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("hospitalNeedsController")
@RequestMapping("/admin/call")
public class HospitalNeedsController extends BaseController {


    @Resource(name = "hospitalNeedServiceImpl")
    private HospitalNeedService hospitalNeedService;

    @GetMapping("/hospital")
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> hospital(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Page<HospitalNeeds> page = hospitalNeedService.findPage(new Pageable(pageNumber, pageSize));
        return ResponseEntity.ok(WebResponse.success(page));
    }

    @PutMapping("hospital/{callId}")
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> hospital(@PathVariable Long callId , @RequestBody HospitalNeedsRequest hospitalNeedsRequest ){
        HospitalNeeds hospitalNeeds = hospitalNeedService.find(callId);
        if (!StringUtils.isEmpty(hospitalNeedsRequest.getRemarks())){
            hospitalNeeds.setRemarks(hospitalNeedsRequest.getRemarks());
        }
        if (hospitalNeedsRequest.getState() != null){
            hospitalNeeds.setState(hospitalNeedsRequest.getState());
        }
        hospitalNeedService.update(hospitalNeeds);
        return  ResponseEntity.ok(WebResponse.success(true));
    }
}
