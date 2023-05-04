package com.wly.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wly.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/04/23/2:48
 * @Description:
 */

/**
 * BaseMapper<T>:包含了CRUD的方法，且都是泛型，所以UserMapper继承了BaseMapper后只要
 * 指定具体的类，即可直接调用已经定义好的类
 */
//将该接口标识为持久层
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String, Object> selectMapById(Long id);


}
