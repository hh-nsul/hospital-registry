package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface HospitalService {

    // Upload hospital info
    void save(Map<String, Object> paramMap);

    Hospital getByHoscode(String hoscode);

    Page<Hospital> selectHospitalByPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);
}
