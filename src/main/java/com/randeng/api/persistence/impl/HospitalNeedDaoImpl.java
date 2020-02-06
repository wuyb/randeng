package com.randeng.api.persistence.impl;

import com.randeng.api.model.HospitalNeeds;
import com.randeng.api.persistence.BaseDao;
import com.randeng.api.persistence.HospitalNeedDao;
import org.springframework.stereotype.Repository;

@Repository("hospitalNeedDaoImpl")
public class HospitalNeedDaoImpl extends BaseDaoImpl<HospitalNeeds, Long> implements HospitalNeedDao {
}
