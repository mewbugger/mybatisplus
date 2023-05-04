package com.wly.mybatisplus.pojo;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/04/23/2:44
 * @Description:
 */

//Data注解包含了GETTER,SETTER,无参构造，equals，hashcode
@Data
//设置实体类所对应的表名
@TableName("user")
public class User {

    //将这个属性所对应的字段来作为主键
    //@TableId注解的value属性用于指定主键的字段
    //@TableId注解的type属性设置主键生成策略
    @TableId(value = "id")
    private Long id;

    //MP会自动把下划线转换成对应的驼峰
    //e.g. user_name => userName
    //@TableField 指定属性所对应的字段
    private String name;

    private Integer age;

    private String email;

}
