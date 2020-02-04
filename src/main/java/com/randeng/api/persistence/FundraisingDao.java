package com.randeng.api.persistence;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;

import java.util.List;

/**
 * The DAO to manage <code>Fundraising</code> entity.
 */
public interface FundraisingDao extends BaseDao<Fundraising, Long> {
    Page<Fundraising> findPageByHospital(Hospital hospital, Pageable pageable);
    Page<Fundraising> findPageByHospitals(List<Hospital> hospitals, Pageable pageable);
}
