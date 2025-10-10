package com.itheima;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilsTest extends BaseSpringBootTest {
    
    @Test
    void testGenerateAndParseJwt() {
        // Given
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "张三");
        claims.put("username", "zhangsan");
        
        // When
        String jwt = JwtUtils.generateJwt(claims);
        
        // Then
        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());
        
        // When
        Claims parsedClaims = JwtUtils.parseJWT(jwt);
        
        // Then
        assertNotNull(parsedClaims);
        assertEquals(1, parsedClaims.get("id"));
        assertEquals("张三", parsedClaims.get("name"));
        assertEquals("zhangsan", parsedClaims.get("username"));
    }
}