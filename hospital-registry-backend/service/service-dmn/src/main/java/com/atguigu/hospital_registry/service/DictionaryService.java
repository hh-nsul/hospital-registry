package com.atguigu.hospital_registry.service;

import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictionaryService extends IService<Dictionary> {

    List<Dictionary> getChildData(Long parentId);

    void exportDictData(HttpServletResponse response);

    void importDictData(MultipartFile multipartFile);

    String getDictionaryName(String dictCode, String value);

    List<Dictionary> getSubNodesByDictCode(String dictCode);
}
