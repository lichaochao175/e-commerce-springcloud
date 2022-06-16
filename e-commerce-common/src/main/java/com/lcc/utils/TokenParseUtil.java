package com.lcc.utils;

import com.alibaba.fastjson.JSON;
import com.lcc.constant.CommonConstant;
import com.lcc.vo.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * @author lichaochao
 * @data 2022/4/12 5:45 PM
 * 鉴权解密
 **/
public class TokenParseUtil {

    /**
     * 从JWT Token中解析 LoginUserInfo 对象
     *
     * @param token
     * @return
     */
    public static LoginUserInfo parseUserInfoFromToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();
        //判断如果token过期返回null
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }
        //todo 传入token错误会出现异常，需要进行全局捕获
        //反序列化成对象
        LoginUserInfo loginUserInfo = JSON.parseObject(body.get(CommonConstant.JWT_USER_INFO_KEY).toString()
                , LoginUserInfo.class);
        return loginUserInfo;
    }

    /**
     * 通过公钥进行解析
     *
     * @param token
     * @param publicKey
     * @return
     */
    private static Jws<Claims> parseToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 根据本地存储的公钥获取到publicKey
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    private static PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(CommonConstant.PUBLIC_KEY)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

}
