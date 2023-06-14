package com.lyy.express.Controller;

import com.alibaba.fastjson.JSONObject;
import com.lyy.express.Entity.Package;
import com.lyy.express.Entity.Postman;
import com.lyy.express.Entity.User;
import com.lyy.express.Mapper.PackageMapper;
import com.lyy.express.Mapper.PostmanMapper;
import com.lyy.express.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/postman")
@Slf4j

public class PostmanController {
    @Resource
    PostmanMapper postmanMapper;
    @Resource
    PackageMapper packageMapper;

    @PostMapping("/postmanlogin")
    public Postman userLogin(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }


        String username = (String) map.get("username");
        System.out.println(username);
        String password = (String) map.get("password");
        System.out.println(password);

        Postman postman = postmanMapper.findBynameAndPwd(username,password);
        if(postman == null){
            log.info("登录失败");
            return null;

        }
        log.info("登录成功");
        return postman;
    }


    @GetMapping("/checknodepackage")
    public List<Package> postmanCheck(String node){
        List<Package> packages = packageMapper.findByNode(node);
        System.out.println(packages);
        return packages;
    }

    @PostMapping("/postmanchangestatus")
    public String postmanChangeStatus(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }


        String code = (String) map.get("code");
        String status = (String) map.get("status");
        Package pkg = packageMapper.findByCode(code);
        if(pkg == null){
            log.info("修改失败，没有这个快递");
            return "No This Package";

        }

        packageMapper.setStatus(code,status);

        log.info("登录成功");
        return "success";
    }
}
