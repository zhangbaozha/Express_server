package com.lyy.express.Controller;

import com.alibaba.fastjson.JSONObject;
import com.lyy.express.Entity.User;
import com.lyy.express.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    @Resource
    UserMapper userMapper;

    @PostMapping("/userlogin")
    public User userLogin(@RequestBody JSONObject jsonParam) {
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

       User user = userMapper.findByUsernameAndPwd(username,password);
        if(user == null){
            log.info("登录失败");
            return user;

        }
        log.info("登录成功");
        return user;
    }



    @PostMapping("/userchangefile")
    public String userchangefile(@RequestBody JSONObject jsonParam) {
        Map<String,Object> map =new HashMap<>();
        //开始转换
        Iterator it =jsonParam.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }


        String username = (String) map.get("username");

        String password = (String) map.get("password");

        String phone = (String) map.get("phone");
        String birth = (String) map.get("birthDate");
        String address = (String) map.get("address");


        User user = new User(1,username,password,phone,birth,address);
        userMapper.updateUser(user);
        log.info(username+"修改信息成功");
        return "s";
    }
}
