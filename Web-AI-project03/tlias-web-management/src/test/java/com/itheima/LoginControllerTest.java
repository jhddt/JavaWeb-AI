package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.controller.LoginController;
import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class LoginControllerTest extends BaseSpringBootTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private EmpService empService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testLogin() throws Exception {
        // Given
        Emp emp = new Emp();
        emp.setUsername("admin");
        emp.setPassword("123456");
        
        // 创建JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "张三");
        claims.put("username", "admin");
        String jwt = JwtUtils.generateJwt(claims);
        
        LoginInfo loginInfo = new LoginInfo(1, "admin", "张三", jwt);
        
        when(empService.login(any(Emp.class))).thenReturn(loginInfo);
        
        // When & Then
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}