package com.randeng.api.persistence.impl;

import com.randeng.api.model.ProductSourceCall;
import com.randeng.api.persistence.ProductSourceCallDao;
import org.springframework.stereotype.Repository;

@Repository("productSourceCallDaoImpl")
public class ProductSourceCallDaoImpl extends BaseDaoImpl<ProductSourceCall, Long> implements ProductSourceCallDao {
}
