package com.randeng.api.service.impl;

import com.randeng.api.model.Expense;
import com.randeng.api.service.ExpenseService;
import org.springframework.stereotype.Service;

@Service("expenseServiceImpl")
public class ExpenseServiceImpl extends BaseServiceImpl<Expense, Long> implements ExpenseService {
}
