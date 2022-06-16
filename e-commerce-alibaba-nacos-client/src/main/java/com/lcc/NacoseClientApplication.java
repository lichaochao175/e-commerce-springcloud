package com.lcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lichaochao
 * @data 2022/4/11 2:59 PM
 * Nacos Client 工程入口
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class NacoseClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacoseClientApplication.class, args);
    }
}
