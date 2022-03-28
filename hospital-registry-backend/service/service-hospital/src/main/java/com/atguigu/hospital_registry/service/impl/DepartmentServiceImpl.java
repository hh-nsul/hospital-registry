package com.atguigu.hospital_registry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.model.hosp.Department;
import com.atguigu.hospital_registry.repostitory.DepartmentRepository;
import com.atguigu.hospital_registry.service.DepartmentService;
import com.atguigu.hospital_registry.vo.hosp.DepartmentQueryVo;
import com.atguigu.hospital_registry.vo.hosp.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String paramMapStr = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paramMapStr, Department.class);
        Optional<Department> optionalDepartment = Optional.ofNullable(
                                    departmentRepository.getDepartmentByHoscodeAndDepcode(
                                            department.getHoscode(), department.getDepcode()));

        if (optionalDepartment.isPresent()) {
            Department departmentPresent = optionalDepartment.get();
            departmentPresent.setUpdateTime(LocalDateTime.now());
            departmentPresent.setIsDeleted(0);
            departmentRepository.save(departmentPresent);
        } else {
            department.setCreateTime(LocalDateTime.now());
            department.setUpdateTime(department.getCreateTime());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {

        // The first page starts at index 0
        Pageable pageable = PageRequest.of(page - 1, limit);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                                                      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                                      .withIgnoreCase(true);

        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);

        Example<Department> example = Example.of(department, exampleMatcher);

        return departmentRepository.findAll(example, pageable);
    }

    @Override
    public void remove(String hoscode, String depcode) {

        Optional<Department> optionalDepartment = Optional.ofNullable(departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode));

        optionalDepartment.ifPresent(department -> departmentRepository.deleteById(department.getId()));

    }

    /**
     * P92
     * @Test http://localhost:8201/swagger-ui.html#!/department45controller/getDepartmentListUsingGET
     * @param hoscode = 1000_1
     */
    @Override
    public List<DepartmentVo> getDepartmentTreeList(String hoscode) {
        List<DepartmentVo> treeList = new ArrayList<>();

        Department departmentQuery = new Department();
        departmentQuery.setHoscode(hoscode);
        Example<Department> example = Example.of(departmentQuery);
        List<Department> departmentList = departmentRepository.findAll(example);

        Map<String, List<Department>> departmentMap = departmentList.stream().collect(Collectors.groupingBy(Department::getBigcode));

        for (Map.Entry<String, List<Department>> entry : departmentMap.entrySet()) {
            String superDepCode = entry.getKey();
            List<Department> depList = entry.getValue();

            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(superDepCode);
            departmentVo.setDepname(depList.get(0).getDepname());

            ArrayList<DepartmentVo> subDepList = new ArrayList<>();
            for (Department department : depList) {
                DepartmentVo subDepartmentVo = new DepartmentVo();
                subDepartmentVo.setDepcode(department.getDepcode());
                subDepartmentVo.setDepname(department.getDepname());
                subDepList.add(subDepartmentVo);
            }

            departmentVo.setChildren(subDepList);

            treeList.add(departmentVo);
        }

        return treeList;
    }
}
