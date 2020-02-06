package com.randeng.api.service.impl;

import com.randeng.api.model.EnterpriseCall;
import com.randeng.api.service.EnterpriseService;
import org.springframework.stereotype.Service;

@Service("enterpriseServiceImpl")
public class EnterpriseServiceImpl extends BaseServiceImpl<EnterpriseCall, Long> implements EnterpriseService {
}
