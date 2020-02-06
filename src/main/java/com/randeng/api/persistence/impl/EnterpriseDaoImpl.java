package com.randeng.api.persistence.impl;

import com.randeng.api.model.EnterpriseCall;
import com.randeng.api.persistence.EnterpriseDao;
import org.springframework.stereotype.Repository;

@Repository("enterpriseDaoImpl")
public class EnterpriseDaoImpl extends BaseDaoImpl<EnterpriseCall, Long> implements EnterpriseDao {
}
