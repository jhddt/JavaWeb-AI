package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.controller.EmpController;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 注意：MockBean在Spring Boot 3.4.0中已被标记为弃用，但在当前版本中仍可使用
@WebMvcTest(EmpController.class)
public class EmpControllerTest extends BaseSpringBootTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private EmpService empService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testListEmp() throws Exception {
        // Given
        List<Emp> empList = Arrays.asList(
            new Emp(1, "admin", "123456", "张三", 1, "13800000001", 1, 15000, "1.jpg", LocalDate.now(), 1, LocalDateTime.now(), LocalDateTime.now(), "开发部", null),
            new Emp(2, "user", "123456", "李四", 2, "13800000002", 2, 12000, "2.jpg", LocalDate.now(), 1, LocalDateTime.now(), LocalDateTime.now(), "开发部", null)
        );
        
        when(empService.selectAll()).thenReturn(empList);
        
        // When & Then
        mockMvc.perform(get("/emps"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}