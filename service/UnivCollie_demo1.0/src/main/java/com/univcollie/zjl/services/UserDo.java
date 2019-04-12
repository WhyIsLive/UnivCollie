package com.univcollie.zjl.services;

import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.entity.Userinfor;

import java.util.List;

public interface UserDo {
//    注册用方法
    public int checkupKey(String telnumber, String studentid);
    public int insertService(User user);
    //    登陆用方法
    public User selectUser(String telnumber, String password);
    public Userinfor selectUserInfor(int id);
    public int updataDis(String field, String content, int id);

    //    打卡用方法
    int punchSuccess(Integer id);

//    admin页面学生总数
    public int getMaxID();

//    得到学生安全员列表
    public List<User> getUserManagerList();

//    取消安全员权限
    void setstudent(int id);


    //    得到学生列表
    public List<User> getUserList();

    //    设置安全员权限
    void setstudentmanager(int id);

}
