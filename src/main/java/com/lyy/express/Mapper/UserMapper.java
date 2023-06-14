package com.lyy.express.Mapper;

import com.lyy.express.Entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> findAllUser();

    @Update("insert into user (username, pwd,phone,birth,address) values (#{username}, #{pwd}, #{phone}, #{birth}, #{address})")
    @Transactional
    void addUser(User user);

    @Update("update user set username=#{username}, age=#{age} where id=#{id}")
    void updateById(User user);


    @Select("select * from user where username=#{username} and pwd=#{pwd}")
    User findByUsernameAndPwd(String username,String pwd);


    @Update("update  user set phone=#{phone},birth=#{birth},address=#{address} where username=#{username} and pwd=#{pwd}")
    void updateUser(User user);
}
