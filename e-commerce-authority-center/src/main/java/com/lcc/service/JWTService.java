package com.lcc.service;

import com.lcc.vo.UsernameAndPassword;

/**
 * @author lichaochao
 * @data 2022/4/12 3:00 PM
 * jwt 相关服务接口定义
 **/
public interface JWTService {

    /**
     * 生成JWT Token ,使用默认的超时时间
     *
     * @param usernameAndPassword
     * @return
     */
    String generateToken(UsernameAndPassword usernameAndPassword) throws Exception;

    /**
     * 生成指定超时时间
     * 单位是天
     *
     * @param qto
     * @param expire
     * @return
     */
    String generateToken(UsernameAndPassword qto, Integer expire) throws Exception;


    /**
     * 注册用户并生成token
     *
     * @param usernameAndPassword
     * @return
     */
    String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception;
}
