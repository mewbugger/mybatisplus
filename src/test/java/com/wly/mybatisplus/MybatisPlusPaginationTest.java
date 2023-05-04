package com.wly.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wly.mybatisplus.mapper.UserMapper;
import com.wly.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/04/23/18:29
 * @Description:
 */
@SpringBootTest
public class MybatisPlusPaginationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage(){
        //Preparing: SELECT id,name,age,email FROM user LIMIT ?,?
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPage(page, null);
        System.out.println(page);
    }
}
