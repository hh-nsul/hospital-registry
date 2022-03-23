package com.atguigu.hospital_registry.model.acl;

import com.atguigu.hospital_registry.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * Permission
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "Permission")
@TableName("acl_permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Superior")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty(value = "Name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "Type(1:Menu, 2:Button)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "Permission Value")
    @TableField("permission_value")
    private String permissionValue;

    @ApiModelProperty(value = "Path")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "Component")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "Icon")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "Status(0:Unavailable, 1:Available)")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "Hierarchy")
    @TableField(exist = false)
    private Integer level;

    @ApiModelProperty(value = "Subordinate")
    @TableField(exist = false)
    private List<Permission> children;

    @ApiModelProperty(value = "Selected")
    @TableField(exist = false)
    private boolean isSelect;

}

