package com.atguigu.hospital_registry.service.impl;

import com.atguigu.hospital_registry.mapper.HospitalConfigMapper;
import com.atguigu.hospital_registry.model.hosp.HospitalConfig;
import com.atguigu.hospital_registry.service.HospitalConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HospitalConfigServiceImpl extends ServiceImpl<HospitalConfigMapper, HospitalConfig> implements HospitalConfigService {
}
