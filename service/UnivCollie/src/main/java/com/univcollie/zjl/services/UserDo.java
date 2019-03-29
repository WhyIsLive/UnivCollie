package com.univcollie.zjl.services;

import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.entity.Userinfor;

public interface UserDo {
//    注册用方法
    public int checkupKey(String telnumber,String studentid);
    public int insertService(User user);
    //    登陆用方法
    public User selectUser(String telnumber,String password);
    public Userinfor selectUserInfor(int id);
    public int updataDis(String field,String content,int id);

//    admin页面学生总数
    public int getMaxID();
}
