package com.imooc.ecommerce.service.async;

import com.imooc.ecommerce.goods.GoodsInfo;

import java.util.List;

/**
 * @author lichaochao
 * @data 2022/6/17 8:56 AM
 * 异步服务接口定义
 **/
public interface IAsyncService {

    /**
     * 异步将商品信息保存下来
     */
    void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId);
}
