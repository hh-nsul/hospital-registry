package com.atguigu.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelUser> {

    /**
     * Read Excel header
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    /**
     * Read Excel table row by row, skipping table header
     */
    @Override
    public void invoke(ExcelUser excelUser, AnalysisContext analysisContext) {
        System.out.println(excelUser);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
