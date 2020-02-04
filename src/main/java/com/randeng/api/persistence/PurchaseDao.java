package com.randeng.api.persistence;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Purchase;

/**
 * The DAO to manage <code>Purchase</code> entity.
 */
public interface PurchaseDao extends BaseDao<Purchase, Long> {
    Page<Purchase> findPageByFundraising(Fundraising fundraising, Pageable pageable);
}
