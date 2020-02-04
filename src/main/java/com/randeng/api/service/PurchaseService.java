package com.randeng.api.service;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Purchase;

/**
 * This interface defines the purchase service.
 */
public interface PurchaseService extends BaseService<Purchase, Long> {
    Page<Purchase> findPageByFundraising(Fundraising fundraising, Pageable pageable);
}
