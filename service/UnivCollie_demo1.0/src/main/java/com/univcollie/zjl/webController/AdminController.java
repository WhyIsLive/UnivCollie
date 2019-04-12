package com.univcollie.zjl.webController;

import com.univcollie.zjl.services.*;
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
    TeacherDo teacherDo;

    @Autowired
    PunchDataDo punchDataDo;

    @Autowired
    LocationDo locationDo;

    @Autowired
    SystemDo systemDo;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("student", userDo.getMaxID());
        model.addAttribute("teacher", teacherDo.getMaxID());
        model.addAttribute("punchnumber", Univcollie.punchnumber);
        model.addAttribute("proportion2019",punchDataDo.getGradePunchProportion("2019"));
        model.addAttribute("proportion2018",punchDataDo.getGradePunchProportion("2018"));
        model.addAttribute("proportion2017",punchDataDo.getGradePunchProportion("2017"));
        model.addAttribute("proportion2016",punchDataDo.getGradePunchProportion("2016"));
        return "index";
    }

    @RequestMapping("/sosinfor")
    public String sosinfor(Model model){
        return "sosinfor";
    }


    @RequestMapping("/teachermanager")
    public String teachermanager(Model model){
        return "teachermanager";
    }

    @RequestMapping("/insertTeacher")
    public String insertTeacher(String teachername,String teacherpassword,String teachertel,String teacherclass1,String teacherclass2){
        System.out.println("添加教师...");
        teacherDo.insertTeacher(teachername,teacherpassword,teachertel,teacherclass1,teacherclass2);
        System.out.println("添加成功！");
        return "redirect:/admin/teachermanager";
    }

    @RequestMapping("/updataEnclosure")
    public String updataEnclosure(String longitude,String latitude,String radius){
        System.out.println("更新电子围栏..");
        locationDo.updataEnclosure(longitude,latitude,radius);
        System.out.println("更新成功！");
        return "redirect:/admin/triprules";
    }



    @RequestMapping("/updataSosrules")
    public String updataSosrules(String medictel,String polictel){
        System.out.println("更新应急电话..");
        systemDo.updataSosrules(medictel,polictel);
        System.out.println("更新成功！");
        return "redirect:/admin/sospunch";
    }


    @RequestMapping("/updataPunchrules")
    public String updataPunchrules(String begintime_h,String begintime_m,String overtime_h,String overtime_m){
        System.out.println("更新应急电话..");
        systemDo.updataPunchrules(begintime_h+":"+begintime_m,overtime_h+":"+overtime_m);
        System.out.println("更新成功！");
        return "redirect:/admin/sospunch";
    }

    @RequestMapping("/studentmanager")
    public String studentmanager(Model model){
        return "studentmanager";
    }

    @RequestMapping("/student")
    public String student(Model model){
        return "student";
    }

    @RequestMapping("/triprules")
    public String triprules(Model model){
        return "triprules";
    }

    @RequestMapping("/sospunch")
    public String sospunch(Model model){
        return "sospunch";
    }
}
