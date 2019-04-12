package com.univcollie.zjl.services;

import com.univcollie.zjl.DAO.PunchDAO;
import com.univcollie.zjl.DAO.SysinforDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SystemDo {
    @Autowired
    PunchDAO punchDAO;

    @Autowired
    SysinforDAO sysinforDAO;

//    重置每日打卡
    public void reloadPunch(){
        punchDAO.reloadPunch_1();
        punchDAO.reloadPunch_2();
        punchDAO.reloadPunch_3();
    }

    //    更新急救电话
    public void updataSosrules(String medictel,String polictel){
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("medictel",medictel);
        map.put("polictel",polictel);
        sysinforDAO.updataSosrules(map);
    }

    //    更新打卡时间
    public void updataPunchrules(String begintime,String overtime){
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("begintime",begintime);
        map.put("overtime",overtime);
        sysinforDAO.updataPunchrules(map);
    }

    //重置打卡信息和安全积分
    public void reloadsystem(){
        punchDAO.reloadPunchNumber_fail();
        punchDAO.reloadPunchNumber_saftyindex();
        punchDAO.reloadPunchNumber_success();
    }

    //    获取所有信息
    public HashMap<String,Object> getAllinfo(){
        sysinforDAO.getAllinfo();
        return sysinforDAO.getAllinfo();
    }
}
