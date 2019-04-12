package com.univcollie.zjl.entity;

public class Teacher {
    private int ID;
    private String teachername;
    private String teacherpassword;
    private String teachertel;
    private String teachermajor;
    private String teacherclass;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeacherpassword() {
        return teacherpassword;
    }

    public void setTeacherpassword(String teacherpassword) {
        this.teacherpassword = teacherpassword;
    }

    public String getTeachertel() {
        return teachertel;
    }

    public void setTeachertel(String teachertel) {
        this.teachertel = teachertel;
    }

    public String getTeachermajor() {
        return teachermajor;
    }

    public void setTeachermajor(String teachermajor) {
        this.teachermajor = teachermajor;
    }

    public String getTeacherclass() {
        return teacherclass;
    }

    public void setTeacherclass(String teacherclass) {
        this.teacherclass = teacherclass;
    }
}
