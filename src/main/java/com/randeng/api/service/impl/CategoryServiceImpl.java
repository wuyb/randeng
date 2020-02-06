package com.randeng.api.service.impl;

import com.randeng.api.model.Category;
import com.randeng.api.service.CategoryService;
import com.randeng.api.service.ExpenseService;
import org.springframework.stereotype.Service;

@Service("categoryServiceImpl")
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> implements CategoryService {
}
