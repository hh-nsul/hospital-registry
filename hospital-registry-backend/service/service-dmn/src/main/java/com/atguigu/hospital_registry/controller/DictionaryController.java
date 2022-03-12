package com.atguigu.hospital_registry.controller;

import com.atguigu.hospital_registry.common.result.Result;
import com.atguigu.hospital_registry.model.dmn.Dictionary;
import com.atguigu.hospital_registry.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "Data Dictionary Interface")
@CrossOrigin
@RestController
@RequestMapping("/admin/dmn/dictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    @ApiOperation(value = "Query sub-list according to ID")
    @GetMapping("child-data/{parent-id}")
    public Result getChildData(@PathVariable("parent-id") Long parentId) {
        List<Dictionary> list = dictionaryService.findChildData(parentId);
        return Result.ok(list);
    }
}
