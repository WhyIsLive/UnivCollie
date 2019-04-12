package com.univcollie.zjl.services;

import com.univcollie.zjl.entity.Teacher;

import java.util.List;

public interface TeacherDo {
    //admin页面 获得教师总数
    int getMaxID();
    Teacher getOneTeacher(String telnumber, String password);
    List<Teacher> getAllTeacher();
    void delectTeacher(int id);
    void insertTeacher(String teachername, String teacherpassword, String teachertel, String teacherclass1, String teacherclass2);
}
