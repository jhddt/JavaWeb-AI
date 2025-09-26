package com.itheima;

import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.ReportService;
import com.itheima.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReportServiceTest extends BaseSpringBootTest {
    
    @Mock
    private EmpMapper empMapper;
    
    @Mock
    private ClazzMapper clazzMapper;
    
    @Mock
    private StudentMapper studentMapper;
    
    @Mock
    private OperateLogMapper operateLogMapper;
    
    @InjectMocks
    private ReportService reportService = new ReportServiceImpl();
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetEmpJobData() {
        // Given
        List<Map<String, Object>> jobData = Arrays.asList(
            createMapEntry("项目经理", 5L),
            createMapEntry("开发工程师", 15L),
            createMapEntry("测试工程师", 8L)
        );
        
        when(empMapper.countJobEmpData()).thenReturn(jobData);
        
        // When
        JobOption result = reportService.getEmpJobData();
        
        // Then
        assertNotNull(result);
        assertEquals(3, result.getJobList().size());
        assertEquals(3, result.getDataList().size());
        verify(empMapper, times(1)).countJobEmpData();
    }
    
    @Test
    void testStudentCountData() {
        // Given
        List<Map<String, Object>> studentData = Arrays.asList(
            createMapEntry("一班", 30L),
            createMapEntry("二班", 28L),
            createMapEntry("三班", 32L)
        );
        
        when(clazzMapper.studentCountData()).thenReturn(studentData);
        
        // When
        ClazzOption result = reportService.studentCountData();
        
        // Then
        assertNotNull(result);
        assertEquals(3, result.getClazzList().size());
        assertEquals(3, result.getDataList().size());
        verify(clazzMapper, times(1)).studentCountData();
    }
    
    private Map<String, Object> createMapEntry(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put("pos", key);
        map.put("num", value);
        return map;
    }
}