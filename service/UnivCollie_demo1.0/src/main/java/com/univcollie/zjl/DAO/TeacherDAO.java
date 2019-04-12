package com.univcollie.zjl.DAO;

import com.univcollie.zjl.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TeacherDAO {
    //获得teacher总数
    int getMaxID();

    Teacher teacherLogin(HashMap<String, Object> map);
    //获得整个teacher表
    List<Teacher> getTeacherList();

    //删除一个Teacher
    void delectTeacher(int id);

//    插入一个教师用户
    void insertTeacher(HashMap<String, Object> map);
}
