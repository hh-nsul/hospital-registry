package com.atguigu.hospital_registry.controller.api;

import com.atguigu.hospital_registry.common.exception.HospitalRegistryException;
import com.atguigu.hospital_registry.common.helper.HttpRequestHelper;
import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.common.result.ResultCodeEnum;
import com.atguigu.hospital_registry.common.util.MD5;
import com.atguigu.hospital_registry.model.hosp.Hospital;
import com.atguigu.hospital_registry.service.DepartmentService;
import com.atguigu.hospital_registry.service.HospitalConfigService;
import com.atguigu.hospital_registry.service.HospitalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private HospitalService hospitalService;

    @Resource
    private HospitalConfigService hospitalConfigService;

    @Resource
    private DepartmentService departmentService;

    @PostMapping("/hospital/save")
    public Result saveHospital(HttpServletRequest httpServletRequest) {
        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        // com.atguigu.hospital.service.impl.ApiServiceImpl.saveHospital()
        String hospitalSignKey = paramMap.get("sign").toString();

        String hoscode = paramMap.get("hoscode").toString();

        String signKey = hospitalConfigService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);

        if (!hospitalSignKey.equals(signKeyMd5)) {
            throw new HospitalRegistryException(ResultCodeEnum.SIGN_ERROR);
        }

        /**
         * Hospital logos are encoded via base64,
         * all the '+' was converted into ' ' during transmission,
         * here is to convert ' ' back
         */
        String logoData = paramMap.get("logoData").toString().replaceAll(" ", "+");
        paramMap.put("logoData", logoData);

        hospitalService.save(paramMap);
        return Result.ok();
    }

    @PostMapping("/hospital/show")
    public Result getHospital(HttpServletRequest httpServletRequest) {
        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = paramMap.get("hoscode").toString();

        String signKey = hospitalConfigService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);

        String hospitalSignKey = paramMap.get("sign").toString();

        if (!hospitalSignKey.equals(signKeyMd5)) {
            throw new HospitalRegistryException(ResultCodeEnum.SIGN_ERROR);
        }

        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    @PostMapping("/department/save")
    public Result saveDepartment(HttpServletRequest httpServletRequest) {
        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hospitalSignKey = paramMap.get("sign").toString();

        String hoscode = paramMap.get("hoscode").toString();

        String signKey = hospitalConfigService.getSignKey(hoscode);
        String signKeyMd5 = MD5.encrypt(signKey);

        if (!hospitalSignKey.equals(signKeyMd5)) {
            throw new HospitalRegistryException(ResultCodeEnum.SIGN_ERROR);
        }

        departmentService.save(paramMap);
        return Result.ok();
    }
}
