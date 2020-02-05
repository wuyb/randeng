package com.randeng.api.persistence.impl;

import com.randeng.api.model.Logistic;
import com.randeng.api.persistence.LogisticDao;
import org.springframework.stereotype.Repository;

@Repository("logisticDaoImpl")
public class LogisticDaoImpl extends BaseDaoImpl<Logistic, Long> implements LogisticDao {
}
