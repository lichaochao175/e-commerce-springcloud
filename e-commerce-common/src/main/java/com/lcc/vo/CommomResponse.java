package com.lcc.vo;

import lombok.*;


import java.io.Serializable;

/**
 * @author lichaochao
 * @data 2022/4/11 10:08 AM
 * <p>
 * 通用响应对象
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommomResponse<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer id;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 泛型响应数据
     */

    @Getter
    @Setter
    private T data;

    public CommomResponse(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public static CommomResponse of(Integer id, String message) {
        return new CommomResponse(id, message);
    }

    public static CommomResponse fail(Integer id, String message) {
        return new CommomResponse(id, message);
    }

    public static CommomResponse fail(Integer id, String message, String data) {
        return new CommomResponse(id, message, data);
    }
    //todo 如何处理 T  data
}
