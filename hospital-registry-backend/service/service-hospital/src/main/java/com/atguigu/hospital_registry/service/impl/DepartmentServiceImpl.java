package com.atguigu.hospital_registry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.model.hosp.Department;
import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.repostitory.DepartmentRepository;
import com.atguigu.hospital_registry.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String paramMapStr = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paramMapStr, Department.class);
        Optional<Department> optionalDepartment = Optional.ofNullable(departmentRepository.getDepartmentByHoscodeAndDepcode(
                                                                         department.getHoscode(), department.getDepcode()));

        if (optionalDepartment.isPresent()) {
            department.setUpdateTime(LocalDateTime.now());
        } else {
            department.setCreateTime(LocalDateTime.now());
            department.setUpdateTime(department.getCreateTime());
        }
        department.setIsDeleted(0);
        departmentRepository.save(department);
    }
}
