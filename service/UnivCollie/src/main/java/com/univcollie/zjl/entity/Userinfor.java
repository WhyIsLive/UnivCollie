package com.univcollie.zjl.entity;

//用户个人信息类 用于对视图userinfo的支持
public class Userinfor {
    private int ID;
    private int success;
    private int fail;
    private String teachername;
    private String teachertel;
    private int safetyindex;
    private String allergylog;
    private String medicationlog;
    private String illog;
    private String secname;
    private String sectel;
    private String securitytel;
    private String matterstel;
    private String bigintime;
    private String overtime;

    public String getIllog() {
        return illog;
    }

    public void setIllog(String illog) {
        this.illog = illog;
    }

    public String getBigintime() {
        return bigintime;
    }

    public void setBigintime(String bigintime) {
        this.bigintime = bigintime;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getSecuritytel() {
        return securitytel;
    }

    public void setSecuritytel(String securitytel) {
        this.securitytel = securitytel;
    }

    public String getMatterstel() {
        return matterstel;
    }

    public void setMatterstel(String matterstel) {
        this.matterstel = matterstel;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeachertel() {
        return teachertel;
    }

    public void setTeachertel(String teachertel) {
        this.teachertel = teachertel;
    }

    public int getSafetyindex() {
        return safetyindex;
    }

    public void setSafetyindex(int safetyindex) {
        this.safetyindex = safetyindex;
    }

    public String getAllergylog() {
        return allergylog;
    }

    public void setAllergylog(String allergylog) {
        this.allergylog = allergylog;
    }

    public String getMedicationlog() {
        return medicationlog;
    }

    public void setMedicationlog(String medicationlog) {
        this.medicationlog = medicationlog;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public String getSectel() {
        return sectel;
    }

    public void setSectel(String sectel) {
        this.sectel = sectel;
    }
}
