package com.lcc.controller;

import com.alibaba.fastjson.JSON;
import com.lcc.service.impl.JWTServiceImpl;
import com.lcc.vo.JwtToken;
import com.lcc.vo.LoginUserInfo;
import com.lcc.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichaochao
 * @data 2022/4/12 5:17 PM
 **/
@Slf4j
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private final JWTServiceImpl jwtServiceImpl;

    @Autowired
    public AuthorityController(JWTServiceImpl jwtServiceImpl) {
        this.jwtServiceImpl = jwtServiceImpl;
    }

    /**
     * 登录返回token
     *
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    @PostMapping("crateToken")
    public JwtToken token(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        log.info(" request to get token with param : [{}]", JSON.toJSONString(usernameAndPassword));
        return new JwtToken(jwtServiceImpl.registerUserAndGenerateToken(usernameAndPassword));
    }


    /**
     * 创建用户返回token
     *
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    @PostMapping("crateUser")
    public JwtToken crateUser(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        log.info(" request to create  user  with param : [{}]", JSON.toJSONString(usernameAndPassword));
        return new JwtToken(jwtServiceImpl.registerUserAndGenerateToken(usernameAndPassword));
    }

    /**
     * 测试解析token
     *
     * @param token
     * @return
     */
    @PostMapping("analysisToken")
    public LoginUserInfo analysisToken(@RequestBody String token) {
        return jwtServiceImpl.JWTTest(token);
    }


    @GetMapping("forward")
    public String forward() {
        return "转发成功";
    }


}
