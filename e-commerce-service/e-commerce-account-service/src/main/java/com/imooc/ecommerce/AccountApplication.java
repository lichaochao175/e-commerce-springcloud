package com.imooc.ecommerce;

import com.imooc.ecommerce.cof.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author lichaochao
 * @data 2022/6/11 2:14 PM
 * 用户账户微服务启动入口
 **/
@EnableDiscoveryClient
@SpringBootApplication
/**
 *  不导入DataSourceProxyAutoConfiguration 则会报错
 * Error creating bean with name 'jmxMBeanExporter' defined in class path resource
 */
@Import(DataSourceProxyAutoConfiguration.class)
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
