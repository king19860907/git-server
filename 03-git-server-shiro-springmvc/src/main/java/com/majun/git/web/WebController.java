package com.majun.git.web;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by majun on 24/01/2018.
 */
@Controller
public class WebController {

    @RequestMapping(value = "/api/test", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String test(){
        Map<String,String> map = new HashMap<>();
        map.put("aaa","aaa");
        map.put("bbb","bbb");
        return JSONObject.toJSONString(map);
    }

    @RequestMapping(value = "/test", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String test2(){
        Map<String,String> map = new HashMap<>();
        map.put("aaa","aaa");
        map.put("bbb","bbb");
        Subject subject = SecurityUtils.getSubject();
        map.put("username",subject.getPrincipal().toString());
        return JSONObject.toJSONString(map);
    }

}
