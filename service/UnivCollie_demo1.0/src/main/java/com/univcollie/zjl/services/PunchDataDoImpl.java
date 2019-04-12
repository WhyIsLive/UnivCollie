package com.univcollie.zjl.services;

import com.univcollie.zjl.DAO.PunchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.HashMap;

@Service
public class PunchDataDoImpl implements PunchDataDo {

    @Autowired
    PunchDAO punchDAO;

    @Override
    public String getGradePunchProportion(String grade) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("today",0);
        map.put("grade",grade+"%");
        int failPunch=punchDAO.punchMax(map);
        System.out.println("得到"+grade+"未打卡总人数为：" +failPunch);
        map.put("today",1);
        int successPunch=punchDAO.punchMax(map);
        System.out.println("得到"+grade+"已打卡总人数为：" + successPunch);
        if(successPunch!=0&&failPunch==0) {
            return "100";
        }
        if(successPunch!=0&&failPunch!=0) {
            NumberFormat numberFormat = NumberFormat.getInstance();
// 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format((float) successPunch / (float) (failPunch + successPunch));
            if (result.substring(2, 3).equals("0")) {
                result = result.substring(3);
                System.out.println("得到打卡百分比为：" + result + "%");
            } else {
                result = result.substring(2);
                System.out.println("得到打卡百分比为：" + result + "%");
            }
            return result;
        }
       return "0";
    }
}
