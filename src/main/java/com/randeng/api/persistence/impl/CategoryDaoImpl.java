package com.randeng.api.persistence.impl;

import com.randeng.api.model.Category;
import com.randeng.api.model.Expense;
import com.randeng.api.persistence.CategoryDao;
import com.randeng.api.persistence.ExpenseDao;
import org.springframework.stereotype.Repository;

@Repository("categoryDaoImpl")
public class CategoryDaoImpl extends BaseDaoImpl<Category, Long> implements CategoryDao {
}
