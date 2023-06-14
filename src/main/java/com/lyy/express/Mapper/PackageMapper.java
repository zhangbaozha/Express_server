package com.lyy.express.Mapper;

import com.lyy.express.Entity.Package;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PackageMapper {
    @Update("insert into package (code, sendername,senderphone,senderaddress,recipientname,recipientphone,recipientaddress,status,node) values (#{code}, #{sendername}, #{senderphone}, #{senderaddress}, #{recipientname},#{recipientphone},#{recipientaddress},#{status},#{node})")
    @Transactional
    public void addPackage(Package pkg);


    @Select("select * from package where senderphone=#{senderphone}")
    @Transactional
    public List<Package> findByPhone(String senderphone);


    @Select("select * from package where recipientphone=#{recipientphone}")
    @Transactional
    public List<Package> findMyReceive(String recipientphone);


    @Select("select * from package where code=#{code}")
    @Transactional
    public Package findByCode(String code);

    @Select("select * from package where node=#{node}")
    @Transactional
    public List<Package> findByNode(String node);

    @Update("update package set status=#{status} where code=#{code}")
    public void setStatus(String code,String status);
}
