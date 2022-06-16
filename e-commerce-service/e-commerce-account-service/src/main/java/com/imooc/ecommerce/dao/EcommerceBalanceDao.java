package com.imooc.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.ecommerce.domian.entity.EcommerceBalance;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lichaochao
 * @data 2022/6/14 1:19 PM
 **/
@Mapper
public interface EcommerceBalanceDao extends BaseMapper<EcommerceBalance> {
    /**
     * 根据 userId 查询 EcommerceBalance 对象
     */
    EcommerceBalance findByUserId(Long userId);

}
