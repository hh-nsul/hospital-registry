package com.atguigu.hospital_registry.model.acl;

import com.atguigu.hospital_registry.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * Role
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "Role")
@TableName("acl_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Role Name")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "Note")
    @TableField("remark")
    private String remark;

}

