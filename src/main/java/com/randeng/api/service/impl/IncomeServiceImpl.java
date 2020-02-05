package com.randeng.api.service.impl;

import com.randeng.api.model.Income;
import com.randeng.api.service.IncomeService;
import org.springframework.stereotype.Service;

@Service("incomeServiceImpl")
public class IncomeServiceImpl extends BaseServiceImpl<Income, Long> implements IncomeService {
}
