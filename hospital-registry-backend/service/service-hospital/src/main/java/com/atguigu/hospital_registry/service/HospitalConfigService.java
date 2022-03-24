package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.hosp.HospitalConfig;
import com.baomidou.mybatisplus.extension.service.IService;

public interface HospitalConfigService extends IService<HospitalConfig> {
    String getSignKey(String hoscode);
}
