package com.itheima;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import com.itheima.service.impl.DeptSerciceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DeptServiceTest extends BaseSpringBootTest {
    
    @Mock
    private DeptMapper deptMapper;
    
    @InjectMocks
    private DeptService deptService = new DeptSerciceImpl();
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testList() {
        // Given
        List<Dept> deptList = Arrays.asList(
            new Dept(1, "开发部", LocalDateTime.now(), LocalDateTime.now()),
            new Dept(2, "测试部", LocalDateTime.now(), LocalDateTime.now())
        );
        
        when(deptMapper.findAll()).thenReturn(deptList);
        
        // When
        List<Dept> result = deptService.findAll();
        
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(deptMapper, times(1)).findAll();
    }
    
    @Test
    void testDeleteById() {
        // Given
        doNothing().when(deptMapper).deleteByID(anyInt());
        
        // When
        deptService.deleteByID(1);
        
        // Then
        verify(deptMapper, times(1)).deleteByID(1);
    }
}