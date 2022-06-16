package com.lcc.constant;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

/**
 * <h1>网关常量定义</h1>
 */
public class GatewayConstant {

    /**
     * 登录 uri
     */
    public static final String LOGIN_URI = "/e-commerce/login";

    /**
     * 注册 uri
     */
    public static final String REGISTER_URI = "/e-commerce/crateUser";

    /**
     * 去授权中心拿到登录 token 的 uri 格式化接口
     */
    public static final String AUTHORITY_CENTER_TOKEN_URL_FORMAT =
            "http://%s:%s/ecommerce-authority-center/authority/crateToken";

    /**
     * 去授权中心注册并拿到 token 的 uri 格式化接口
     */
    public static final String AUTHORITY_CENTER_REGISTER_URL_FORMAT =
            "http://%s:%s/ecommerce-authority-center/authority/crateUser";

//    public static void main(String[] args) throws NacosException {
//                String serverAddr="127.0.0.1:8848";
//                String namespace = "1bbeca75-ae44-4ffb-af59-c07494b3685e";
//                String group ="DEFAULT_GROUP";
//                String dataId= "Authority";
//                Properties properties = new Properties();
//                properties.put("serverAddr",serverAddr);
//                properties.put("namespace",namespace);
//                ConfigService configService = NacosFactory.createConfigService(properties);
//                String config = configService.getConfig(dataId, group,5000);
//                System.out.println(config);
//    }
}
