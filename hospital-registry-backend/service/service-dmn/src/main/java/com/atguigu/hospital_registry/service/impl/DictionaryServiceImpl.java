package com.atguigu.hospital_registry.service.impl;

import com.atguigu.hospital_registry.mapper.DictMapper;
import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.atguigu.hospital_registry.service.DictionaryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictMapper, Dictionary> implements DictionaryService {

    @Override
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

    private boolean hasChildren(Long parent_id) {
        QueryWrapper<Dictionary> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", parent_id);
        Integer count = baseMapper.selectCount(dictQueryWrapper);
        return count > 0;
    }
}
