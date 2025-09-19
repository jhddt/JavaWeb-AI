package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTTest {

    /**
     * 测试生成jwt
     */
    @Test
    public void testJWT(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")//设置密钥
                .addClaims(dataMap)//设置数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//设置有效期
                .compact();
        System.out.println("测试生成的密钥为：" + jwt);
    }

    /**
     * 测试解析jwt
     * parser解析方法
     *
     */
    @Test
    public void testParseJWT(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1ODI1MzY3M30.UV-h2swgcKHS9ftbgw2tsD1mx699QW32zlMu6Tn3tOs";
        Claims claims = Jwts.parser()//解析方法
                .setSigningKey("aXRoZWltYQ==")//设置密钥
                .parseClaimsJws(token)//解析token
                .getBody();//将token中的自定义信息解析出来
        System.out.println( claims);
    }
}
