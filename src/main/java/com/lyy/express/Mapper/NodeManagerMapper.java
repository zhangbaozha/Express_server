package com.lyy.express.Mapper;

import com.lyy.express.Entity.NodeManager;
import com.lyy.express.Entity.Postman;
import org.apache.ibatis.annotations.Select;

public interface NodeManagerMapper {
    @Select("select * from nodemanager where username=#{username} and pwd=#{pwd}")
    NodeManager findBynameAndPwd(String username, String pwd);
}
