package com.atguigu.hospital_registry.dmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-dmn")
@Service
public interface DictionaryFeignClient {

    @GetMapping("/admin/dmn/dictionary/dict-name/{dictCode}/{value}")
    public String getName(@PathVariable("dictCode") String dictCode,
                          @PathVariable("value") String value);

    @GetMapping("/admin/dmn/dictionary/dict-name/{value}")
    public String getName(@PathVariable("value") String value);
}
