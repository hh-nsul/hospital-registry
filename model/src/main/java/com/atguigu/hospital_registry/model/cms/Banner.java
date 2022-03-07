package com.atguigu.hospital_registry.model.cms;

import com.atguigu.hospital_registry.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 首页Banner实体
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "Homepage Banner")
@TableName("banner")
public class Banner extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Title")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "Image URL")
	@TableField("image_url")
	private String imageUrl;

	@ApiModelProperty(value = "Link URL")
	@TableField("link_url")
	private String linkUrl;

	@ApiModelProperty(value = "Sort")
	@TableField("sort")
	private Integer sort;

}

