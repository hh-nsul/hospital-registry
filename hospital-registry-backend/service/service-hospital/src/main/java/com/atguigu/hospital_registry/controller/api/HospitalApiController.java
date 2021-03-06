package com.atguigu.hospital_registry.controller.api;

import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.service.DepartmentService;
import com.atguigu.hospital_registry.service.HospitalService;
import com.atguigu.hospital_registry.vo.hosp.DepartmentVo;
import com.atguigu.hospital_registry.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hospital/user")
public class HospitalApiController {

    @Resource
    private HospitalService hospitalService;

    @Resource
    private DepartmentService departmentService;

    @ApiOperation(value = "Query Hospital List")
    @GetMapping("/list/{page}/{limit}")
    public Result getHospitalList(@PathVariable Integer page,
                                  @PathVariable Integer limit,
                                  HospitalQueryVo hospitalQueryVo) {

        Page<Hospital> hospitalList = hospitalService.selectHospitalByPage(page, limit, hospitalQueryVo);
        return Result.ok(hospitalList);
    }

    @ApiOperation(value = "Query Based on Hospital Name")
    @GetMapping("/{hosname}")
    public Result getHospitalByName(@PathVariable String hosname) {
        List<Hospital> hospitalList = hospitalService.getHospitalByName(hosname);
        return Result.ok(hospitalList);
    }

    @GetMapping("/department/{hoscode}")
    public Result getDepartmentByHoscode(@PathVariable String hoscode) {
        List<DepartmentVo> departmentTreeList = departmentService.getDepartmentTreeList(hoscode);
        return Result.ok(departmentTreeList);
    }

    @GetMapping("/hospital/detail/{hoscode}")
    public Result getHospitalDetailByHoscode(@PathVariable String hoscode) {
        Map<String, Object> map = hospitalService.getHospitalDetailByHoscode(hoscode);
        return Result.ok(map);
    }

}
