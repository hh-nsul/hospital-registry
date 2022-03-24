package com.atguigu.hospital_registry.common.exception;

import com.atguigu.hospital_registry.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Custom Global Exception Handler Class
 *
 * @author qy
 */
@Data
@ApiModel(value = "Custom Global Exception Handler Class")
public class HospitalRegistryException extends RuntimeException {

    @ApiModelProperty(value = "Exception Status Code")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param message
     * @param code
     */
    public HospitalRegistryException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    public HospitalRegistryException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Hospital Registry Exception {")
               .append("code = ")
               .append(code)
               .append(", message = ")
               .append(this.getMessage())
               .append('}');

        return builder.toString();
    }
}
