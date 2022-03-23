package com.atguigu.hospital_registry.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.hospital_registry.listener.DictionaryListener;
import com.atguigu.hospital_registry.mapper.DictMapper;
import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.atguigu.hospital_registry.service.DictionaryService;
import com.atguigu.hospital_registry.vo.cmn.DictionaryExcelVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictMapper, Dictionary> implements DictionaryService {

    @Override
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    public List<Dictionary> findChildData(Long parentId) {

        QueryWrapper<Dictionary> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", parentId);
        List<Dictionary> dictionaryList = baseMapper.selectList(dictQueryWrapper);

        for (Dictionary dictionary : dictionaryList) {
            boolean hasChildren = this.hasChildren(dictionary.getId());
            dictionary.setHasChildren(hasChildren);
        }
        return dictionaryList;
    }

    @Override
    public void exportDictData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = "Hospital Dictionary Data";
        response.setHeader("Content-disposition", "attachment:filename=" + fileName + ".xlsx");

        List<Dictionary> dictionaryList = baseMapper.selectList(null);

        List<DictionaryExcelVo> dictionaryExcelVoList = dictionaryList.stream()
                                                          .map(dictionary -> {
                                                              DictionaryExcelVo dictionaryExcelVo = new DictionaryExcelVo();
                                                              BeanUtils.copyProperties(dictionary, dictionaryExcelVo);

                                                               return dictionaryExcelVo;
                                                          })
                                                          .collect(Collectors.toList());

        try {
            EasyExcel.write(response.getOutputStream(), DictionaryExcelVo.class)
                     .sheet("Dictionary")
                     .doWrite(dictionaryExcelVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @CacheEvict(value = "dict", allEntries = true)
    public void importDictData(MultipartFile multipartFile) {
        try {
            EasyExcel.read(multipartFile.getInputStream(),
                           DictionaryExcelVo.class,
                           new DictionaryListener(baseMapper))
                     .sheet()
                     .doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean hasChildren(Long parent_id) {
        QueryWrapper<Dictionary> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", parent_id);
        Integer count = baseMapper.selectCount(dictQueryWrapper);
        return count > 0;
    }
}
