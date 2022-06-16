package com.lcc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import sun.security.jca.ServiceId;

import java.util.List;

/**
 * @author lichaochao
 * @data 2022/4/11 5:56 PM
 **/

@Slf4j
@Service
public class NacosClientService {


    //由springcloud 提供接口
    private final DiscoveryClient discoveryClient;

    @Autowired
    public NacosClientService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    public List<ServiceInstance> getNacosClientInfo(String serviceId) {
        //通过命名空间id 获取信息
        log.info("request nacos  client to get service instance info :[{}]", serviceId);
        return discoveryClient.getInstances(serviceId);
    }
}
