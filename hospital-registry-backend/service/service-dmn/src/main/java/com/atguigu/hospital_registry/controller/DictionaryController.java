package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.atguigu.hospital_registry.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "Data Dictionary Interface")
//@CrossOrigin
@RestController
@RequestMapping("/admin/dmn/dictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    @ApiOperation(value = "Query sub-list according to ID")
    @GetMapping("/child-data/{parentId}")
    public Result getChildData(@PathVariable Long parentId) {
        List<Dictionary> list = dictionaryService.getChildData(parentId);
        return Result.ok(list);
    }

    @ApiOperation(value = "Get sub nodes according to dict_code")
    @GetMapping("/sub-nodes/{dictCode}")
    public Result getSubNodesByDictCode(@PathVariable String dictCode) {
        List<Dictionary> list = dictionaryService.getSubNodesByDictCode(dictCode);
        return Result.ok(list);
    }

    /**
     * multipartFile would always be null without "@RequestParam("file")"
     */
    @PostMapping("/data-all-import")
    public Result importDictData(@RequestParam("file") MultipartFile multipartFile) {
        dictionaryService.importDictData(multipartFile);
        return Result.ok();
    }

    @GetMapping("/data-all-export")
    public void exportDictData(HttpServletResponse response) {
        dictionaryService.exportDictData(response);
    }

    @GetMapping("/dict-name/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value) {

        return dictionaryService.getDictionaryName(dictCode, value);
    }

    @GetMapping("/dict-name/{value}")
    public String getName(@PathVariable String value) {

        return dictionaryService.getDictionaryName("", value);
    }

}
