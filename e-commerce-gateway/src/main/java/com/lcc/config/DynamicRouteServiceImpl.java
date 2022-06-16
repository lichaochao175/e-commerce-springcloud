package com.lcc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 事件推送 Aware: 动态更新路由网关 Service
 * spring 提供事件接口ApplicationEventPublisherAware
 */
@Slf4j
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    /**
     * 获取路由定义
     */
    private final RouteDefinitionLocator routeDefinitionLocator;

    /**
     * 写路由定义
     */
    private final RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 事件发布=》监听
     */
    private ApplicationEventPublisher publisher;

    public DynamicRouteServiceImpl(RouteDefinitionLocator routeDefinitionLocator,
                                   RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionLocator = routeDefinitionLocator;
        this.routeDefinitionWriter = routeDefinitionWriter;
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //完成事件推送句柄的初始化
        this.publisher = applicationEventPublisher;
    }


    /**
     * 增加路由定义
     *
     * @param definition
     * @return
     */
    public String addRouteDefinition(RouteDefinition definition) {
        log.info("gateway add route : [{}]", definition);

        //保存路由配置并发布 刷新配置
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        //发布事件通知给Gateway ，同步新增的路由定义
        this.publisher.publishEvent(new RefreshRoutesEvent(this));

        //todo 修改返回结果
        return "success";
    }

    /**
     * 更新路由 =》批量
     *
     * @param definitionList
     * @return
     */
    public String updateList(List<RouteDefinition> definitionList) {

        log.info("gateway update route:[{}]", definitionList);

        //先拿到当前gateway中存储的路由定义
        List<RouteDefinition> routeDefinitions =
                routeDefinitionLocator.getRouteDefinitions().buffer().blockFirst();
        if (!CollectionUtils.isEmpty(routeDefinitions)) {
            //获取不等于空则清除所有旧的路由
            routeDefinitions.forEach(rd -> {
                log.info("开始清除旧的路由定义:[{}]", rd);
                deleteById(rd.getId());
            });
        }
        //把更新的路由定义同步到gateway中
        definitionList.forEach(rd -> updateByRouteDefinition(rd));
        return "success";
    }

    /**
     * 根据id删除路由配置
     *
     * @param id
     * @return
     */
    private String deleteById(String id) {
        try {
            log.info("根据路由id删除配置");
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            //发布事件通知给gatway 更新路由
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "delete success";
        } catch (Exception ex) {
            log.error("gateway delete route fail:[{}]", ex.getMessage(), ex);
            return "delete fail";
        }
    }

    /**
     * 更新的实现策略  删除+新增= 更新
     *
     * @param definition
     * @return
     */
    private String updateByRouteDefinition(RouteDefinition definition) {

        try {
            log.info("gateway update route [{}]", definition);
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception ex) {
            return "update fail, not find route routeId: " + definition.getId();
        }

        try {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception ex) {
            return "update route fail";
        }
    }
}
