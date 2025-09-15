package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest //SpringBoot的单元测试注解 --- 当前类中的测试方法运行时，会启动这个SpringBoot项目 --- IOC容器就会创建
class SpringMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试查询功能
     */
    @Test
    public void testFindAll() {
        List<User> userList = userMapper.findAll();
        userList.forEach(user -> System.out.println(user));
    }
    /**
    * 测试删除功能
     * 没有返回值
    */
    @Test
    public void testDelecteById() {
        Integer i = userMapper.deleteById(5);
        System.out.println("执行结束后影响的记录数："+i);
    }
    /**
     * 测试添加功能
     */
    @Test
    public void testInsert() {
        User user = new User(null,"admin","123456","管理员",18);
        Integer i = userMapper.insert(user);
        System.out.println("添加成功！"+i);
    }
    /**
     * 测试根据id修改信息功能
     */
    @Test
    public void testUpdate() {
        User user = new User(5,"gaoyuanyuan","123456","高圆圆",18);
        Integer i = userMapper.update(user);
        System.out.println("修改成功！"+i);
    }
    /**
     * 测试根据id查询功能
     */
    @Test
    public void testFindByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("gaoyuanyuan","123456");
        System.out.println(user);
    }
}
