package com.randeng.api.service;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Fundraising;
import com.randeng.api.model.Hospital;

import java.util.List;

/**
 * This interface defines the fundraising service.
 */
public interface FundraisingService extends BaseService<Fundraising, Long> {
    Page<Fundraising> findPageByHospital(Hospital hospital, Pageable pageable);
    Page<Fundraising> findPageByHospitals(List<Hospital> hospitals, Pageable pageable);
    Page<Fundraising> search(String keyword, Pageable pageable);
}
