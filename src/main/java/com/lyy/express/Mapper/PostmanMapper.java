package com.lyy.express.Mapper;

import com.lyy.express.Entity.Postman;
import com.lyy.express.Entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostmanMapper {
    @Select("select * from postman where username=#{username} and pwd=#{pwd}")
    Postman findBynameAndPwd(String username, String pwd);

    @Select("select * from postman where node=#{node}")
    List<Postman> findByNode(String node);

    @Update("insert into postman (username, pwd,node) values (#{username}, #{pwd}, #{node})")
    void addPostman(String username,String pwd,String node);

    @Select("select * from postman where node=#{node} and username=#{username}")
    Postman findByNodeAndName(String node,String username);

    @Delete("delete  from postman where node=#{node} and username=#{username}")
    void deleteByNodeAndName(String node,String username);
}
