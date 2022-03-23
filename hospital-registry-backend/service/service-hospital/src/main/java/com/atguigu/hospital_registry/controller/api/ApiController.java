package com.atguigu.hospital_registry.controller.api;

import com.atguigu.hospital_registry.service.HospitalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/hospital")
public class ApiController {

    @Resource
    private HospitalService hospitalService;
}
