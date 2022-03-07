package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.model.hosp.HospitalConfig;
import com.atguigu.hospital_registry.service.HospitalConfigService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/hospital/hospitalConfig")
public class HospitalConfigController {

    @Resource
    private HospitalConfigService hospitalConfigService;

    // http://localhost:8201/admin/hospital/hospitalConfig/findAll
    @GetMapping("/findAll")
    public List<HospitalConfig> findAllHospitalConfig() {
        List<HospitalConfig> hospitalConfigList = hospitalConfigService.list();
        return hospitalConfigList;
    }

}
