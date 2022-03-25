package com.atguigu.hospital_registry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.model.hosp.Department;
import com.atguigu.hospital_registry.model.hosp.Schedule;
import com.atguigu.hospital_registry.repostitory.ScheduleRepository;
import com.atguigu.hospital_registry.service.ScheduleService;
import com.atguigu.hospital_registry.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleRepository scheduleRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        String paramMapStr = JSONObject.toJSONString(paramMap);
        Schedule schedule = JSONObject.parseObject(paramMapStr, Schedule.class);

        Optional<Schedule> optionalSchedule = Optional.ofNullable(
                                scheduleRepository.getScheduleByHoscodeAndHosScheduleId(
                                        schedule.getHoscode(), schedule.getHosScheduleId()));

        if (optionalSchedule.isPresent()) {
            Schedule schedulePresent = optionalSchedule.get();
            schedulePresent.setUpdateTime(LocalDateTime.now());
            schedulePresent.setIsDeleted(0);
            scheduleRepository.save(schedulePresent);
        } else {
            schedule.setCreateTime(LocalDateTime.now());
            schedule.setUpdateTime(schedule.getCreateTime());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);
        }

    }

    @Override
    public Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                                                      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                                      .withIgnoreCase(true);

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo, schedule);

        Example<Schedule> example = Example.of(schedule, exampleMatcher);

        return scheduleRepository.findAll(example, pageable);
    }

    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Optional<Schedule> optionalSchedule = Optional.ofNullable(
                                                scheduleRepository.getScheduleByHoscodeAndHosScheduleId(
                                                        hoscode, hosScheduleId));

        optionalSchedule.ifPresent(schedule -> scheduleRepository.deleteById(schedule.getId()));
    }
}
