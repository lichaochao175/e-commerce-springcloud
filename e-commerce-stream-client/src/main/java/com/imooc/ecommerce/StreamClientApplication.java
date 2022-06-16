package com.imooc.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lichaochao
 * @data 2022/6/16 4:07 PM
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class StreamClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamClientApplication.class, args);
    }
}
