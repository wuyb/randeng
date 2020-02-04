package com.randeng.api.persistence.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;
import com.randeng.api.persistence.FundraisingDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fundraisingDaoImpl")
public class FundraisingDaoImpl extends BaseDaoImpl<Fundraising, Long> implements FundraisingDao {
    @Override
    public Page<Fundraising> findPageByHospital(Hospital hospital, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fundraising> criteriaQuery = criteriaBuilder.createQuery(Fundraising.class);
        Root<Fundraising> root = criteriaQuery.from(Fundraising.class);
        criteriaQuery.select(root).where(criteriaBuilder.isMember(hospital, root.get("hospitals")));

        if (pageable.getFilters() == null) {
            pageable.setFilters(new ArrayList<>());
        } else {
            pageable.setFilters(new ArrayList<>(pageable.getFilters()));
        }
        pageable.getFilters().add(new Filter(BaseEntity.DELETED_PROPERTY_NAME, Filter.Operator.eq, false));

        return findPage(criteriaQuery, pageable);
    }

    @Override
    public Page<Fundraising> findPageByHospitals(List<Hospital> hospitals, Pageable pageable) {

        if (pageable.getFilters() == null) {
            pageable.setFilters(new ArrayList<>());
        } else {
            pageable.setFilters(new ArrayList<>(pageable.getFilters()));
        }
        pageable.getFilters().add(new Filter(BaseEntity.DELETED_PROPERTY_NAME, Filter.Operator.eq, false));

        if (hospitals.size() == 0) {
            return findPage(pageable);
        } else {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Fundraising> criteriaQuery = criteriaBuilder.createQuery(Fundraising.class);
            Root<Fundraising> root = criteriaQuery.from(Fundraising.class);
            ListJoin<Fundraising, Hospital> hospitalSetJoin = root.joinList("hospitals");
            Predicate predicate = hospitalSetJoin.get("id").in(hospitals.stream().map(Hospital::getId).collect(Collectors.toSet()));
            criteriaQuery.select(root).distinct(true).where(predicate);
            return findPage(criteriaQuery, pageable);
        }
    }
}
