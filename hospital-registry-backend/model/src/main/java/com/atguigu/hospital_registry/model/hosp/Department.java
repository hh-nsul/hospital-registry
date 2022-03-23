package com.atguigu.hospital_registry.model.hosp;

import com.atguigu.hospital_registry.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * Department
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Department")
@Document("Department")
public class Department extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Hospital Code")
    @Indexed //ordinary index
    private String hoscode;

    @ApiModelProperty(value = "Department Code")
    @Indexed(unique = true) //唯一索引
    private String depcode;

    @ApiModelProperty(value = "Department Name")
    private String depname;

    @ApiModelProperty(value = "Department Description")
    private String intro;

    @ApiModelProperty(value = "Large Department Code")
    private String bigcode;

    @ApiModelProperty(value = "Large Department Name")
    private String bigname;

}

