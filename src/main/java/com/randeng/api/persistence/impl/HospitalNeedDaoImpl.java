package com.randeng.api.persistence.impl;

import com.randeng.api.model.HospitalNeedsCall;
import com.randeng.api.persistence.HospitalNeedDao;
import org.springframework.stereotype.Repository;

@Repository("hospitalNeedDaoImpl")
public class HospitalNeedDaoImpl extends BaseDaoImpl<HospitalNeedsCall, Long> implements HospitalNeedDao {
}
