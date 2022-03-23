package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

public class TestRead {

    public static void main(String[] args) {
        String filePath = "D:\\excel\\01.xlsx";
        EasyExcel.read(filePath, ExcelUser.class, new ExcelListener()).sheet().doRead();
    }
}
