package com.imooc.ecommerce.domian.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lichaochao
 * @data 2022/6/14 1:08 PM
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseData {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
