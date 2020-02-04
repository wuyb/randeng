package com.randeng.api.persistence.impl;

import com.randeng.api.model.Hospital;
import com.randeng.api.persistence.HospitalDao;
import org.springframework.stereotype.Repository;

@Repository("hospitalDaoImpl")
public class HospitalDaoImpl extends BaseDaoImpl<Hospital, Long> implements HospitalDao {
}
