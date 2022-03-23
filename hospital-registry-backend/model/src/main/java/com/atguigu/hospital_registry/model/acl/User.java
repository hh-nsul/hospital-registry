package com.atguigu.hospital_registry.model.acl;

import com.atguigu.hospital_registry.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "User")
@TableName("acl_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Username")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "Password")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "Nickname")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "Avatar")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "Signature")
    @TableField("token")
    private String token;

}



