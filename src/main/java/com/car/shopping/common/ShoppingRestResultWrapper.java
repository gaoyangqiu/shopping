package com.car.shopping.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;


@ControllerAdvice(basePackages = "com.car.shopping.controller")
public class ShoppingRestResultWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Annotation type= methodParameter.getMethod().getAnnotation(ResponseBody.class);
        return type!=null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        RestResult result=new RestResult();
        if (o instanceof RestResult||o instanceof ModelAndView){
            return o;
        }else {
            result = new RestResult(ErrorMessage.SUCCESS.getCode().toString(), o, null,null);
        }
        return JSONObject.toJSON(result);
    }
}
