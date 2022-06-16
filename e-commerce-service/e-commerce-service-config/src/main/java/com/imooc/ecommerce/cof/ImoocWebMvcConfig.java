package com.imooc.ecommerce.cof;

import com.imooc.ecommerce.fillter.LoginUserInfoInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author lichaochao
 * @data 2022/6/11 10:45 AM
 * Web Mvc配置
 **/
public class ImoocWebMvcConfig extends WebMvcConfigurationSupport {


    //添加拦截器配置
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //添加用户身份统一登录拦截的拦截器
        // addPathPatterns ：针对拦截的url
        //order 拦截的顺序
        registry.addInterceptor(new LoginUserInfoInterceptor())
                .addPathPatterns("/**")
                .order(0);
//        super.addInterceptors(registry);
    }

    /**
     * <h2>让 MVC 加载 Swagger 的静态资源</h2>
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }
}
