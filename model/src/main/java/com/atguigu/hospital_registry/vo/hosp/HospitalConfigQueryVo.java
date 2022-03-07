package com.atguigu.hospital_registry.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HospitalConfigQueryVo {

    @ApiModelProperty(value = "医院名称")
    private String hosname;

    @ApiModelProperty(value = "医院编号")
    private String hoscode;
}
