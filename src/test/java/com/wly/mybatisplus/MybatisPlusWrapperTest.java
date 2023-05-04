package com.wly.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wly.mybatisplus.mapper.UserMapper;
import com.wly.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/04/23/17:15
 * @Description:
 */
@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
        //SELECT id,name,age,email FROM user WHERE (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "三")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按id升序排序
        //SELECT id,name,age,email FROM user ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    @Test
    public void test03() {
        //删除邮箱地址为null的用户信息
        //DELETE FROM user WHERE (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result:" + result);
    }

    @Test
    public void test4() {
        //将年龄大于20并且用户名中包含三或者邮箱为null的用户信息修改
        //UPDATE user SET name=?, email=? WHERE (age > ? AND name LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("name", "三")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("wangleyan0735@163.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);

    }

    @Test
    public void test05() {
        //将用户名中包含有二并且（年龄大于20或邮箱为null）的用户信息进行修改
        //lambda中的条件优先执行
        //UPDATE user SET name=?, email=? WHERE (name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "一")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("小明");
        user.setEmail("wangleyan0735@163.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);
    }

    @Test
    public void test06() {
        //查询用户的用户名、年龄、邮箱信息
        //SELECT name,age,email FROM user
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        //查询id小于等于100的用户信息（子查询）
        //SELECT id,name,age,email FROM user WHERE (id IN (select id from user where id <= 100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08() {
        //将用户名中包含有二并且（年龄大于20或邮箱为null）的用户信息进行修改
        //UPDATE user SET name=?,email=? WHERE (name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "五")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "小黑").set("email", "wlywly0735@126.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result:" + result);

    }

    @Test
    public void test09() {
        //SELECT id,name,age,email FROM user WHERE (name LIKE ? AND age <= ?)
        String username = "四";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "name", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
