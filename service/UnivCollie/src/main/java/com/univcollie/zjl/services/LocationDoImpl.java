package com.univcollie.zjl.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LocationDoImpl implements LocationDo {

    @Autowired
    HttpGetPost httpGetPost;

    @Override
    public boolean creatEnclosure(String name,String center,String radius) {
        String url="https://restapi.amap.com/v4/geofence/meta?key=1d835c78f580eb9e072b4ebb257b7980";
        Map<String,String> map=new HashMap<String,String>();
        map.put("name",name);
        map.put("center",center);
        map.put("radius",radius);
        map.put("repeat","Mon,Tues,Wed,Thur,Fri,Sat,Sun");
        JSONObject jsonObject=httpGetPost.DoPost(url,map);
        try{
        if(jsonObject.getJSONObject("data").getString("message")!=null||jsonObject.getJSONObject("data").getString("message").equals("成功")){
            return true;
        } }catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public JSONObject selectEnclosure() {
        String url="https://restapi.amap.com/v4/geofence/meta?key=1d835c78f580eb9e072b4ebb257b7980";
        JSONObject jsonObject=httpGetPost.DoGet(url,null);
        return jsonObject;
    }

    @Override
    public boolean updataEnclosure(String longitude,String latitude,String radius) {
        String url="https://restapi.amap.com/v4/geofence/meta?key=1d835c78f580eb9e072b4ebb257b7980&gid=c591fa72-ba61-421b-8235-607420e9a1b1&method=patch";
        String center=longitude+","+latitude;
//        String center="102.82147,24.88554";
        Map<String,String> map=new HashMap<String,String>();
        map.put("name","univcolli");
        map.put("center",center);
        map.put("radius",radius);
        map.put("repeat","Mon,Tues,Wed,Thur,Fri,Sat,Sun");
        JSONObject jsonObject=httpGetPost.DoPost(url,map);
        try{
            if(jsonObject.getJSONObject("data").getString("message")!=null||jsonObject.getJSONObject("data").getString("message").equals("成功")){
                return true;
            } }catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean removeEnclosure(String gid) {
        String url="https://restapi.amap.com/v4/geofence/meta?key=1d835c78f580eb9e072b4ebb257b7980&gid="+gid;
        JSONObject jsonObject=httpGetPost.DoDelete(url,null);
        try{
        if(jsonObject.getJSONObject("data").getString("message")!=null||jsonObject.getJSONObject("data").getString("message").equals("成功")){
            return true;
        }
        }catch (Exception e) {
            return false;
        }
        return false;
    }

    //用于判断用户坐标是否在电子围栏内
    @Override
    public boolean cheakEnclosure(String longtitude,String latitude) {
        String time=System.currentTimeMillis()+"";
        String url="https://restapi.amap.com/v4/geofence/status?key=1d835c78f580eb9e072b4ebb257b7980&diu=869033024848468&locations="+longtitude+","+latitude+","+time.substring(0,10);
        JSONObject jsonObject=httpGetPost.DoGet(url,null);
        System.out.println("URL= "+url);
        System.out.println("开始通过高德API查询 "+longtitude+"  "+latitude );
        JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("fencing_event_list");
        System.out.println(jsonArray);
        if(jsonArray.size()==0){
            return false;
        }
        if(jsonArray.getJSONObject(0).getString("client_status")==null||jsonArray.getJSONObject(0).getString("client_status").equals("out")){
            return false;
        }
        return true;
    }

}
