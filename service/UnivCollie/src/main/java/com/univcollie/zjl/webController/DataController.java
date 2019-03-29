package com.univcollie.zjl.webController;


import com.alibaba.fastjson.JSONObject;
import com.univcollie.zjl.Univcollie;
import com.univcollie.zjl.entity.Sos;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {


    //获得正在发送求助信息的列表
    @RequestMapping("/sosinfor")
    public  HashMap<String, Object> sosInfor(HashMap<String, Object> map){
//        model.addAllAttributes();
        map=new HashMap<String, Object>();
        List<List> list1=new ArrayList<List>();
        for(int key: Univcollie.soslist.keySet()){
            List<Object> list=new ArrayList<Object>();
            list.add(Univcollie.soslist.get(key).getSostype());
            list.add(Univcollie.soslist.get(key).getBeigintime());
            list.add(Univcollie.soslist.get(key).getStuname());
            list.add(Univcollie.soslist.get(key).getStutel());
            list.add(Univcollie.soslist.get(key).getLocation());
            list.add(Univcollie.soslist.get(key).getIllog());
            list.add(Univcollie.soslist.get(key).getStuid());
            list1.add(list);
        }
        map.put("data",list1);
//        JSONObject jsonParam1=new JSONObject();
//        jsonParam1.put("求助类型", "Tiger Nixon");
//        jsonParam1.put("求助时间", "Tiger Nixon");
//        jsonParam1.put("求助人姓名", "Tiger Nixon");
//        jsonParam1.put("求助人电话", "Tiger Nixon");
//        jsonParam1.put("求助人位置", "Tiger Nixon");
//        jsonParam1.put("求助人病历", "Tiger Nixon");
//        jsonParam1.put("操作", "Tiger Nixon");
        System.out.println(map);
        return map;
    }
    @RequestMapping("/sosnumber")
    public int sosNumber(HashMap<Object, Object> map){
        System.out.print("查询当前求救数！");
        return Univcollie.soslist.size();
    }
}
