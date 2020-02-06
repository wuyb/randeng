package com.randeng.api.persistence.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;
import com.randeng.api.persistence.FundraisingDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
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

        pageable.getFilters().add(new Filter(BaseEntity.DELETED_PROPERTY_NAME, Filter.Operator.eq, false));

        return findPage(criteriaQuery, pageable);
    }

    @Override
    public Page<Fundraising> findPageByHospitals(List<Hospital> hospitals, Pageable pageable) {
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

    @Override
    public Page<Fundraising> search(String keyword, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fundraising> criteriaQuery = criteriaBuilder.createQuery(Fundraising.class);
        Root<Fundraising> root = criteriaQuery.from(Fundraising.class);

        // there are three parts to search on
        // 1. the category
        // 2. the district
        // 3. the hospital
        // these three parts are in OR relationship

        // search on category name
        Path<String> categoryPath = root.get("category").get("name");
        Predicate categoryPredicate = criteriaBuilder.like(categoryPath, "%" + keyword + "%");

        // search on district or hospital together

        Query query = entityManager.createNativeQuery("select id from rc_district where district like '%" + keyword + "%'");
        List districtIds = query.getResultList();
        if (districtIds.size() > 0) {
            StringBuilder ids = new StringBuilder();
            for (Object districtId : districtIds) {
                ids.append(districtId).append(",");
            }
            ids.deleteCharAt(ids.length() - 1);
            query = entityManager.createNativeQuery("select id from hospital where province_id in (" + ids + ") or city_id in (" + ids + ") or region_id in (" + ids + ") or name like '%" + keyword + "%'");
        } else {
            query = entityManager.createNativeQuery("select id from hospital where name like '%" + keyword + "%'");
        }

        List hospitalIds = query.getResultList();
        if (hospitalIds.size() > 0) {
            ListJoin<Fundraising, Hospital> hospitalSetJoin = root.joinList("hospitals");
            Predicate predicate = hospitalSetJoin.get("id").in(hospitalIds);
            criteriaQuery.select(root).distinct(true).where(criteriaBuilder.or(categoryPredicate, predicate));
        } else {
            criteriaQuery.select(root).distinct(true).where(categoryPredicate);
        }
        return findPage(criteriaQuery, pageable);
    }
}
