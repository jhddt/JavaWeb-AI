package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.controller.DeptController;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeptController.class)
public class DeptControllerTest extends BaseSpringBootTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DeptService deptService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testListDepts() throws Exception {
        // Given
        List<Dept> deptList = Arrays.asList(
            new Dept(1, "开发部", LocalDateTime.now(), LocalDateTime.now()),
            new Dept(2, "测试部", LocalDateTime.now(), LocalDateTime.now())
        );
        
        when(deptService.findAll()).thenReturn(deptList);
        
        // When & Then
        mockMvc.perform(get("/depts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}