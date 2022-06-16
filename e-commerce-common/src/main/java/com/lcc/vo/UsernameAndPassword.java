package com.lcc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichaochao
 * @data 2022/4/12 3:04 PM
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameAndPassword {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
