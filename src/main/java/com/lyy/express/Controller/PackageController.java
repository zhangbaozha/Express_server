package com.lyy.express.Controller;

import com.alibaba.fastjson.JSONObject;
import com.lyy.express.Entity.Package;
import com.lyy.express.Mapper.PackageMapper;
import com.lyy.express.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/package")
@Slf4j
public class PackageController {
    @Resource
    PackageMapper packageMapper;

    @PostMapping("/addpackage")
    public String addPackage(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }
        String code = generateRandomNumber(16);
        String username = (String) map.get("username");
        String phone = (String) map.get("phone");
        String address = (String) map.get("address");
        String recipientname = (String) map.get("recipientname");
        String recipientphone = (String) map.get("recipientphone");
        String recipientaddress = (String) map.get("recipientaddress");
        String node = (String) map.get("node");
        System.out.println("node:"+node);
        String status = "待发出";

        Package pkg = new Package(code,username,phone,address,recipientname,recipientphone,recipientaddress,status,node);
        packageMapper.addPackage(pkg);
        System.out.println(pkg);
        return "success";
    }
    @GetMapping("/mysend")
    public List<Package> CheckSend(String phone){
        List<Package> packages = packageMapper.findByPhone(phone);
        return packages;
    }


    @GetMapping("/myreceive")
    public List<Package> MyReceive(String phone){
        List<Package> packages = packageMapper.findMyReceive(phone);
        return packages;
    }

    @GetMapping("/findbycode")
    public Package findByCode(String code){
        Package pkg = packageMapper.findByCode(code);
        return pkg;
    }

    private static String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
}
