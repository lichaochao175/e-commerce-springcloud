package com.imooc.ecommerce.fillter;

import com.lcc.vo.LoginUserInfo;

/**
 * @author lichaochao
 * @data 2022/6/7 9:57 AM
 * 使用ThreadLocal 去单独存储每一个线程携带的 LoginUserInfo 信息：
 * 1。保证没有资源泄漏1
 * 2。保证线程在重用时，不会出现数据混乱
 **/
public class AccessContext {

    private static final ThreadLocal<LoginUserInfo> loginUserInfo = new ThreadLocal<>();

    // 获取线程中loginUserInfo信息
    public static LoginUserInfo getLoginUserInfo() {
        return loginUserInfo.get();
    }

    // 在请求前拦截信息，将登录信息存入
    public static void setLoginUserInfo(LoginUserInfo loginUserInfo_) {
        loginUserInfo.set(loginUserInfo_);
    }

    // 在使用完后清除信息
    public static void clearLoginUserInfo() {
        loginUserInfo.remove();
    }
}
