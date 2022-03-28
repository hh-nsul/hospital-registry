package com.atguigu.hospital_registry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.model.hosp.Schedule;
import com.atguigu.hospital_registry.repostitory.ScheduleRepository;
import com.atguigu.hospital_registry.service.HospitalService;
import com.atguigu.hospital_registry.service.ScheduleService;
import com.atguigu.hospital_registry.vo.hosp.BookingScheduleRuleVo;
import com.atguigu.hospital_registry.vo.hosp.ScheduleQueryVo;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleRepository scheduleRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private HospitalService hospitalService;

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

    @Override
    public Map<String, Object> getScheduleRule(Long page, Long limit, String hoscode, String depcode) {
        Criteria criteria = Criteria.where("hoscode").is(hoscode).and("depcode").is(depcode);

        // Grouping based on work date
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("workDate").first("workDate").as("workDate")
                        .count().as("docCount")
                        .sum("reservedNumber").as("reservedNumber")
                        .sum("availableNumber").as("availableNumber"),

                Aggregation.sort(Sort.Direction.DESC, "workDate"),

                Aggregation.skip((page - 1) * limit),

                Aggregation.limit(limit)
        );

        AggregationResults<BookingScheduleRuleVo> aggResult =
                mongoTemplate.aggregate(agg, Schedule.class, BookingScheduleRuleVo.class);

        List<BookingScheduleRuleVo> bookingScheduleRuleVoList = aggResult.getMappedResults();

        Aggregation totalAgg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("workDate")
        );

        AggregationResults<BookingScheduleRuleVo> totalAggResult =
                mongoTemplate.aggregate(agg, Schedule.class, BookingScheduleRuleVo.class);

        int total = totalAggResult.getMappedResults().size();

        bookingScheduleRuleVoList.forEach( bookingSchedule -> {
            Date workDate = bookingSchedule.getWorkDate();
            String dayOfWeek = this.getDayOfWeek(new DateTime(workDate));
            bookingSchedule.setDayOfWeek(dayOfWeek);
        });

        Map<String, Object> mappedResults = new HashMap<>();
        mappedResults.put("bookingScheduleRuleVoList", bookingScheduleRuleVoList);
        mappedResults.put("total", total);

        String hosname = hospitalService.getHospitalNameByHoscode(hoscode);
        HashMap<String, String> baseMap = new HashMap<>();
        baseMap.put("hosname", hosname);
        mappedResults.put("baseMap", baseMap);

        return mappedResults;
    }

    private String getDayOfWeek(DateTime dateTime) {
        String dayOfWeek = "";
        switch (dateTime.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "Sunday";
                break;
            case DateTimeConstants.MONDAY:
                dayOfWeek = "Monday";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "Tuesday";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "Wednesday";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "Thursday";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "Friday";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "Saturday";
            default:
                break;
        }
        return dayOfWeek;
    }
}
