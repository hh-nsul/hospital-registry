package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * https://stackoverflow.com/questions/48495043/failed-to-execute-goal-org-codehaus-mojoexec-maven-plugin1-6-0java
 * https://www.freesion.com/article/46021009132/
 */
public class TestWrite {

    public static void main(String[] args) {
        List<ExcelUser> userList = new ArrayList<>();
        for (int i = 0; i <= 7; ++i) {
            ExcelUser excelUser = new ExcelUser();
            excelUser.setId((long) i);
            excelUser.setUsername("user " + i);
            userList.add(excelUser);
        }

        String filePath = "D:\\excel\\01.xlsx";

        EasyExcel.write(filePath, ExcelUser.class)
                 .sheet("User Info")
                 .doWrite(userList);
    }
}
