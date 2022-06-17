package com.imooc.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.ecommerce.constant.BrandCategory;
import com.imooc.ecommerce.constant.GoodsCategory;
import com.imooc.ecommerce.domain.entity.EcommerceGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * @author lichaochao
 * @data 2022/6/17 8:59 AM
 **/
@Mapper
public interface EcommerceGoodsDao extends BaseMapper<EcommerceGoods> {

    /**
     * 根据查询条件查询商品表 ，并限制返回结果
     * select * from t_ecommerce_goods where goods_category = ? and brand_category = ?
     * and goods_name = ? limit 1;
     */
    Optional<EcommerceGoods> findFirst1ByGoodsCategoryAndBrandCategoryAndGoodsName(
            GoodsCategory goodsCategory, BrandCategory brandCategory,
            String goodsName
    );
}

