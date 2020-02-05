package com.randeng.api.persistence.impl;

import com.randeng.api.model.District;
import com.randeng.api.persistence.DistrictDao;
import org.springframework.stereotype.Repository;

@Repository("districtDaoImpl")
public class DistrictDaoImpl extends BaseDaoImpl<District, Long> implements DistrictDao {
}
