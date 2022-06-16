package com.lcc.advice;

import cn.hutool.http.server.HttpServerRequest;
import com.lcc.vo.CommomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lichaochao
 * @data 2022/4/11 11:13 AM
 * <p>
 * 设置全局捕获异常（ 其他 ：AOP， 过滤器 ， 拦截器）
 **/

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    //如果实现自定义异常

    @ExceptionHandler(Exception.class)
    public CommomResponse<String> handlerCommerceException(HttpServerRequest request, Exception e) {
        log.error("commerce service has error[{}]", e.getMessage(), e);
        //todo  e.getMessage()
        return CommomResponse.fail(-1, "抛异常", e.getMessage().toString());
    }


    @ExceptionHandler(IllegalStateException.class)
    public CommomResponse<String> handlerCommerceIllegalStateException(HttpServerRequest request, Exception e) {
        log.error("commerce service has error[{}]", e.getMessage(), e);
        //todo  e.getMessage()
        return CommomResponse.fail(-1, "抛异常", e.getMessage().toString());
    }
}
