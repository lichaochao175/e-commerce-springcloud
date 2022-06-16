package com.lcc.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author lichaochao
 * @data 2022/4/13 2:58 PM
 * 局部过滤器生效：
 * 1。将局部过滤器添加到过滤器工厂
 * 2。需要配置文件中配置
 * 检查是否携带token
 **/
public class HeaderTokenGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //从http Header 中寻找key 为token , value为imooc 的键值对
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if ("imooc".equals(token)) {
            return chain.filter(exchange);
        }
        //否则标记此次请求没有权限 ，并结束这次请求
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        //优先级   值越大 优先级越低
        return HIGHEST_PRECEDENCE + 1;
    }
}
