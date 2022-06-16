package com.lcc.controller;

import com.lcc.service.NacosClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lichaochao
 * @data 2022/4/11 6:01 PM
 **/
@Slf4j
@RestController
@RequestMapping("/nacos-client")
public class NacosController {

    private NacosClientService nacosClientService;

    @Autowired
    public NacosController(NacosClientService nacosClientService) {
        this.nacosClientService = nacosClientService;
    }


    /**
     * 根据serviceid 获取id下所有服务信息
     *
     * @param serviceId
     * @return
     * @RequestParam(defaultValue = "e-commerce-nacos-client") //设置默认值
     */
    @RequestMapping("/serivce-instance")  // todo  不传参数不走IllegalStateException 全局捕获异常
    public List<ServiceInstance> logNacosClientInfo(@RequestParam(defaultValue = "e-commerce-nacos-client") String serviceId) {
        log.info("coming in log nacos client info [{}]", serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }
}
