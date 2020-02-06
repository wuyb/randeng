package com.randeng.api.service.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.model.District;
import com.randeng.api.persistence.DistrictDao;
import com.randeng.api.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("districtServiceImpl")
public class DistrictServiceImpl extends BaseServiceImpl<District, Long> implements DistrictService {

    @Resource(name = "districtDaoImpl")
    private DistrictDao districtDao;

    @Override
    public List<District> search(String keyword) {
        return districtDao.findList(null, null, Arrays.asList(Filter.like("name", keyword)), null);
    }
}
