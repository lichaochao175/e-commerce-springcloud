package com.lcc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcc.domain.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lichaochao
 * @data 2022/4/12 1:40 PM
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
