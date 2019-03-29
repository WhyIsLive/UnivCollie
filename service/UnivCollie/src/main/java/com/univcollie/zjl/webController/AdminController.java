package com.univcollie.zjl.webController;

import com.alibaba.fastjson.JSONObject;
import com.univcollie.zjl.services.TeacherDo;
import com.univcollie.zjl.services.UserDo;
import com.univcollie.zjl.Univcollie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserDo userDo;
    @Autowired
    TeacherDo teacherD0;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("student", userDo.getMaxID());
        model.addAttribute("teacher", teacherD0.getMaxID());
        model.addAttribute("punchnumber", Univcollie.punchnumber);
        return "index";
    }

    @RequestMapping("/sosinfor")
    public String sosinfor(Model model){
        return "sosinfor";
    }


}
