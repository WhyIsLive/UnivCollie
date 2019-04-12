package com.univcollie.zjl.DAO;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


@Mapper
public interface SysinforDAO {
//    更新急救电话
    void updataSosrules(HashMap<String, Object> map);

    //    更新打卡时间
    void updataPunchrules(HashMap<String, Object> map);

    HashMap<String,Object> getAllinfo();
}
