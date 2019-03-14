package com.univcollie.zjl.DAO;


import com.univcollie.zjl.entity.User;
import com.univcollie.zjl.entity.Userinfor;
import org.apache.ibatis.annotations.Mapper;


import java.util.HashMap;

//User DAO接口类 映射类
@Mapper
public interface UserDAO {
//    插入user
    Integer checkupKey(HashMap<String,Object> map);
    int insertUser(HashMap<String,Object> map);
    int insertUser_punch();
    int insertUser_dis();

//    查找user
    User getUser(HashMap<String,Object> map);
    Userinfor getUserInfor(Integer ID);
}