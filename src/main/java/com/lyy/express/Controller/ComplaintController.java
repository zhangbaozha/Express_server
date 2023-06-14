package com.lyy.express.Controller;


import com.alibaba.fastjson.JSONObject;
import com.lyy.express.Entity.Complaint;
import com.lyy.express.Entity.Package;
import com.lyy.express.Mapper.ComplaintMapper;
import com.lyy.express.Mapper.NodeManagerMapper;
import com.lyy.express.Mapper.PackageMapper;
import com.lyy.express.Mapper.PostmanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/complaint")
@Slf4j

public class ComplaintController {


    @Resource
    ComplaintMapper complaintMapper;

    @PostMapping("/addcomplaint")
    public String addComplaint(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }

        String content = (String) map.get("content");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String datetime = formatter.format(date);

        complaintMapper.addComplaint(content,datetime);

        return "success";
    }


    @GetMapping("/findall")
    public List<Complaint> findAll(String phone){
        return complaintMapper.findAll();
    }

}
