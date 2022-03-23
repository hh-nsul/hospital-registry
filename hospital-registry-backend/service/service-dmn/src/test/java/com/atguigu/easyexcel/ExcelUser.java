package com.atguigu.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelUser {

    @ExcelProperty(value = "User ID", index = 0)
    private Long id;

    @ExcelProperty(value = "User Name", index = 1)
    private String username;
}
