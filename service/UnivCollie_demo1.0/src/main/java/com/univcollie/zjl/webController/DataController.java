package com.univcollie.zjl.webController;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.univcollie.zjl.DAO.TeacherDAO;
import com.univcollie.zjl.Univcollie;
import com.univcollie.zjl.entity.Sos;
import com.univcollie.zjl.entity.Teacher;
import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.services.LocationDo;
import com.univcollie.zjl.services.SystemDo;
import com.univcollie.zjl.services.TeacherDo;
import com.univcollie.zjl.services.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    TeacherDo teacherDo;

    @Autowired
    LocationDo locationDo;

    @Autowired
    UserDo userDo;

    @Autowired
    SystemDo systemDo;

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
            list.add("<button type=\"button\" class=\"btn btn-danger\" onclick=\"stop_sos("+Univcollie.soslist.get(key).getStuid()+")\">终止求救信号</button>");
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

//    获得教师表
    @RequestMapping("/teachermanager")
    public HashMap<String,Object> teachermanager(HashMap<String,Object> map){
        map=new HashMap<String,Object>();
        List<List> list1=new ArrayList<List>();
        for(Teacher teacher: teacherDo.getAllTeacher()){
            List<Object> list2=new ArrayList<Object>();
            list2.add(teacher.getTeachername());
            list2.add(teacher.getTeacherpassword());
            list2.add(teacher.getTeachertel());
            list2.add(teacher.getTeachermajor());
            list2.add(teacher.getTeacherclass());
            list2.add("<button type=\"button\" class=\"btn btn-danger\" onclick=\"delectTeacher("+teacher.getID()+")\">删除用户</button>");
            list1.add(list2);
        }
        map.put("data",list1);
        System.out.println(map);
        return map;
    }

//    获得学生安全员表
    @RequestMapping("/sutdentmanager")
    public HashMap<String,Object> sutdentmanager(HashMap<String,Object> map){
        map=new HashMap<String,Object>();
        List<List> list1=new ArrayList<List>();
        for(User user: userDo.getUserManagerList()){
            List<Object> list2=new ArrayList<Object>();
            list2.add(user.getUsername());
            list2.add(user.getTelnumber());
            list2.add(user.getStudentid());
            list2.add(user.getClassname());
            list2.add(user.getSafetyindex());
            list2.add("<button type=\"button\" class=\"btn btn-danger\" onclick=\"setstudent("+ user.getID()+")\">撤销安全员</button>");
            list1.add(list2);
        }
        map.put("data",list1);
        System.out.println(map);
        return map;
    }


    //    获得学生表
    @RequestMapping("/student")
    public HashMap<String,Object> student(HashMap<String,Object> map){
        map=new HashMap<String,Object>();
        List<List> list1=new ArrayList<List>();
        for(User user: userDo.getUserList()){
            List<Object> list2=new ArrayList<Object>();
            list2.add(user.getUsername());
            list2.add(user.getTelnumber());
            list2.add(user.getStudentid());
            list2.add(user.getClassname());
            list2.add(user.getSafetyindex());
            list2.add("<button type=\"button\" class=\"btn btn-danger\" onclick=\"setstudentmanager("+ user.getID()+")\">任命安全员</button>");
            list1.add(list2);
        }
        map.put("data",list1);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/sosnumber")
    public int sosNumber(HashMap<Object, Object> map){
//        System.out.println("查询当前求救数！");
        return Univcollie.soslist.size();
    }

    @RequestMapping("/stop_sos")
    public String stop_sos(int id,HashMap<Object, Object> map){
        try {
            Univcollie.soslist.remove(id);
            return "已终止求救信号！";
        }catch (Exception e){
            return "无法终止求救信号！";
        }
    }

    @RequestMapping("/delectTeacher")
    public void delectTeacher(int id){
        System.out.println("删除id为"+id+"的教师...");
        teacherDo.delectTeacher(id);
        System.out.println("删除成功！");
    }

    @RequestMapping("/setstudent")
    public void setstudent(int id){
        System.out.println("更新id为"+id+"的学生安全员为普通学生...");
        userDo.setstudent(id);
        System.out.println("更新成功！");
    }

    @RequestMapping("/setstudentmanager")
    public void setstudentmanager(int id){
        System.out.println("更新id为"+id+"的学生为安全员...");
        userDo.setstudentmanager(id);
        System.out.println("更新成功！");
    }


    @RequestMapping("/getlocation")
    public JSONObject getlocation(){
        System.out.println("获取当前围栏中心坐标...");
        JSONArray jsonArray= locationDo.selectEnclosure().getJSONObject("data").getJSONArray("rs_list");
        String center=jsonArray.getJSONObject(0).getString("center");
        String radius=jsonArray.getJSONObject(0).getString("radius");
        JSONObject json=new JSONObject();
        json.put("longitude",center.split(",")[0]);
        json.put("latitude",center.split(",")[1]);
        json.put("radius",radius);
        System.out.println("获取成功！");
        return json;
    }

    @RequestMapping("/getInfor")
    public JSONObject getSostel(){
        System.out.println("获取当前急救电话与打卡时间...");
        JSONObject json=new JSONObject();
        HashMap<String,Object> map= systemDo.getAllinfo();
        json.put("begintime_h",map.get("begintime").toString().split(":")[0]);
        json.put("begintime_m",map.get("begintime").toString().split(":")[1]);
        json.put("overtime_h",map.get("overtime").toString().split(":")[0]);
        json.put("overtime_m",map.get("overtime").toString().split(":")[1]);
        json.put("securitytel",map.get("securitytel").toString());
        json.put("matterstel",map.get("matterstel").toString());
        System.out.println("获取成功！");
        return json;
    }


    @RequestMapping("/reloadsystem")
    public void reloadsystem(){
        System.out.println("重置系统。。。");
        systemDo.reloadsystem();
        System.out.println("重置成功！");
    }
}
