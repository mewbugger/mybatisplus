package com.wly.mybatisplus;

import com.wly.mybatisplus.mapper.UserMapper;
import com.wly.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/04/23/2:52
 * @Description:
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        //SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("王乐岩");
        user.setAge(22);
        user.setEmail("wlywly0735@126.com");
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }

    @Test
    public void testDeleteById() {
        //加L是因为id经过mp自带的雪花算法，已经超过了Integer的长度
        //DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1649854994035847169L);
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteByMap() {
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:" + result);
    }

    @Test
    public void testDeleteBatchIds() {
        //加上L是因为数据库中的id的数据类型是big int即Long
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result:" + result);
    }

    @Test
    public void testUpdateById() {
        //UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("wlywly0735@126.com");
        int result = userMapper.updateById(user);
        System.out.println("result:" + result);

    }

    @Test
    public void testSelectById() {
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap(){
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 21);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testCustomSelect(){
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
        System.out.println("hh");
        System.out.println("hhh");
        System.out.println("hot-fix");
        System.out.println("master冲突分支测试");

    }
}
