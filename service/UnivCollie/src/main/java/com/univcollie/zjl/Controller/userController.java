package com.univcollie.zjl.Controller;

import com.alibaba.fastjson.JSONObject;
import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.entity.Userinfor;
import com.univcollie.zjl.services.UserDoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    UserDoImpl userDo;

//    @RequestMapping("/test")
//    public int test() {
//        int id_result=0;
//        return 0;
//    }

    //    登陆服务
    @RequestMapping("/login")
    @ResponseBody
    public JSONObject userlogin(@RequestBody User user, HashMap<String, String> map ) {
        JSONObject jsonParam=new JSONObject();
        User user_result= userDo.selectUser(user.getTelnumber(), user.getPassword());
        if(user_result==null) {
            jsonParam.put("ID", 0);
            return jsonParam;
        }
        jsonParam.put("ID",user_result.getID());
        jsonParam.put("telnumber",user_result.getTelnumber());
        jsonParam.put("studentid",user_result.getStudentid());
        jsonParam.put("username",user_result.getUsername());
        return jsonParam;
    }

//    注册服务
    @RequestMapping("/register")
    @ResponseBody
//    public void insertService(String username,String password,int telnumber,int studentid,int age,String sex,int identity,String classname,int safetyindex)
    public String userRegister(@RequestBody User user, HashMap<String, String> map) {
//        System.out.println(username+password+telnumber+studentid);
        if(userDo.checkupKey(user.getTelnumber(),user.getStudentid())>0)
            return "该手机或学号已被注册！";
        user.setAge(0);
        user.setSex("未知");
        user.setIdentity(0);
        user.setClassname(user.getStudentid().substring(0,10));
        user.setSafetyindex(100);
        if (userDo.insertService(user) > 0)
            return "Success";
        return "注册失败请与管理员联系！";
    }

//    个人信息服务
    @RequestMapping("/infor")
    public JSONObject userInfo(int id,HashMap<String, String> map){
        JSONObject jsonParam=new JSONObject();
        Userinfor infor_result=userDo.selectUserInfor(id);
        jsonParam.put("success",infor_result.getSuccess());
        jsonParam.put("fail",infor_result.getFail());
        jsonParam.put("teachername",infor_result.getTeachername());
        jsonParam.put("teachertel",infor_result.getTeachertel());
        jsonParam.put("safetyindex",infor_result.getSafetyindex());
        jsonParam.put("allergylog",infor_result.getAllergylog());
        jsonParam.put("medicationlog",infor_result.getMedicationlog());
        jsonParam.put("secname",infor_result.getSecname());
        jsonParam.put("sectel",infor_result.getSectel());
        jsonParam.put("securitytel",infor_result.getSecuritytel());
        jsonParam.put("matterstel",infor_result.getMatterstel());
        jsonParam.put("begintime",infor_result.getSecuritytel());
        jsonParam.put("overtime",infor_result.getMatterstel());
        return jsonParam;
    }
}
