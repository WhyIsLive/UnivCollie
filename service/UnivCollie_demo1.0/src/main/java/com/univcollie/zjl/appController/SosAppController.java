package com.univcollie.zjl.appController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.univcollie.zjl.Univcollie;
import com.univcollie.zjl.entity.Sos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sos")
public class SosAppController {


    @RequestMapping("/medical")
    public int addSos_m( int id, String location,String stuname,String stutel,String illog, HashMap<String, String> map){
        System.out.print(id+ "    "+location);
        if(Univcollie.soslist==null||!Univcollie.soslist.containsKey(id)) {
            Sos sos = new Sos();
            sos.setStuid(id);
            sos.setLocation(location);
            sos.setStuname(stuname);
            sos.setStutel(stutel);
            sos.setBeigintime(new Date());
            sos.setSostype("医疗求助");
            sos.setIllog(illog);
            Univcollie.soslist.put(sos.getStuid(),sos);
            return 1;
        }else{
            return -1;
        }
    }

    @RequestMapping("/police")
    public int addSos_p( int id, String location,String stuname,String stutel,String illog, HashMap<String, String> map){
        System.out.print(id+ "    "+location);
        if(Univcollie.soslist==null||!Univcollie.soslist.containsKey(id)) {
            Sos sos = new Sos();
            sos.setStuid(id);
            sos.setLocation(location);
            sos.setStuname(stuname);
            sos.setStutel(stutel);
            sos.setBeigintime(new Date());
            sos.setSostype("警务求助");
            sos.setIllog(illog);
            Univcollie.soslist.put(sos.getStuid(),sos);
            return 1;
        }else{
            return -1;
        }
    }

    @RequestMapping("/sosinfor")
    public  JSONArray sosInfor(HashMap<String, Object> map){
//        model.addAllAttributes();
        JSONArray list=new JSONArray();
        for(int key: Univcollie.soslist.keySet()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("sostype",Univcollie.soslist.get(key).getSostype());
            jsonObject.put("beigintime",Univcollie.soslist.get(key).getBeigintime().toString().substring(11,16));
            jsonObject.put("stuname",Univcollie.soslist.get(key).getStuname());
            jsonObject.put("stutel",Univcollie.soslist.get(key).getStutel());
            jsonObject.put("longitude",Double.valueOf(Univcollie.soslist.get(key).getLocation().split("#")[0]));
           // System.out.println(Double.valueOf(Univcollie.soslist.get(key).getLocation().split("#")[0]));
            jsonObject.put("latitude",Double.valueOf(Univcollie.soslist.get(key).getLocation().split("#")[1]));
           // System.out.println(Double.valueOf(Univcollie.soslist.get(key).getLocation().split("#")[1]));
            jsonObject.put("illog",Univcollie.soslist.get(key).getIllog());
            list.add(jsonObject);
        }
//        JSONObject jsonParam1=new JSONObject();
//        jsonParam1.put("求助类型", "Tiger Nixon");
//        jsonParam1.put("求助时间", "Tiger Nixon");
//        jsonParam1.put("求助人姓名", "Tiger Nixon");
//        jsonParam1.put("求助人电话", "Tiger Nixon");
//        jsonParam1.put("求助人位置", "Tiger Nixon");
//        jsonParam1.put("求助人病历", "Tiger Nixon");
//        jsonParam1.put("操作", "Tiger Nixon");
        System.out.println(list);
        return list;
    }
}
