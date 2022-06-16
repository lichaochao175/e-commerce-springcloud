package com.imooc.ecommerce.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.ecommerce.account.AddressInfo;
import com.imooc.ecommerce.dao.EcommerceAddressDao;
import com.imooc.ecommerce.domian.entity.EcommerceAddress;
import com.imooc.ecommerce.service.EcommerceAddressService;
import org.springframework.stereotype.Service;

/**
 * @author lichaochao
 * @data 2022/6/14 1:13 PM
 **/
@Service
public class EcommerceAddressImpl extends ServiceImpl<EcommerceAddressDao, EcommerceAddress> implements EcommerceAddressService {


    @Override
    public TableId createAddressInfo(AddressInfo addressInfo) {
        return null;
    }

    @Override
    public AddressInfo getCurrentAddressInfo() {
        return null;
    }

    @Override
    public AddressInfo getAddressInfoById(Long id) {
        return null;
    }

    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {
        return null;
    }
}
