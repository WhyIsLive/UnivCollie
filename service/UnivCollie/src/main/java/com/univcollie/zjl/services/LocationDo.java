package com.univcollie.zjl.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface LocationDo {
    public boolean creatEnclosure(String name,String center,String radius);
    public JSONObject selectEnclosure();
    public boolean updataEnclosure(String longitude,String latitude,String radius);
    public boolean cheakEnclosure(String longtitude, String latitude);
    public boolean removeEnclosure(String gid);
}
