package com.univcollie.zjl.services;


import com.univcollie.zjl.DAO.TeacherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherDoImpl implements TeacherDo {
    @Autowired
    TeacherDAO teacherDAO;
    @Override
    public int getMaxID() {
        return teacherDAO.getMaxID();
    }
}
