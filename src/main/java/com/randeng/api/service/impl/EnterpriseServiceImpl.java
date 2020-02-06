package com.randeng.api.service.impl;

import com.randeng.api.model.Enterprise;
import com.randeng.api.service.BaseService;
import com.randeng.api.service.EnterpriseService;
import org.springframework.stereotype.Service;

@Service("enterpriseServiceImpl")
public class EnterpriseServiceImpl extends BaseServiceImpl<Enterprise, Long> implements EnterpriseService {
}
