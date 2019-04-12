package com.univcollie.zjl.services;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class HttpGetPost {
    public JSONObject DoPost(String url,Map<String, String> hashMap){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(hashMap, httpHeaders);
    //执行请求
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST,requestEntity, String.class);
    //获得返回值
    String body = resp.getBody();
    System.out.println(body.toString());
     return  JSONObject.parseObject(body.toString());
    }

    public JSONObject DoGet(String url,Map<String, String> hashMap){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(hashMap, httpHeaders);
        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url,HttpMethod.GET,requestEntity,String.class);
        //获得返回值
        String body = resp.getBody();
        System.out.println("GET请求返回得Body体："+body.toString());
        return  JSONObject.parseObject(body.toString());
    }

    public JSONObject DoDelete(String url,Map<String, String> hashMap){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(hashMap, httpHeaders);
        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.DELETE,requestEntity, String.class);
        //获得返回值
        String body = resp.getBody();
        System.out.println(body.toString());
        return  JSONObject.parseObject(body.toString());
    }

}
