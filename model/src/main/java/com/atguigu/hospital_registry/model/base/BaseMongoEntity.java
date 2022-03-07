package com.atguigu.hospital_registry.model.base;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseMongoEntity implements Serializable {

    @ApiModelProperty(value = "ID")
    @Id
    private String id;

    @ApiModelProperty(value = "Creation Time")
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    private Date updateTime;

    @ApiModelProperty(value = "Logical Deletion(1:Deleted，0:Not Deleted)")
    private Integer isDeleted;

    @ApiModelProperty(value = "Other Parameters")
    @Transient //被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性
    private Map<String,Object> param = new HashMap<>();
}
