package com.imooc.ecommerce.domian.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.imooc.ecommerce.domian.base.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <h1>用户账户余额表实体类定义</h1>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_ecommerce_balance")
public class EcommerceBalance extends BaseData {

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
     * 账户余额
     */
    private Long balance;


}
