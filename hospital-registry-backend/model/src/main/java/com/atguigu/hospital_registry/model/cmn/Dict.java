package com.atguigu.hospital_registry.model.cmn;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "数据字典")
@TableName("dict")
public class Dict {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Creation Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "Logical Deletion(1:Deleted，0:Not Deleted)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "Other Parameters")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();

    @ApiModelProperty(value = "Superior ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "Name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "Value")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "Code")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "Child Nodes Contained")
    @TableField(exist = false)
    private boolean hasChildren;

}