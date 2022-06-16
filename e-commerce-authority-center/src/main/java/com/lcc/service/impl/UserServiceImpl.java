package com.lcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcc.domain.po.UserPO;
import com.lcc.mapper.UserMapper;
import com.lcc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lichaochao
 * @data 2022/4/12 1:41 PM
 **/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


}
