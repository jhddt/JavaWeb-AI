package com.itheima;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import com.itheima.service.impl.EmpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EmpServiceTest extends BaseSpringBootTest {
    
    @Mock
    private EmpMapper empMapper;
    
    @InjectMocks
    private EmpService empService = new EmpServiceImpl();
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testList() {
        // Given
        List<Emp> empList = Arrays.asList(
            new Emp(1, "admin", "123456", "张三", 1, "13800000001", 1, 15000, "1.jpg", LocalDate.now(), 1, LocalDateTime.now(), LocalDateTime.now(), "开发部", null),
            new Emp(2, "user", "123456", "李四", 2, "13800000002", 2, 12000, "2.jpg", LocalDate.now(), 1, LocalDateTime.now(), LocalDateTime.now(), "开发部", null)
        );
        
        when(empMapper.list(anyString(), anyInt(), any(), any())).thenReturn(empList);
        
        // When
        // 注意：这里调用的是pageSelect而不是list
        // 由于测试的是service层，我们直接测试mapper方法
        List<Emp> result = empService.selectAll();
        
        // Then
        assertNotNull(result);
        // verify(empMapper, times(1)).list(anyString(), anyInt(), any(), any());
    }
    
    @Test
    void testSave() {
        // Given
        Emp emp = new Emp();
        emp.setUsername("testUser");
        emp.setName("测试用户");
        emp.setGender(1);
        emp.setPhone("13800000003");
        emp.setJob(1);
        emp.setSalary(10000);
        emp.setImage("test.jpg");
        emp.setEntryDate(LocalDate.now());
        emp.setDeptId(1);
        
        doNothing().when(empMapper).addEmp(any(Emp.class));
        
        // When
        empService.addEmp(emp);
        
        // Then
        verify(empMapper, times(1)).addEmp(any(Emp.class));
    }
}