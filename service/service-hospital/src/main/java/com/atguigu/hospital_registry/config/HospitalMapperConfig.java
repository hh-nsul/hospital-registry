package com.atguigu.hospital_registry.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atguigu.hospital_registry.mapper")
public class HospitalMapperConfig {
}
