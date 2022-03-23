package com.atguigu.hospital_registry.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.hospital_registry.mapper.DictMapper;
import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.atguigu.hospital_registry.vo.cmn.DictionaryExcelVo;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

public class DictionaryListener extends AnalysisEventListener<DictionaryExcelVo> {

    @Resource
    private DictMapper dictMapper;

    public DictionaryListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictionaryExcelVo dictionaryExcelVo, AnalysisContext analysisContext) {
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(dictionaryExcelVo, dictionary);
        dictMapper.insert(dictionary);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
