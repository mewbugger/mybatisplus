package com.wly.mybatisplus;

import com.wly.mybatisplus.pojo.User;
import com.wly.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/04/23/6:06
 * @Description:
 */
@SpringBootTest
public class MybatisPlusServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testInsertMore() {
        //INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        //上述SQL循环执行
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("wly" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }

}
