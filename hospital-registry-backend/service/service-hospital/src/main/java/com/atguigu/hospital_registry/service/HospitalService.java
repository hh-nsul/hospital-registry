package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface HospitalService {

    // Upload hospital info
    void save(Map<String, Object> paramMap);

    Hospital getHospitalByHoscode(String hoscode);

    Page<Hospital> selectHospitalByPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    void updateHospitalStatus(String id, Integer status);

    Map<String, Object> getHospitalById(String id);

    String getHospitalNameByHoscode(String hoscode);

    List<Hospital> getHospitalByName(String hosname);
}
