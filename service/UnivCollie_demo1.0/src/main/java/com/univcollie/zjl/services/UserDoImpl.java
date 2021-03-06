package com.univcollie.zjl.services;

import com.univcollie.zjl.DAO.UserDAO;
import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.entity.Userinfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;


@Service
public class UserDoImpl implements UserDo{

    @Autowired
    private UserDAO userDAO;

    //    插入一条用户记录 插入前的主键验证：防止ID主键递增
    public int checkupKey(String telnumber,String studentid){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("telnumber",telnumber);
        map.put("studentid",studentid);
        if(userDAO.checkupKey(map).length>0)
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

//    用户更新电子病例
    @Override
    public int updataDis(String field, String content, int id) {
        int result=0;
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("ID",id);
        switch (field){
            case "allergylog":
                map.put("allergylog",content);
                result = userDAO.updata_allergylog(map);
                return result;
            case "medicationlog":
                map.put("medicationlog",content);
                result = userDAO.updata_medicationlog(map);
                return result;
            case "illog":
                map.put("illog",content);
                result = userDAO.updata_illog(map);
                return result;
        }
        return 0;
    }

    //打卡用方法
    @Override
    public int punchSuccess(Integer id) {
//        System.out.println("查询今日打卡信息："+userDAO.selectTodayPunch(id));
//        System.out.println("更新信息："+userDAO.updataPunchSuccess(id));
        System.out.println("打卡成功更新数据库！");
        if(userDAO.selectTodayPunch(id)==0) {
            System.out.println("更新完成！");
            userDAO.updataPunchSuccess(id);
            return 1;
        }
        System.out.println("用户"+id+"今日已打卡！");
        return 0;
    }

    //    admin页面方法----得到总用户数
    @Override
    public int getMaxID() {
        return  userDAO.getMaxID();
    }

//    得到学生安全员列表
    @Override
    public List<User> getUserManagerList() {
        return userDAO.getManagerStudentList();
    }

//    取消其安全员权限
    public void setstudent(int id){
        userDAO.setStudent(id);
    }

//    得到普通学生表
    public List<User> getUserList(){
        return userDAO.getStudentList();
    }

    //   设置其安全员权限
    public void setstudentmanager(int id){
        userDAO.setstudentmanager(id);
    }
}
