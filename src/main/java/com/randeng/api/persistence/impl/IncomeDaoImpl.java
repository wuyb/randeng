package com.randeng.api.persistence.impl;

import com.randeng.api.model.Income;
import com.randeng.api.persistence.IncomeDao;
import org.springframework.stereotype.Repository;

@Repository("incomeDaoImpl")
public class IncomeDaoImpl extends BaseDaoImpl<Income, Long> implements IncomeDao {
}
