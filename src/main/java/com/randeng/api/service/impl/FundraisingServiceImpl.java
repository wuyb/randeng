package com.randeng.api.service.impl;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;
import com.randeng.api.persistence.FundraisingDao;
import com.randeng.api.service.FundraisingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("fundraisingServiceImpl")
public class FundraisingServiceImpl extends BaseServiceImpl<Fundraising, Long> implements FundraisingService {

    @Resource(name = "fundraisingDaoImpl")
    private FundraisingDao fundraisingDao;

    @Override
    public Page<Fundraising> findPageByHospital(Hospital hospital, Pageable pageable) {
        return fundraisingDao.findPageByHospital(hospital, pageable);
    }

    @Override
    public Page<Fundraising> findPageByHospitals(List<Hospital> hospitals, Pageable pageable) {
        return fundraisingDao.findPageByHospitals(hospitals, pageable);
    }
}
