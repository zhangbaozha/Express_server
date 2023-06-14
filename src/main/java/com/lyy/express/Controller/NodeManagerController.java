package com.lyy.express.Controller;

import com.alibaba.fastjson.JSONObject;
import com.lyy.express.Entity.NodeManager;
import com.lyy.express.Entity.Package;
import com.lyy.express.Entity.Postman;
import com.lyy.express.Mapper.NodeManagerMapper;
import com.lyy.express.Mapper.PackageMapper;
import com.lyy.express.Mapper.PostmanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nodemanager")
@Slf4j

public class NodeManagerController {
    @Resource
    NodeManagerMapper nodeManagerMapper;
    @Resource
    PackageMapper packageMapper;

    @Resource
    PostmanMapper postmanMapper;

    @PostMapping("/nodemanagerlogin")
    public NodeManager userLogin(@RequestBody JSONObject jsonParam) {
        Map<String, Object> map = new HashMap<>();
        //开始转换
        Iterator it = jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }


        String username = (String) map.get("username");
        System.out.println(username);
        String password = (String) map.get("password");
        System.out.println(password);

        NodeManager nodeManager = nodeManagerMapper.findBynameAndPwd(username, password);
        if (nodeManager == null) {
            log.info("登录失败");
            return null;

        }
        log.info("登录成功");
        return nodeManager;
    }

    @GetMapping("/nodemanagercheckpostman")
    public List<Postman> nodemanagercheckpostman(String node){
        List<Postman> postmen = postmanMapper.findByNode(node);
        return postmen;
    }


    @PostMapping("/addpostman")
    public String addpostman(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }


        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String node = (String) map.get("node");

        postmanMapper.addPostman(username,password,node);


        log.info("增加成功");
        return "success";
    }

    @PostMapping("/firepostman")
    public String firepostman(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }


        String username = (String) map.get("username");
        String node = (String) map.get("node");


        Postman postman = postmanMapper.findByNodeAndName(node,username);
        if(postman == null){
            log.info("修改失败，没有这个快递");
            return "No This Package";

        }

       postmanMapper.deleteByNodeAndName(node,username);

        log.info("开除成功");
        return "success";
    }

}

