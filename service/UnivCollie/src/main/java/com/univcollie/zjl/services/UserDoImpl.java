package com.univcollie.zjl.services;

import com.univcollie.zjl.DAO.UserDAO;
import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.entity.Userinfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;


@Service
public class UserDoImpl implements UserDo{

    @Autowired
    private UserDAO userDAO;

    //    插入一条用户记录 插入前的主键验证：防止ID主见递增
    public int checkupKey(String telnumber,String studentid){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("telnumber",telnumber);
        map.put("studentid",studentid);
        if(userDAO.checkupKey(map)!=null)
            return 1;
        return 0;
    }
    //    插入一条用户记录
    @Transactional
    public int insertService(User user){
        HashMap<String,Object> map = new HashMap<String,Object>();
//        username,password,telnumber,studentid,age,sex,identity,classname,safetyindex
//        #{username},#{password},#{telnumber},#{age},#{sex},#{identity},#{classname},#{safetyindex}
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("telnumber",user.getTelnumber());
        map.put("studentid",user.getStudentid());
        map.put("age",user.getAge());
        map.put("sex",user.getSex());
        map.put("identity",user.getIdentity());
        map.put("classname",user.getClassname());
        map.put("safetyindex",user.getSafetyindex());
        int result_index=userDAO.insertUser(map);
        result_index=userDAO.insertUser_punch();
        result_index=userDAO.insertUser_dis();
        return result_index;
    }

//    登陆用方法
    public User selectUser(String telnumber,String password){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("telnumber",telnumber);
        map.put("password",password);
        User user = userDAO.getUser(map);
//        System.out.println(user);
        return user;
    }

    //用户登陆后获取个人信息
    @Override
    public Userinfor selectUserInfor(int id) {
        Userinfor userinfor=userDAO.getUserInfor(id);
        return userinfor;
    }
}