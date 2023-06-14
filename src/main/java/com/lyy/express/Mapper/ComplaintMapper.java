package com.lyy.express.Mapper;

import com.lyy.express.Entity.Complaint;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ComplaintMapper {
    @Update("insert into complaint (content,date) values (#{content},#{date});")
    void addComplaint(String content,String date);

    @Select("select * from complaint")
    List<Complaint> findAll();
}
