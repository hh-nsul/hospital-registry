package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.service.DepartmentService;
import com.atguigu.hospital_registry.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/hospital/department")
//@CrossOrigin
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @ApiOperation(value = "Get the list of departments")
    @GetMapping("/list/{hoscode}")
    public Result getDepartmentList(@PathVariable String hoscode) {
        List<DepartmentVo> departmentTreeList = departmentService.getDepartmentTreeList(hoscode);
        return Result.ok(departmentTreeList);
    }
}
