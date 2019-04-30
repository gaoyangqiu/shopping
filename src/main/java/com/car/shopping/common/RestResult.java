package com.car.shopping.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 72038667
 * @Date: 2019/3/16 12:05
 * @Version: 1.0
 */

public class RestResult {
    /**
     * 成功或者失败的code错误码
     */
    private String code;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    private Object data;
    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    private Object errorMessage;


    private Long count=0L;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public static RestResult success(){
        return new RestResult(ErrorMessage.SUCCESS.getCode(),null,null,null);


    }

    public RestResult(String code, Object data, Object errorMessage, Long count) {
        this.code = code;
        this.data = data;
        this.errorMessage = errorMessage;
        this.count = count;
    }

    public RestResult() {
    }
}
