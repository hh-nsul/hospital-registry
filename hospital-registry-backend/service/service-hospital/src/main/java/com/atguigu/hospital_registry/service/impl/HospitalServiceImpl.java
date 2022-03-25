package com.atguigu.hospital_registry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.repostitory.HospitalRepository;
import com.atguigu.hospital_registry.service.HospitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String jsonString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(jsonString, Hospital.class);

        Optional<Hospital> optionalHospital = Optional.ofNullable(hospitalRepository.getHospitalByHoscode(hospital.getHoscode()));
        if (optionalHospital.isPresent()) {
            Hospital hospitalPresent = optionalHospital.get();
            hospitalPresent.setUpdateTime(LocalDateTime.now());
            hospitalPresent.setIsDeleted(0);
            hospitalRepository.save(hospitalPresent);
        } else {
            hospital.setStatus(0);
            hospital.setCreateTime(LocalDateTime.now());
            hospital.setUpdateTime(hospital.getCreateTime());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }
}