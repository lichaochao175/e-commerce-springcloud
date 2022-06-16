package com.lcc.constant;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import java.security.*;

/**
 * @author lichaochao
 * @data 2022/4/12 1:50 PM
 * 授权需要使用的一些常量类
 **/
@Slf4j
public final class AuthorityConstant {

    public static final Integer DEFAULT_EXPIRE_DAY = 1;
    /**
     * RSA 私钥 ，除了授权中心 不暴露任何信息
     */
    public static String PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC9R99UmoTmKRc/yGIQgrjCcw" +
            "+y1dzYVnD5q3+pfGfzE7ZBGFzLQjy9prZ2MIxdqoldj2ujaRzges9TTGZAxUcXsolW2oBfgcnIvFmC+fXKQyKyn9pwqqD3Wl7avDOT1" +
            "/jEGmnHeE8rsFI5+0PFLpMKXzQ14PkuIO6TyykIN2qQhhw0XUjdLx+yBM46FS99vl9GU3CULUKxWFWF8B8yhxCzbaKL0n" +
            "3cIs6sjueNmlGUyBC2I+AekrGaT05qoUwMhTROzY+Rqu5ZxCj6nATrsYdxfPpt+3du0Xl6yjfkbPf5LCtV6LxQC0dbrIZLEMCEL" +
            "dJdH5obmtg5A8pBixM4KQy9AgMBAAECggEBALmt4dW65xhpwtbdGeYcdma6PPIwfb7zvUwPAuMemVKJSca9wXnbdn6NJnz2zxur" +
            "ialwYiUhniRQFUYfskdjv4PtrnC3VCtcxLfYnO6xin5qMYWM7tqLZ/B+hIKLEQW9XdnyF0aILRIsm90pMagjMjeAgVVBZu/rel8" +
            "dcxkOU8mFNOObIGslvW6Za4XUocUVw/LfvYGVFj1K03gmtIvoZrXwDH21zepTt8RjPkgUGHu16GLX00CKCAMTL66Za+iuWE+u7sA" +
            "6m4aJL0gLIHbG61ONRZi0KzSs678WE02AdHgaUV62VA46SbIHwoqkzo1TNr9ag4wqOOLHw2o30MJYxTkCgYEA7PQa9dnv2C3TVy+7" +
            "XOlBzOwtkU8e8CbixJyQ3GH75kmOfC4S5Pvnw+umzFlDmnjvBfzRGQFz2BTewamt/3BE6ngyXx4/vIp+cFMF5JcAdh6dnkU0GqENc" +
            "eUGOovCwpWekFw1R9Sum+sKh2xf0NjVois8dI4mt7MKubCj6T596GsCgYEAzH7IoKrvSZoAw+Mgw0IyhOP9FFgONCQeY9kPst9WOB" +
            "gefDPE+3XuBcqmhVF+POlIi1SF9Bm2/XdIctDr7jzRi9fV7eprBz72oeWXgo7g4/u4lEHXldqYWIvPuAvKSY0hLcUl+RhB94zBaz3AQ" +
            "xEfza/dCOdV1ACG6MEmxJHyyXcCgYADiiX49Dd8ADs++yyOV8Gu4PxRRmBM8BfFHh2f3SrZJtIlEr8fgo/UwmlsdgznetT/PuaUvAvR" +
            "94XYWMOLpE6zPfZqq1YCaAM5hFP7NTJBkXWDTr9Xgn5j7CrqpvnS1E9ZEreNCiVNmPZATaMkyIhwcrrldtYnOlp3qabZLiDNXwKBgQCh" +
            "ra+jz+HGeFsYhO+3URQuGy9RsVjxgokAenI+AvPg/7jCjA+2nIOm0pUbnNXJBMwVVKtf1iDmfzOpi2KthipKfUgnhYo/PVmzoJlMYPiP" +
            "Z8qEdRYk5aJDh66gHbo+srVDwbaHHdWyCfg2eKkUnWperid8zaH/Lrd7OJMVISsqewKBgQC9QfcHL2VI+amTGNWwAO0T1UWA+/Swn7Yr" +
            "VqAU747A5ldaZh7x2wSVjqcM+A960EjODcwz/HEypMJqlKEsbDpaCEhprMacWuCjkewOytf0whns6vQdQB7MdZK5tX8+d3ACLtihJgx0V" +
            "0DfiQoq7AWP8Ihq49RyunFFIyw5mNdETg==";

    /**
     * 生成公钥
     * 私钥
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //生成公钥
        PublicKey publicKey = keyPair.getPublic();
        log.info("公钥：[{}]", Base64.encode(publicKey.getEncoded()));
        log.info("私钥：[{}]", Base64.encode(privateKey.getEncoded()));
    }
}
