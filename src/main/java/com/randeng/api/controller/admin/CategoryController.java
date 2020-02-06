package com.randeng.api.controller.admin;

import com.randeng.api.controller.common.BaseController;
import com.randeng.api.controller.common.ErrorCode;
import com.randeng.api.controller.common.WebResponse;
import com.randeng.api.model.Category;
import com.randeng.api.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("categoryController")
@RequestMapping("admin/category")
public class CategoryController extends BaseController {

    @Resource(name = "categoryServiceImpl")
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Category category) {
        if (StringUtils.isEmpty(category.getName())) {
            return ResponseEntity.badRequest().body(WebResponse.error("The name is required.", ErrorCode.INVALID_HOSPITAL));
        }

        categoryService.save(category);

        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> list() {
        return ResponseEntity.ok(WebResponse.success(categoryService.findAll()));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category) {
        Category existing = categoryService.find(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (!StringUtils.isEmpty(category.getName())) {
            existing.setName(category.getName());
        }
        categoryService.update(existing);
        return ResponseEntity.ok(WebResponse.success(true));
    }


    @RequestMapping(value = "{id}/disable", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> disable(@PathVariable Long id) {
        Category category = categoryService.find(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.delete(category);
        return ResponseEntity.ok(WebResponse.success(true));
    }

    @RequestMapping(value = "{id}/enable", method = RequestMethod.PUT)
    @Secured({"ROLE_admin", "ROLE_operator"})
    public @ResponseBody
    ResponseEntity<?> enable(@PathVariable Long id) {
        Category category = categoryService.find(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setDeleted(false);
        categoryService.update(category);
        return ResponseEntity.ok(WebResponse.success(true));
    }
}
