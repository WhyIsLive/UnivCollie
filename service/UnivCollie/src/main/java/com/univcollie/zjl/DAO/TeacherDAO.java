package com.univcollie.zjl.DAO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDAO {
    //获得teacher总数
    int getMaxID();
}
