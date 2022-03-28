package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.hosp.Department;
import com.atguigu.hospital_registry.vo.hosp.DepartmentQueryVo;
import com.atguigu.hospital_registry.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    void save(Map<String, Object> paramMap);

    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);

    List<DepartmentVo> getDepartmentTreeList(String hoscode);
}
