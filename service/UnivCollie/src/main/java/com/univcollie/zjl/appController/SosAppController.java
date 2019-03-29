package com.univcollie.zjl.appController;

import com.univcollie.zjl.Univcollie;
import com.univcollie.zjl.entity.Sos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/sos")
public class SosAppController {


    @RequestMapping("/medical")
    public int addSos( int id, String location,String stuname,String stutel,String illog, HashMap<String, String> map){
        System.out.print(id+ "    "+location);
        if(Univcollie.soslist==null||!Univcollie.soslist.containsKey(id)) {
            Sos sos = new Sos();
            sos.setStuid(id);
            sos.setLocation(location);
            sos.setStuname(stuname);
            sos.setStutel(stutel);
            sos.setBeigintime(new Date());
            sos.setSostype("医疗求助");
            sos.setIllog(illog);
            Univcollie.soslist.put(sos.getStuid(),sos);
            return 1;
        }else{
            return -1;
        }
    }
}
