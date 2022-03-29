package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.hosp.Schedule;
import com.atguigu.hospital_registry.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);

    Map<String, Object> getScheduleRule(Long page, Long limit, String hoscode, String depcode);

    List<Schedule> getScheduleDetail(String hoscode, String depcode, String workDate);
}
