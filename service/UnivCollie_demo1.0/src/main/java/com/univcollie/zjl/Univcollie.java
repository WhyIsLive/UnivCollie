package com.univcollie.zjl;

import com.univcollie.zjl.entity.Sos;
import com.univcollie.zjl.timeManage.TimeManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@SpringBootApplication
@Configuration
public class Univcollie {
    public static int punchnumber=0;
    @Autowired
    TimeManage timeManage;
    public static HashMap<Integer,Sos> soslist=new HashMap<Integer,Sos>(); //急救缓存<id，急救信息>

    public static void main(String[] args) {
        SpringApplication.run(Univcollie.class, args);
    }
}
