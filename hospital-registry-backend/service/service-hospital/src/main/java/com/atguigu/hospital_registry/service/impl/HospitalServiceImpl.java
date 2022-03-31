package com.atguigu.hospital_registry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.dmn.client.DictionaryFeignClient;
import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.repostitory.HospitalRepository;
import com.atguigu.hospital_registry.service.HospitalService;
import com.atguigu.hospital_registry.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalRepository hospitalRepository;

    @Resource
    private DictionaryFeignClient dictionaryFeignClient;

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
    public Hospital getHospitalByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    @Override
    public Page<Hospital> selectHospitalByPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {

        Pageable pageable = PageRequest.of(page - 1, limit);

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                               .withIgnoreCase(true);

        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);

        Example<Hospital> example = Example.of(hospital, matcher);

        Page<Hospital> pages =  hospitalRepository.findAll(example, pageable);
        List<Hospital> hospitalList = pages.getContent();
        hospitalList.stream().forEach(item -> this.setHospitalType(item));

        return pages;
    }

    @Override
    public void updateHospitalStatus(String id, Integer status) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        optionalHospital.ifPresent(hospital -> {
            hospital.setStatus(status);
            hospital.setUpdateTime(LocalDateTime.now());
            hospitalRepository.save(hospital);
        });

        return;
    }

    @Override
    public Map<String, Object> getHospitalById(String id) {
        HashMap<String, Object> map = new HashMap<>();
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        optionalHospital.ifPresent(hospital -> {
            this.setHospitalType(hospital);
            map.put("hospital", hospital);
            map.put("bookingRule", hospital.getBookingRule());
            hospital.setBookingRule(null);
        });
        return map;
    }

    @Override
    public String getHospitalNameByHoscode(String hoscode) {
        return Optional.ofNullable(hospitalRepository.getHospitalByHoscode(hoscode))
                       .map(hospital -> hospital.getHosname())
                       .orElse(" ");
    }

    @Override
    public List<Hospital> getHospitalByName(String hosname) {
        Optional<List<Hospital>> optionalHospitalList = Optional.ofNullable(
                hospitalRepository.findHospitalByHosnameLike(hosname));
        if (optionalHospitalList.isPresent()) {
            return optionalHospitalList.get();
        } else {
            return new ArrayList<>();
        }
    }


    private Hospital setHospitalType(Hospital hospital) {
        String hospitalTypeName = dictionaryFeignClient.getName("Hostype", hospital.getHostype());
        String provinceName = dictionaryFeignClient.getName(hospital.getProvinceCode());
        String cityName = dictionaryFeignClient.getName(hospital.getCityCode());
        String districtName = dictionaryFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("hospitalTypeName", hospitalTypeName);
        hospital.getParam().put("fullAddress", provinceName + cityName + districtName);

        return hospital;
    }
}