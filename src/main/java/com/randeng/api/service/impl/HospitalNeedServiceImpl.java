package com.randeng.api.service.impl;


import com.randeng.api.model.HospitalNeedsCall;
import com.randeng.api.service.HospitalNeedService;
import org.springframework.stereotype.Service;

@Service("hospitalNeedServiceImpl")
public class HospitalNeedServiceImpl extends BaseServiceImpl<HospitalNeedsCall, Long> implements HospitalNeedService {
}
