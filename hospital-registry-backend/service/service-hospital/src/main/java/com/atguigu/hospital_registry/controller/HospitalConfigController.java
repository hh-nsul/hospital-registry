package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.common.exception.HospitalRegistryException;
import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.common.util.MD5;
import com.atguigu.hospital_registry.model.hosp.HospitalConfig;
import com.atguigu.hospital_registry.service.HospitalConfigService;
import com.atguigu.hospital_registry.vo.hosp.HospitalConfigQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/admin/hospital/hospitalConfig")
public class HospitalConfigController {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Resource
    private HospitalConfigService hospitalConfigService;

    // http://localhost:8201/admin/hospital/hospitalConfig/findAll
    @GetMapping("/findAll")
    public Result findAllHospitalConfig() {
        List<HospitalConfig> hospitalConfigList = hospitalConfigService.list();
        return Result.ok(hospitalConfigList);
    }

    @GetMapping("/findHospitalConfig/{id}")
    public Result findHospitalConfigById(@PathVariable Long id) {
        HospitalConfig hospitalConfig = hospitalConfigService.getById(id);
        return Result.ok(hospitalConfig);
    }

    @PostMapping("/findByPage/{currPageNum}/{recordsNum}")
    public Result findHospitalConfigWithPagination(@PathVariable Long currPageNum,
                                                   @PathVariable Long recordsNum,
                                                   @RequestBody(required = false) HospitalConfigQueryVo hospitalConfigQueryVo) {
        Page<HospitalConfig> page = new Page<>(currPageNum, recordsNum);

        QueryWrapper<HospitalConfig> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(hospitalConfigQueryVo.getHosname())) {
            queryWrapper.like("hosname", hospitalConfigQueryVo.getHosname());
        }

        if (!StringUtils.isEmpty(hospitalConfigQueryVo.getHoscode())) {
            queryWrapper.eq("hoscode", hospitalConfigQueryVo.getHoscode());
        }

        Page<HospitalConfig> pageQuery = hospitalConfigService.page(page, queryWrapper);
        return Result.ok(pageQuery);
    }

    @PostMapping("/createHospitalConfig")
    public Result createHospitalConfig(@RequestBody HospitalConfig hospitalConfig) {
        hospitalConfig.setStatus(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis()).append(RANDOM.nextInt(1000));
        hospitalConfig.setSignKey(MD5.encrypt(stringBuilder.toString()));

        boolean saved = hospitalConfigService.save(hospitalConfig);
        return saved ? Result.ok() : Result.fail();
    }

    @PostMapping("/updateHospitalConfig")
    public Result updateHospitalConfig(@RequestBody HospitalConfig hospitalConfig) {
        boolean updated = hospitalConfigService.updateById(hospitalConfig);
        return updated ? Result.ok() : Result.fail();
    }

    @PutMapping("/lockHospitalConfig/{id}/{status}")
    public Result lockHospitalConfig(@PathVariable Long id,
                                     @PathVariable Integer status) {
        HospitalConfig hospitalConfig = hospitalConfigService.getById(id);
        hospitalConfig.setStatus(status);
        boolean updated = hospitalConfigService.updateById(hospitalConfig);
        return updated ? Result.ok() : Result.fail();
    }

    @PutMapping("/sendHospitalConfigKey/{id}")
    public Result sendHospitalConfigKey(@PathVariable Long id) {
        HospitalConfig hospitalConfig = hospitalConfigService.getById(id);
        String signKey = hospitalConfig.getSignKey();
        String hoscode = hospitalConfig.getHoscode();
        // TODO: Send a message
        return Result.ok();
    }

    @DeleteMapping("/removeBatch")
    public Result removeHospitalConfigBatch(@RequestBody List<Long> idList) {
        boolean removed = hospitalConfigService.removeByIds(idList);
        return removed ? Result.ok() : Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result removeHospitalConfig(@PathVariable Long id) {
        boolean removed = hospitalConfigService.removeById(id);
        return removed ? Result.ok() : Result.fail();
    }
}
