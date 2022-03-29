package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.model.hosp.Schedule;
import com.atguigu.hospital_registry.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/hospital/schedule")
@CrossOrigin
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @ApiOperation(value = "Query schedule info")
    @GetMapping("/rule/{page}/{limit}/{hoscode}/{depcode}")
    public Result getScheduleRule(@PathVariable Long page,
                                  @PathVariable Long limit,
                                  @PathVariable String hoscode,
                                  @PathVariable String depcode) {
        Map<String, Object> map = scheduleService.getScheduleRule(page, limit, hoscode, depcode);
        return Result.ok(map);
    }

    @ApiOperation(value = "Query detail information of schedule")
    @GetMapping("/detail/{hoscode}/{depcode}/{workDate}")
    public Result getScheduleDetail(@PathVariable String hoscode,
                                    @PathVariable String depcode,
                                    @PathVariable String workDate) {
        List<Schedule> scheduleDetailList = scheduleService.getScheduleDetail(hoscode, depcode, workDate);
        return Result.ok(scheduleDetailList);
    }
}
