package com.atguigu.hospital_registry.model.hosp;

import com.atguigu.hospital_registry.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * HospitalConfig
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Hospital Configuration")
@TableName("hospital_config")
public class HospitalConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Hospital Name")
    @TableField("hosname")
    private String hosname;

    @ApiModelProperty(value = "Hospital Code")
    @TableField("hoscode")
    private String hoscode;

    @ApiModelProperty(value = "API URL")
    @TableField("api_url")
    private String apiUrl;

    @ApiModelProperty(value = "Sign Key")
    @TableField("sign_key")
    private String signKey;

    @ApiModelProperty(value = "Contact Name")
    @TableField("contacts_name")
    private String contactsName;

    @ApiModelProperty(value = "Contact Phone")
    @TableField("contacts_phone")
    private String contactsPhone;

    @ApiModelProperty(value = "Status")
    @TableField("status")
    private Integer status;

}

