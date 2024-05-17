package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.EventDaoImp;
import team.cheese.domain.EventDto;
import team.cheese.domain.ImgDto;

import java.io.IOException;
import java.util.*;

@Service
public class EventServiceImp {
    @Autowired
    EventDaoImp dao;

    @Autowired
    ImgService imgService;

    public int eventRegister(EventDto dto, String imgname) throws IOException {
        String S_Cd = isWithinRange(dto.getS_date(),dto.getE_date());
        dto.setActive_s_cd(S_Cd);

        ImgDto Eventimg = imgService.reg_img_one(imgname);
        dto.setImg_full_rt(Eventimg.getImg_full_rt());
        dto.setGroup_no(Eventimg.getGroup_no());
        dto.setAd_id("asd0");
        dto.setFirst_id("asd1");
        dto.setLast_id("asd2");

        System.out.println("dto : "+dto);

        int result = dao.insert(dto);
        System.out.println("imgname : "+result);

        return 1;
    }

    public String isWithinRange(Date startDate ,Date endDate){
        Date nowDate = new Date();
        if(nowDate.after(startDate)&& nowDate.before(endDate))
            return "P";
        if(nowDate.before(startDate))
            return "B";
        else
            return "F";
    }
}