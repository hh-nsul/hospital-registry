package com.atguigu.hospital_registry.model.hosp;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.hospital_registry.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * Hospital
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Hospital")
@Document("Hospital")
public class Hospital extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Hospital Code")
	@Indexed(unique = true) //unique index
	private String hoscode;

	@ApiModelProperty(value = "Hospital Name")
	@Indexed //ordinary index
	private String hosname;

	@ApiModelProperty(value = "Hospital Type")
	private String hostype;

	@ApiModelProperty(value = "Province Code")
	private String provinceCode;

	@ApiModelProperty(value = "City Code")
	private String cityCode;

	@ApiModelProperty(value = "District Code")
	private String districtCode;

	@ApiModelProperty(value = "Address")
	private String address;

	@ApiModelProperty(value = "Hospital Logo")
	private String logoData;

	@ApiModelProperty(value = "Hospital Intro")
	private String intro;

	@ApiModelProperty(value = "Route")
	private String route;

	@ApiModelProperty(value = "Status 0: Offline 1: Online")
	private Integer status;

	@ApiModelProperty(value = "Booking Rule")
	private BookingRule bookingRule;

	public void setBookingRule(String bookingRule) {
		this.bookingRule = JSONObject.parseObject(bookingRule, BookingRule.class);
	}

}

