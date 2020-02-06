package com.randeng.api.service;

import com.randeng.api.model.District;

import java.util.List;

/**
 * This interface defines the district service.
 */
public interface DistrictService extends BaseService<District, Long> {
    List<District> search(String keyword);
}
