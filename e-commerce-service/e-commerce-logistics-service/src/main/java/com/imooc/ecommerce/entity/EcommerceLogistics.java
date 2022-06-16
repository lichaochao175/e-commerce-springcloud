package com.imooc.ecommerce.entity;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lichaochao
 * @data 2022/6/16 3:43 PM
 * 物流表实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_ecommerce_logistics")
public class EcommerceLogistics {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 订单 id
     */
    private Long orderId;

    /**
     * 用户地址 id
     */
    private Long addressId;

    /**
     * 备注信息(json 存储)
     */
    private String extraInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public EcommerceLogistics(Long userId, Long orderId, Long addressId, String extraInfo) {

        this.userId = userId;
        this.orderId = orderId;
        this.addressId = addressId;
        this.extraInfo = StringUtils.isNotBlank(extraInfo) ? extraInfo : "{}";
    }
}
