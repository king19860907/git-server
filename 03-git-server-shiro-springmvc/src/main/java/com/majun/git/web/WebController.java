package com.majun.git.web;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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

        List<Map<String,String>> list = packageUser(23);
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/readme", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String test3(){
        String str=readFileByLines("readme.md");
        return JSONObject.toJSONString(str);
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        System.out.println(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file.exists());
        return file;
    }

    private String readFileByLines(String fileName) {
        File file = getFile(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            String str = "";
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
                str += tempString+"\n";
            }
            reader.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

    public List<Map<String,String>> packageUser(int number){
        List<Map<String,String>> list = new ArrayList<>();
        for(int i=1;i<=number;i++){
            Map<String,String> map = new HashMap<>();
            map.put("username","majun"+i);
            map.put("code","100"+i);
            list.add(map);
        }
        return list;
    }

}
