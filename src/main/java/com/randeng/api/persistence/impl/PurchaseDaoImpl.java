package com.randeng.api.persistence.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Purchase;
import com.randeng.api.persistence.PurchaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl extends BaseDaoImpl<Purchase, Long> implements PurchaseDao {
    @Override
    public Page<Purchase> findPageByFundraising(Fundraising fundraising, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Purchase> criteriaQuery = criteriaBuilder.createQuery(Purchase.class);
        Root<Purchase> root = criteriaQuery.from(Purchase.class);
        criteriaQuery.select(root).where(criteriaBuilder.isMember(fundraising, root.get("fundraisings")));

        if (pageable.getFilters() == null) {
            pageable.setFilters(new ArrayList<>());
        } else {
            pageable.setFilters(new ArrayList<>(pageable.getFilters()));
        }
        pageable.getFilters().add(new Filter(BaseEntity.DELETED_PROPERTY_NAME, Filter.Operator.eq, false));

        return findPage(criteriaQuery, pageable);
    }
}
