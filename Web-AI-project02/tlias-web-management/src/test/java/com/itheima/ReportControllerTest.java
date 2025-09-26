package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.controller.ReportController;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
public class ReportControllerTest extends BaseSpringBootTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ReportService reportService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testCountJob() throws Exception {
        // Given
        List<Object> jobList = Arrays.asList("项目经理", "开发工程师", "测试工程师");
        List<Object> dataList = Arrays.asList(5, 15, 8);
        JobOption jobOption = new JobOption(jobList, dataList);
        
        when(reportService.getEmpJobData()).thenReturn(jobOption);
        
        // When & Then
        mockMvc.perform(get("/report/empJobData"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
    
    @Test
    void testStudentCountData() throws Exception {
        // Given
        List<Object> clazzList = Arrays.asList("一班", "二班", "三班");
        List<Object> dataList = Arrays.asList(30, 28, 32);
        ClazzOption clazzOption = new ClazzOption(clazzList, dataList);
        
        when(reportService.studentCountData()).thenReturn(clazzOption);
        
        // When & Then
        mockMvc.perform(get("/report/studentCountData"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}