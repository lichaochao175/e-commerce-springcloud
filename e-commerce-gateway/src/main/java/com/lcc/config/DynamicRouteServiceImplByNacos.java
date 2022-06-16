package com.lcc.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * <h1>通过 nacos 下发动态路由配置, 监听 Nacos 中路由配置变更</h1>
 * DependsOn：
 * 等指定初始化完成再初始化本类
 * 需要gatewayconfig加载完毕后再实现@bean注入
 * bean 中 类名首字母小写
 */
@Slf4j
@Component
@DependsOn({"gatewayConfig"})
public class DynamicRouteServiceImplByNacos {

    private final DynamicRouteServiceImpl dynamicRouteService;
    /**
     * Nacos 配置服务
     */
    private ConfigService configService;


    public DynamicRouteServiceImplByNacos(DynamicRouteServiceImpl dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    /**
     * Bean在容器中构造完成之后会执行init方法
     */
    @PostConstruct
    public void init() {
        log.info("gateway route init .....");

        try {
            //初始化Nacos配置客户端
            configService = initConfigService();
            if (null == configService) {
                //获取不到则返回null
                log.error("init config service fail");
                return;
            }

            //通过Nacos Config 并指定路由配置路径去获取路由配置
            String config = configService.getConfig(
                    GatewayConfig.NACOS_ROUTE_DATA_ID,
                    GatewayConfig.NACOS_ROUTE_GROUP,
                    GatewayConfig.DEFAULT_TIMEOUT
            );

            log.info("get current gateway config:[{}]", config);
            List<RouteDefinition> definitionList =
                    JSON.parseArray(config, RouteDefinition.class);
            if (CollectionUtils.isNotEmpty(definitionList)) {
                for (RouteDefinition definition : definitionList) {
                    log.info("init gateway config: [{}]", definition.toString());
                    dynamicRouteService.addRouteDefinition(definition);
                }
            }
        } catch (Exception ex) {
            log.error("gateway route init has some error: [{}]", ex.getMessage(), ex);
        }
        // 设置监听器
        dynamicRouteByNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID,
                GatewayConfig.NACOS_ROUTE_GROUP);
    }

    /**
     * 初始化 Nacos config
     *
     * @return
     */
    private ConfigService initConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", GatewayConfig.NACOS_SERVER_ADDR);
            properties.setProperty("namespace", GatewayConfig.NACOS_NAMESPACE);
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception ex) {
            log.error("init  gateway nacos config error: [{}]", ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 监听Nacos 下发的动态路由配置
     */
    private void dynamicRouteByNacosListener(String dataId, String group) {

        try {
            // 添加监听器
            configService.addListener(dataId, group, new Listener() {

                /**
                 * 自己提供线程池执行操作，不提供则使用nacos默认线程池
                 * @return
                 */
                @Override
                public Executor getExecutor() {
                    return null;
                }

                /**
                 * 监听器收到配置更新
                 * @param configInfo
                 */
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("start to update config:[{}]", configInfo);
                    List<RouteDefinition> definitionList =
                            JSON.parseArray(configInfo, RouteDefinition.class);
                    log.info("update route :[{}]", definitionList.toString());
                    dynamicRouteService.updateList(definitionList);
                }
            });
        } catch (Exception ex) {
            log.error("dynamic update gateway config error: [{}]", ex.getMessage(), ex);
        }
    }
}
