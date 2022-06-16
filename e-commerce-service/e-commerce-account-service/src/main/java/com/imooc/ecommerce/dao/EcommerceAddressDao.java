package com.imooc.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.ecommerce.domian.entity.EcommerceAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lichaochao
 * @data 2022/6/14 1:12 PM
 **/
@Mapper
public interface EcommerceAddressDao extends BaseMapper<EcommerceAddress> {

    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     */
    List<EcommerceAddress> findAllByUserId(Long userId);

}
