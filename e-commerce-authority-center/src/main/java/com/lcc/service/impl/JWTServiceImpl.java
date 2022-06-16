package com.lcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcc.AuthorityCenterApplication;
import com.lcc.constant.AuthorityConstant;
import com.lcc.constant.CommonConstant;
import com.lcc.domain.po.UserPO;
import com.lcc.mapper.UserMapper;
import com.lcc.utils.TokenParseUtil;
import com.lcc.vo.CommomResponse;
import com.lcc.vo.LoginUserInfo;
import com.lcc.vo.UsernameAndPassword;
import com.lcc.service.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * @author lichaochao
 * @data 2022/4/12 3:09 PM
 **/

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class JWTServiceImpl implements JWTService {

    private final UserMapper userMapper;

    public JWTServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 生成JWT Token ,使用默认的超时时间
     *
     * @param qto
     * @return
     */
    @Override
    public String generateToken(UsernameAndPassword qto) throws Exception {
        return generateToken(qto, 0);
    }

    /**
     * 生成指定超时时间
     * 单位是天
     *
     * @param qto
     * @param expire
     * @return
     */
    @Override
    public String generateToken(UsernameAndPassword qto, Integer expire) throws Exception {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", qto.getUsername())
                //todo 密码需要加密
                .eq("password", qto.getPassword());
        //首先需要验证用户是否能通过授权校验，判断是否与数据库一致
        UserPO userPO = userMapper.selectOne(wrapper);
        //查询不到数据则返回
        if (Objects.isNull(userPO)) {
            log.info("can not find user: [{}]", qto.getUsername());
            return null;
        }
        if (expire <= 0) {
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        // 计算超时时间
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

        //生成token
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        BeanUtils.copyProperties(userPO, loginUserInfo);
        return Jwts.builder()
                //jwt  payload -->kv 形式存在
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                .setId(userPO.getId().toString())
                //jwt 过期时间
                .setExpiration(expireDate)
                //jwt 签名加密
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256).compact();
    }

    /**
     * 注册用户并生成token
     *
     * @param qto
     * @return
     */
    @Override
    public String registerUserAndGenerateToken(UsernameAndPassword qto) throws Exception {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", qto.getUsername())
                //todo 密码需要加密
                .eq("password", qto.getPassword());
        //验证数据库是否存在
        UserPO userPO = userMapper.selectOne(wrapper);
        if (!Objects.isNull(userPO)) {
            //存在
            log.info("register user success: [{}]", qto.getUsername());
        }
        // 生成 token 并返回
        return generateToken(qto);
    }

    /**
     * <h2>根据本地存储的私钥获取到 PrivateKey 对象</h2>
     */
    private PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }


    /**
     * JWT 解析token
     */
    public LoginUserInfo JWTTest(String token) {
        LoginUserInfo loginUserInfo = null;
        try {
            loginUserInfo = TokenParseUtil.parseUserInfoFromToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("解析token [{}]", JSON.toJSONString(loginUserInfo));
        return loginUserInfo;
    }


}
