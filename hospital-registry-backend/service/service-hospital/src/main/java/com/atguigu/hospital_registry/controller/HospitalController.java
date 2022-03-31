package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.service.HospitalService;
import com.atguigu.hospital_registry.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/hospital")
//@CrossOrigin
public class HospitalController {

    @Resource
    private HospitalService hospitalService;

    @GetMapping("/list/{page}/{limit}")
    public Result getHospitalList(@PathVariable Integer page,
                                  @PathVariable Integer limit,
                                  HospitalQueryVo hospitalQueryVo) {

        Page<Hospital> pageModel = hospitalService.selectHospitalByPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "Status Update")
    @GetMapping("/{id}/{status}")
    public Result updateHospitalStatus(@PathVariable String id,
                                       @PathVariable Integer status) {
        hospitalService.updateHospitalStatus(id, status);
        return Result.ok();
    }

    @ApiOperation(value = "Hospital Details")
    @GetMapping("/detail/{id}")
    public Result getHospitalDetail(@PathVariable String id) {
        Map<String, Object> map = hospitalService.getHospitalById(id);
        return Result.ok(map);
    }
}
