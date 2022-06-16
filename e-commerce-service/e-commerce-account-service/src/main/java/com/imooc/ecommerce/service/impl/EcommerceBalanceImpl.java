package com.imooc.ecommerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.ecommerce.account.BalanceInfo;
import com.imooc.ecommerce.dao.EcommerceBalanceDao;
import com.imooc.ecommerce.domian.entity.EcommerceBalance;
import com.imooc.ecommerce.service.EcommerceBalanceService;
import org.springframework.stereotype.Service;

/**
 * @author lichaochao
 * @data 2022/6/14 1:19 PM
 **/
@Service
public class EcommerceBalanceImpl extends ServiceImpl<EcommerceBalanceDao, EcommerceBalance> implements EcommerceBalanceService {
    @Override
    public BalanceInfo getCurrentUserBalanceInfo() {
        return null;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {
        return null;
    }

}
