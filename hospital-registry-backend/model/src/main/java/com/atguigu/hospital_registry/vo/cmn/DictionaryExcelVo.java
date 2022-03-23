package com.atguigu.hospital_registry.vo.cmn;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
public class DictionaryExcelVo {

	@ExcelProperty(value = "ID" ,index = 0)
	private Long id;

	@ExcelProperty(value = "Supervisor ID" ,index = 1)
	private Long parentId;

	@ExcelProperty(value = "Name" ,index = 2)
	private String name;

	@ExcelProperty(value = "Value" ,index = 3)
	private String value;

	@ExcelProperty(value = "Code" ,index = 4)
	private String dictCode;

}

