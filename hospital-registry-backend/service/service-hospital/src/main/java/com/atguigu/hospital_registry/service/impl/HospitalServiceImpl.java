package com.atguigu.hospital_registry.service.impl;

import com.atguigu.hospital_registry.repostitory.HospitalRepository;
import com.atguigu.hospital_registry.service.HospitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalRepository hospitalRepository;
}
