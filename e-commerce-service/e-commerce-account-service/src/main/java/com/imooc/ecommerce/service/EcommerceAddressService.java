package com.imooc.ecommerce.service;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.ecommerce.account.AddressInfo;
import com.imooc.ecommerce.domian.entity.EcommerceAddress;

/**
 * @author lichaochao
 * @data 2022/6/14 1:12 PM
 **/
public interface EcommerceAddressService extends IService<EcommerceAddress> {

    /**
     * <h2>创建用户地址信息</h2>
     */
    TableId createAddressInfo(AddressInfo addressInfo);

    /**
     * <h2>获取当前登录的用户地址信息</h2>
     */
    AddressInfo getCurrentAddressInfo();

    /**
     * <h2>通过 id 获取用户地址信息, id 是 EcommerceAddress 表的主键</h2>
     */
    AddressInfo getAddressInfoById(Long id);

    /**
     * <h2>通过 TableId 获取用户地址信息</h2>
     */
    AddressInfo getAddressInfoByTableId(TableId tableId);

}
