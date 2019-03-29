package com.univcollie.zjl.entity;

import java.util.Date;

public class Sos {
    private int stuid;
    private String location;
    private String sostype;

    private String stuname;
    private String stutel;
    private String illog;
    private Date beigintime;

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSostype() {
        return sostype;
    }

    public void setSostype(String sostype) {
        this.sostype = sostype;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStutel() {
        return stutel;
    }

    public void setStutel(String stutel) {
        this.stutel = stutel;
    }

    public String getIllog() {
        return illog;
    }

    public void setIllog(String illog) {
        this.illog = illog;
    }

    public Date getBeigintime() {
        return beigintime;
    }

    public void setBeigintime(Date beigintime) {
        this.beigintime = beigintime;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    private Date overtime;

}
