package com.imooc.ecommerce;

import com.imooc.ecommerce.cof.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author lichaochao
 * @data 2022/6/16 3:39 PM
 **/
@Import(DataSourceProxyAutoConfiguration.class)
@SpringBootApplication
@EnableDiscoveryClient
public class LogisticsApplication {
    public static void main(String[] args) {

        SpringApplication.run(LogisticsApplication.class, args);
    }
}
