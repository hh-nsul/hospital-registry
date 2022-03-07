package com.atguigu.hospital_registry.model.hosp;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * RegisterRule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Booking Rule")
@Document("BookingRule")
public class BookingRule {
	
	@ApiModelProperty(value = "Booking Period")
	private Integer cycle;

	@ApiModelProperty(value = "Release Time")
	private String releaseTime;

	@ApiModelProperty(value = "Cut-off Time")
	private String stopTime;

	@ApiModelProperty(value = "Cancel Days（如：就诊前一天为-1，当天为0）")
	private Integer quitDay;

	@ApiModelProperty(value = "Cancel Time")
	private String quitTime;

	@ApiModelProperty(value = "Booking Rules")
	private List<String> rule;

	/**
	 *
	 * @param rule
	 */
	public void setRule(String rule) {
		if(!StringUtils.isEmpty(rule)) {
			this.rule = JSONArray.parseArray(rule, String.class);
		}
	}

}

