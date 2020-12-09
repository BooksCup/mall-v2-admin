package com.bc.wd.utils.shiro;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pan Weilong
 * @date 2019/7/2 20:54
 * @description: 接口.
 */
@Configuration
@Getter
@Setter
public class JwtProperties {

    /**
     * 秘钥
     **/
    @Value("${security.secret}")
    private String secret;
    /**
     * 过期时间
     **/
    @Value("${token.tokenExpireTime}")
    private Integer tokenExpireTime;
}
