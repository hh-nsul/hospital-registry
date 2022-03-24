package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {

    // Upload hospital info
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);
}
