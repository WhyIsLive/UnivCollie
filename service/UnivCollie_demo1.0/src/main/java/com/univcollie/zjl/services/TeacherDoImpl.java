package com.univcollie.zjl.services;


import com.univcollie.zjl.DAO.TeacherDAO;
import com.univcollie.zjl.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TeacherDoImpl implements TeacherDo {
    @Autowired
    TeacherDAO teacherDAO;
    @Override
    public int getMaxID() {
        return teacherDAO.getMaxID();
    }

    @Override
    public Teacher getOneTeacher(String telnumber, String password) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("telnumber",telnumber);
        map.put("password",password);
        return teacherDAO.teacherLogin(map);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDAO.getTeacherList();
    }

    public void delectTeacher(int id){
        teacherDAO.delectTeacher(id);
    }

    public void insertTeacher(String teachername,String teacherpassword,String teachertel,String teacherclass1,String teacherclass2){
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("teachername",teachername);
        map.put("teacherpassword",teacherpassword);
        map.put("teachertel",teachertel);
        map.put("teacherclass1",teacherclass1);
        map.put("teacherclass2",teacherclass2);
        teacherDAO.insertTeacher(map);
    }
}
