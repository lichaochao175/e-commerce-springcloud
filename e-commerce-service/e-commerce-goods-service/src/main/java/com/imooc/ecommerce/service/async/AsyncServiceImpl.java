package com.imooc.ecommerce.service.async;

import com.imooc.ecommerce.dao.EcommerceGoodsDao;
import com.imooc.ecommerce.goods.GoodsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lichaochao
 * @data 2022/6/17 8:58 AM
 * 异步服务接口实现
 **/
@Slf4j
@Service
@Transactional
public class AsyncServiceImpl implements IAsyncService {

    private final EcommerceGoodsDao ecommerceGoodsDao;
    private final StringRedisTemplate redisTemplate;

    public AsyncServiceImpl(EcommerceGoodsDao ecommerceGoodsDao,
                            StringRedisTemplate redisTemplate) {
        this.ecommerceGoodsDao = ecommerceGoodsDao;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId) {

    }
}
