package com.univcollie.zjl.test;

import com.univcollie.zjl.services.LocationDo;
import com.univcollie.zjl.services.PunchDataDo;
import com.univcollie.zjl.services.SystemDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("/")
@RestController
public class TestController {

    @Autowired
    SystemDo systemDo;

    @Autowired
    LocationDo locationDo;

    @Autowired
    PunchDataDo punchDataDo;

    @RequestMapping("/testadd")
    public String testadd(){
        return locationDo.creatEnclosure("univcolli","102.82147,24.88554","500")+"";
    }

    @RequestMapping("/testselect")
    public String testselect(){
//        JSONArray jsonArray= locationDo.selectEnclosure().getJSONObject("data").getJSONArray("rs_list");
//        String result=jsonArray.getJSONObject(0).getString("diu");
//        result=result+jsonArray.getJSONObject(0).getString("radius");
        return locationDo.selectEnclosure().toJSONString();
//        System.out.println("获取当前围栏中心坐标...");
//        JSONArray jsonArray= locationDo.selectEnclosure().getJSONObject("data").getJSONArray("rs_list");
//        String center=jsonArray.getJSONObject(0).getString("center");
//        String radius=jsonArray.getJSONObject(0).getString("radius");
//        JSONObject json=new JSONObject();
//        json.put("longitude",center.split(",")[0]);
//        json.put("latitude",center.split(",")[1]);
//        json.put("radius",radius);
//        System.out.println("更新成功！");
//        return json;
    }



    @RequestMapping("/testUpdata")
    public String testUpdata(){
        return locationDo.updataEnclosure("102.82147","24.88554","700")+"";
    }


    @RequestMapping("/testremove")
    public String testremove(){
        return locationDo.removeEnclosure("451f2f4e-7385-4038-afed-0784cdb50629")+"";
    }

    @RequestMapping("/testcheak")
    public String testcheak(){
        return locationDo.cheakEnclosure("102.855352","24.839087")+"";
    }

    @RequestMapping("/reloadPunch")
    public void reloadPunch(){
        systemDo.reloadPunch();
    }

    @RequestMapping("/adminindex")
    public void adminindex(){
        punchDataDo.getGradePunchProportion("2019");
    }



    @RequestMapping("/testgetSysinfor")
    public String testgetSysinfor(){
        return systemDo.getAllinfo().get("begintime").toString();
    }
}
