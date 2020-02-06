package com.randeng.api.persistence.impl;

import com.randeng.api.model.Enterprise;
import com.randeng.api.persistence.EnterpriseDao;
import org.springframework.stereotype.Repository;

@Repository("enterpriseDaoImpl")
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise, Long> implements EnterpriseDao {
}
