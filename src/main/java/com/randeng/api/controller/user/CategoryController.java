package com.randeng.api.controller.user;

import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller("userCategoryController")
@RequestMapping("user/category")
public class CategoryController extends BaseController {

    @Resource(name = "categoryServiceImpl")
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> list() {
        return ResponseEntity.ok(WebResponse.success(categoryService.findAll()));
    }
}
