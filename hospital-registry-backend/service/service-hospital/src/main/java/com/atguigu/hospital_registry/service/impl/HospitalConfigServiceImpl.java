package com.atguigu.hospital_registry.service.impl;

import com.atguigu.hospital_registry.mapper.HospitalConfigMapper;
import com.atguigu.hospital_registry.model.hosp.HospitalConfig;
import com.atguigu.hospital_registry.service.HospitalConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HospitalConfigServiceImpl extends ServiceImpl<HospitalConfigMapper, HospitalConfig> implements HospitalConfigService {
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hoscode", hoscode);
        HospitalConfig hospitalConfig = baseMapper.selectOne(queryWrapper);

        return hospitalConfig.getSignKey();
    }
}
