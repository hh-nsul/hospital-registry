package com.atguigu.hospital_registry.model.hosp;

import com.atguigu.hospital_registry.model.base.BaseMongoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Schedule
 * </p>
 *
 * @author qy
 */

@Data
@ApiModel(description = "Schedule")
@Document("Schedule")
public class Schedule extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Hospital Code")
    @Indexed //普通索引
    private String hoscode;

    @ApiModelProperty(value = "Department Code")
    @Indexed //普通索引
    private String depcode;

    @ApiModelProperty(value = "Title")
    private String title;

    @ApiModelProperty(value = "Doctor Name")
    private String docname;

    @ApiModelProperty(value = "Skill")
    private String skill;

    @ApiModelProperty(value = "Shift Schedule Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    @ApiModelProperty(value = "Shift Schedule Time（0: AM, 1: PM）")
    private Integer workTime;

    @ApiModelProperty(value = "Total Reservation Numbers")
    private Integer reservedNumber;

    @ApiModelProperty(value = "Available Reservation Numbers")
    private Integer availableNumber;

    @ApiModelProperty(value = "Registration Fee")
    private BigDecimal amount;

    @ApiModelProperty(value = "Shift Schedule Status（-1: Medical Care Suspended 0: Registration Suspended 1: Available）")
    private Integer status;

    // @ApiModelProperty(value = "排班编号（医院自己的排班主键）")
    @ApiModelProperty(value = "Shift Schedule Code")
    @Indexed //ordinary index
    private String hosScheduleId;

}

