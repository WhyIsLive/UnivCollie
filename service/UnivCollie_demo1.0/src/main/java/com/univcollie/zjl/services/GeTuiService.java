package com.univcollie.zjl.services;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeTuiService {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "w7oeT2bSdp7ODcfltx9uA5";
    private static String appKey = "6SuWQGvVoa56A6XrgB0Lg1";
    private static String masterSecret = "iJrUXIvx6Z7hqMh3W7zZm4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    public void tuisong(String begintime,String overtime){

        IGtPush push = new IGtPush(url, appKey, masterSecret);

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        NotificationTemplate template = new NotificationTemplate ();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("打卡时间有变更哟！");
        template.setText("打卡时间变更为"+begintime+"-"+overtime);
        template.setChannelLevel(4);
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 1200);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
}
