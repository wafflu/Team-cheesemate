package team.cheese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import team.cheese.domain.ImgDto;
import team.cheese.service.ImgService;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalController {

    @Autowired
    ImgService imgService;

    @ModelAttribute("imglist")
    public List<ImgDto> populateImgList() {
        ArrayList<ImgDto> list = (ArrayList<ImgDto>) imgService.load_cssimg("home");
//        System.out.println("list : "+list);
        return list;
    }
}
