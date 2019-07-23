package com.jin.controller;

import com.jin.utils.FastDFSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 高山仰之可及，深渊度之可测
 */
@Controller
public class MyController {

    @RequestMapping("/fileupload.json")
    @ResponseBody
    public Map<String,Object> fileupload(@RequestParam("file") MultipartFile file){

        Map<String,Object> map = new HashMap<>();


        try {
            //1.得到图片名称
            String name = file.getOriginalFilename();
            //2.得到后缀
            String suffix = name.substring(name.lastIndexOf(".")+1);

            //3.得到上传的文件的byte数组
            byte[] b = file.getBytes();

            FastDFSUtils fastDFSUtils = new FastDFSUtils();
            String[] s = fastDFSUtils.upload(b, suffix);

            StringBuilder stringBuilder = new StringBuilder("http://192.168.168.10:82/");
            if (s!=null){
                for (int i = 0; i < s.length; i++) {
                    stringBuilder.append(s[i]);
                    if (i==0){
                        stringBuilder.append("/");
                    }
                }
            }
            String url = stringBuilder.toString();
            map.put("status",200);
            map.put("msg","success");
            map.put("url",url);
            return map;




        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("status",800);
        map.put("msg","fail");
        return map;
    }


}
