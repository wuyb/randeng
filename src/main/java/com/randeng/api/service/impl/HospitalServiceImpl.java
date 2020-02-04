package com.randeng.api.service.impl;

import com.randeng.api.model.Hospital;
import com.randeng.api.service.HospitalService;
import org.springframework.stereotype.Service;

@Service("hospitalServiceImpl")
public class HospitalServiceImpl extends BaseServiceImpl<Hospital, Long> implements HospitalService {
}
