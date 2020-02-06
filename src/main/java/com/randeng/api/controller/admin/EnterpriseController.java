package com.randeng.api.controller.admin;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.controller.dto.EnterpriseRequest;
import com.randeng.api.controller.dto.HospitalNeedsRequest;
import com.randeng.api.model.Enterprise;
import com.randeng.api.service.EnterpriseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("enterpriseController")
@RequestMapping("/admin/call")
public class EnterpriseController extends BaseController {

    @Resource(name = "enterpriseServiceImpl")
    private EnterpriseService enterpriseService;

    @GetMapping("/enterprise")
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> hospital(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Page<Enterprise> page = enterpriseService.findPage(new Pageable(pageNumber, pageSize));
        return ResponseEntity.ok(WebResponse.success(page));
    }

    //enterprise/[goodsId]

    @PutMapping("enterprise/{goodsId}")
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> hospital(@PathVariable Long goodsId , @RequestBody EnterpriseRequest request ){
        Enterprise enterprise = enterpriseService.find(goodsId);
        if (!StringUtils.isEmpty(request.getRemarks())){
            enterprise.setRemarks(request.getRemarks());
        }
        if (request.getState() != null){
            enterprise.setState(request.getState());
        }
        enterpriseService.update(enterprise);
        return  ResponseEntity.ok(WebResponse.success(true));
    }
}
