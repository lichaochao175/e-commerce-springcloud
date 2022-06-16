package com.imooc.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.ecommerce.account.BalanceInfo;
import com.imooc.ecommerce.domian.entity.EcommerceBalance;

/**
 * @author lichaochao
 * @data 2022/6/14 1:19 PM
 **/
public interface EcommerceBalanceService extends IService<EcommerceBalance> {

    /**
     * <h2>获取当前用户余额信息</h2>
     */
    BalanceInfo getCurrentUserBalanceInfo();

    /**
     * <h2>扣减用户余额</h2>
     *
     * @param balanceInfo 代表想要扣减的余额
     */
    BalanceInfo deductBalance(BalanceInfo balanceInfo);

}
