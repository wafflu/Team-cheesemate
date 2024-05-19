package team.cheese.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.event.EventDto;
import team.cheese.domain.SearchDto;
import team.cheese.entity.PageHandler;
import team.cheese.service.event.EventService;
import team.cheese.service.ImgService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/event")
public class EventController {
    @Autowired
    EventService eventService;
    
    @Autowired
    ImgService imgService;


    @GetMapping(value = "")
    public String event(String cd,Integer page, Model model) {
        String nowcd = cd==null?"P":cd;
        int nowpage = page==null?1:page;
        PageHandler ph = new PageHandler(eventService.count(nowcd),nowpage);
        ArrayList<EventDto> arr = eventService.getPageList(ph.getOffset(), nowcd, ph.getPageSize());
        model.addAttribute("eventarr", arr);
        model.addAttribute("ph", ph);
        model.addAttribute("cd", nowcd);
        return "/event/event";
    }


//    @PostMapping(value = "search")
//    @ResponseBody
//    public List<EventDto> SearchAjax(@RequestBody SearchDto dto) {
//        System.out.println("Welcom Ajax");
//        System.out.println(dto);
//        int nowpage = 1;
//        PageHandler ph = new PageHandler(eventService.searchCount(dto.getCd(),dto.getContents()),nowpage);
//        List<EventDto> arr=eventService.getSearchList(dto.getCd(), dto.getContents(), ph.getOffset());
//        return arr;
//    }
}
