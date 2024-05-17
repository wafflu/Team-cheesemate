package team.cheese.controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.EventDto;
import team.cheese.domain.EventPageHanddler;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SearchDto;
import team.cheese.service.EventService;
import team.cheese.service.EventServiceImp;
import team.cheese.service.ImgService;

import java.io.IOException;
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
        EventPageHanddler ph = new EventPageHanddler(eventService.count(nowcd),nowpage);
        ArrayList<EventDto> arr = eventService.getPageList(ph.getStartBno(), nowcd, ph.getMAXCONTENT());
        model.addAttribute("eventarr", arr);
        model.addAttribute("ph", ph);
        model.addAttribute("cd", nowcd);
        return "event";
    }
    @GetMapping(value = "read")
    public String read(Long evt_no, Model model) {
        EventDto dto = eventService.getContent(evt_no);
        model.addAttribute("dto", dto);
        model.addAttribute("readonly", "readonly");
        return "write";
    }
    @GetMapping(value = "modify")
    public String modify(Long evt_no, Model model) {
        EventDto dto = eventService.getContent(evt_no);
        model.addAttribute("dto", dto);
        return "write";
    }
    @GetMapping(value = "write")
    public String write() {
        return "write";
    }

    @PostMapping(value = "write")
    public String write(EventDto dto, String imgname) throws IOException {
        System.out.println(dto);
        System.out.println(imgname);
        eventService.eventRegister(dto, imgname);
        return "redirect: /event/read?evt_no="+dto.getEvt_no();
    }
    @PostMapping(value = "modify")
    public String modify(Long evt_no,EventDto dto) throws Exception {
        dto.setEvt_no(evt_no);
        int result=eventService.updateContent(dto);
        if(result==1) {
            throw new Exception("업데이트 실패");
        }
        else{
            return "redirect:/read?evt_no="+dto.getEvt_no();
        }
    }
    @GetMapping(value = "remove")
    public String remove(Long evt_no) {
        EventDto dto=eventService.getContent(evt_no);
        dto.setActive_s_cd("C");
        eventService.changeState(dto);
        return "redirect:/event";
    }
    @PostMapping(value = "search")
    @ResponseBody
    public List<EventDto> SearchAjax(@RequestBody SearchDto dto) {
        System.out.println("Welcom Ajax");
        System.out.println(dto);
        int nowpage = 1;
        EventPageHanddler ph = new EventPageHanddler(eventService.searchCount(dto.getCd(),dto.getContents()),nowpage);
        List<EventDto> arr=eventService.getSearchList(dto.getCd(), dto.getContents(), ph.getStartBno());
        return arr;
    }
}
