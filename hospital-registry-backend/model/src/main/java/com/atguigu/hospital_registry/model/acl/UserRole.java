package com.atguigu.hospital_registry.model.acl;

import com.atguigu.hospital_registry.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * User Role
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "User Role")
@TableName("acl_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Role ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "User ID")
    @TableField("user_id")
    private Long userId;

}

