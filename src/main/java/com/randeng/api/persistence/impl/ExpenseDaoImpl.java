package com.randeng.api.persistence.impl;

import com.randeng.api.model.Expense;
import com.randeng.api.persistence.ExpenseDao;
import org.springframework.stereotype.Repository;

@Repository("expenseDaoImpl")
public class ExpenseDaoImpl extends BaseDaoImpl<Expense, Long> implements ExpenseDao {
}
