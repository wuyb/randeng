package com.randeng.api.controller.user;

import com.randeng.api.controller.common.BaseController;
import com.randeng.api.service.FundraisingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller("userFundraisingController")
@RequestMapping("user/fundraising")
public class FundraisingController extends BaseController {

    @Resource(name = "fundraisingServiceImpl")
    private FundraisingService fundraisingService;

    
}
