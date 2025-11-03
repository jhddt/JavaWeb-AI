package com.itheima;

import com.itheima.controller.ClazzController;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 班级控制器测试类
 * 用于测试ClazzController中各个接口的功能
 */
@SpringBootTest
public class ClazzControllerTest {

    /**
     * MockMvc对象，用于模拟HTTP请求
     */
    private MockMvc mockMvc;

    /**
     * ClazzService的模拟对象，用于模拟服务层行为
     */
    @Mock
    private ClazzService clazzService;

    /**
     * 要测试的ClazzController对象，结合模拟对象进行测试
     */
    @InjectMocks
    private ClazzController clazzController;

    /**
     * 测试前的初始化工作
     * 初始化Mockito模拟对象和MockMvc
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clazzController).build();
    }

    /**
     * 测试查询所有班级信息接口
     * 验证GET /clazzs/list接口能否正确返回所有班级信息
     * 
     * @throws Exception 当测试过程中发生异常时抛出
     */
    @Test
    public void testSelectAll() throws Exception {
        // 准备测试数据
        Clazz clazz1 = new Clazz();
        clazz1.setId(1);
        clazz1.setName("Java基础班");
        clazz1.setRoom("A101");
        clazz1.setBeginDate(LocalDate.now());
        clazz1.setEndDate(LocalDate.now().plusMonths(3));
        clazz1.setMasterId(1);
        clazz1.setSubject(1);
        clazz1.setCreateTime(LocalDateTime.now());
        clazz1.setUpdateTime(LocalDateTime.now());

        Clazz clazz2 = new Clazz();
        clazz2.setId(2);
        clazz2.setName("Java高级班");
        clazz2.setRoom("A102");
        clazz2.setBeginDate(LocalDate.now());
        clazz2.setEndDate(LocalDate.now().plusMonths(6));
        clazz2.setMasterId(2);
        clazz2.setSubject(1);
        clazz2.setCreateTime(LocalDateTime.now());
        clazz2.setUpdateTime(LocalDateTime.now());

        List<Clazz> clazzList = Arrays.asList(clazz1, clazz2);

        // 模拟服务层返回
        when(clazzService.selectAll()).thenReturn(clazzList);

        // 执行测试
        mockMvc.perform(get("/clazzs/list")
                        .header("token", "fake-token-for-test")) // 绕过TokenInterceptor验证
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));

        // 验证服务方法被调用
        verify(clazzService, times(1)).selectAll();
    }

    /**
     * 测试条件分页查询班级信息接口
     * 验证GET /clazzs接口能否根据查询条件正确返回分页结果
     * 
     * @throws Exception 当测试过程中发生异常时抛出
     */
    @Test
    public void testGetClazzDatas() throws Exception {
        // 准备测试数据
        ClazzQueryParam param = new ClazzQueryParam();
        param.setPage(1);
        param.setPageSize(10);
        param.setName("Java");

        Clazz clazz = new Clazz();
        clazz.setId(1);
        clazz.setName("Java基础班");
        clazz.setRoom("A101");
        clazz.setBeginDate(LocalDate.now());
        clazz.setEndDate(LocalDate.now().plusMonths(3));
        clazz.setMasterId(1);
        clazz.setSubject(1);
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        PageResult<Clazz> pageResult = new PageResult<>();
        pageResult.setTotal(1L);
        pageResult.setRows(Arrays.asList(clazz));

        // 模拟服务层返回
        when(clazzService.getClazzDatas(any(ClazzQueryParam.class))).thenReturn(pageResult);

        // 执行测试
        mockMvc.perform(get("/clazzs")
                        .param("page", "1")
                        .param("pageSize", "10")
                        .param("name", "Java")
                        .header("token", "fake-token-for-test")) // 绕过TokenInterceptor验证
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total").value(1))
                .andExpect(jsonPath("$.data.rows").isArray())
                .andExpect(jsonPath("$.data.rows.length()").value(1));

        // 验证服务方法被调用
        verify(clazzService, times(1)).getClazzDatas(any(ClazzQueryParam.class));
    }

    /**
     * 测试根据ID查询班级信息接口
     * 验证GET /clazzs/{id}接口能否根据ID正确返回班级信息
     * 
     * @throws Exception 当测试过程中发生异常时抛出
     */
    @Test
    public void testGetByID() throws Exception {
        // 准备测试数据
        Clazz clazz = new Clazz();
        clazz.setId(1);
        clazz.setName("Java基础班");
        clazz.setRoom("A101");
        clazz.setBeginDate(LocalDate.now());
        clazz.setEndDate(LocalDate.now().plusMonths(3));
        clazz.setMasterId(1);
        clazz.setSubject(1);
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 模拟服务层返回
        when(clazzService.getId(1)).thenReturn(clazz);

        // 执行测试
        mockMvc.perform(get("/clazzs/{id}", 1)
                        .header("token", "fake-token-for-test")) // 绕过TokenInterceptor验证
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Java基础班"));

        // 验证服务方法被调用
        verify(clazzService, times(1)).getId(1);
    }

    /**
     * 测试新增班级信息接口
     * 验证POST /clazzs接口能否正确处理新增班级请求
     * 
     * @throws Exception 当测试过程中发生异常时抛出
     */
    @Test
    public void testAddClazzData() throws Exception {
        // 准备测试数据
        Clazz clazz = new Clazz();
        clazz.setName("Python基础班");
        clazz.setRoom("B201");
        clazz.setBeginDate(LocalDate.now());
        clazz.setEndDate(LocalDate.now().plusMonths(3));
        clazz.setMasterId(3);
        clazz.setSubject(2);

        String clazzJson = "{\n" +
                "  \"name\": \"Python基础班\",\n" +
                "  \"room\": \"B201\",\n" +
                "  \"beginDate\": \"" + LocalDate.now() + "\",\n" +
                "  \"endDate\": \"" + LocalDate.now().plusMonths(3) + "\",\n" +
                "  \"masterId\": 3,\n" +
                "  \"subject\": 2\n" +
                "}";

        // 执行测试
        mockMvc.perform(post("/clazzs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clazzJson)
                        .header("token", "fake-token-for-test")) // 绕过TokenInterceptor验证
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1));

        // 验证服务方法被调用
        verify(clazzService, times(1)).addClazzData(any(Clazz.class));
    }

    /**
     * 测试更新班级信息接口
     * 验证PUT /clazzs接口能否正确处理更新班级请求
     * 
     * @throws Exception 当测试过程中发生异常时抛出
     */
    @Test
    public void testUpdateClazzData() throws Exception {
        // 准备测试数据
        Clazz clazz = new Clazz();
        clazz.setId(1);
        clazz.setName("Java高级班-更新");
        clazz.setRoom("A103");
        clazz.setBeginDate(LocalDate.now());
        clazz.setEndDate(LocalDate.now().plusMonths(6));
        clazz.setMasterId(2);
        clazz.setSubject(1);

        String clazzJson = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Java高级班-更新\",\n" +
                "  \"room\": \"A103\",\n" +
                "  \"beginDate\": \"" + LocalDate.now() + "\",\n" +
                "  \"endDate\": \"" + LocalDate.now().plusMonths(6) + "\",\n" +
                "  \"masterId\": 2,\n" +
                "  \"subject\": 1\n" +
                "}";

        // 执行测试
        mockMvc.perform(put("/clazzs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clazzJson)
                        .header("token", "fake-token-for-test")) // 绕过TokenInterceptor验证
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1));

        // 验证服务方法被调用
        verify(clazzService, times(1)).updateClazzData(any(Clazz.class));
    }

    /**
     * 测试删除班级信息接口
     * 验证DELETE /clazzs/{id}接口能否正确处理删除班级请求
     * 
     * @throws Exception 当测试过程中发生异常时抛出
     */
    @Test
    public void testDeleteClazzData() throws Exception {
        // 执行测试
        mockMvc.perform(delete("/clazzs/{id}", 1)
                        .header("token", "fake-token-for-test")) // 绕过TokenInterceptor验证
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1));

        // 验证服务方法被调用
        verify(clazzService, times(1)).deleteClazzData(1);
    }
}