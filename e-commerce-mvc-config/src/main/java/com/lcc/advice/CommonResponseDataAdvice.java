package com.lcc.advice;


import com.lcc.vo.CommomResponse;
import com.lcc.annotation.IgnoreResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author lichaochao
 * @data 2022/4/11 10:26 AM
 * 实现统一对RestController接口拦截
 * <p>
 * ResponseBodyAdvice<Object>     一定是object类型，其他类型会报错
 **/
@RestControllerAdvice(value = "com.lcc")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断响应数据是否被处理
     * 默认 false 不被处理
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    //屏蔽警告信息
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        //标记在类上
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        //标记在方法上
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return false;
    }


    /**
     * 在响应前处理 返回数据
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        //封装的结果集
        CommomResponse<Object> response = new CommomResponse<>(0, "");
        if (null == o) {
            return response;
        } else if (o instanceof CommomResponse) {
            response = (CommomResponse<Object>) o;
        } else {
            response.setData(o);
        }
        return response;
    }
}
