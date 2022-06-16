package com.lcc.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserPO implements Serializable {

    private Long id;
    private String mobile;
    private String username;
    private String password;
    private String email;
    private BigDecimal incomeWithdrawn;
    private BigDecimal shareRatio;
    private String bankName;
    private String bankBranch;
    private String bankNum;
    private String company;
    private String payeeName;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
