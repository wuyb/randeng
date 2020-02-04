package com.randeng.api.service.impl;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Purchase;
import com.randeng.api.persistence.PurchaseDao;
import com.randeng.api.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl extends BaseServiceImpl<Purchase, Long> implements PurchaseService {

    @Resource(name = "purchaseDaoImpl")
    private PurchaseDao purchaseDao;


    @Override
    public Page<Purchase> findPageByFundraising(Fundraising fundraising, Pageable pageable) {
        return purchaseDao.findPageByFundraising(fundraising, pageable);
    }
}
