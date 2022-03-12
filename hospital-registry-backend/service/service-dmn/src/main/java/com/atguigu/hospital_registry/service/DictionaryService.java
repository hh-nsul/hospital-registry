package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DictionaryService extends IService<Dictionary> {
    List<Dictionary> findChildData(Long parentId);
}
