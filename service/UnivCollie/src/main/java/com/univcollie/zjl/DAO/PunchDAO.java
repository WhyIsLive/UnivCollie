package com.univcollie.zjl.DAO;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface PunchDAO {
//    减分
    int reloadPunch_1();
//    加失败次数
    int reloadPunch_2();
//    重置每日打卡
    int reloadPunch_3();

    int punchMax(HashMap<String,Object> map);

    void reloadPunchNumber_success();
    void reloadPunchNumber_fail();
    void reloadPunchNumber_saftyindex();
}
