package team.cheese.controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.EventDto;
import team.cheese.domain.EventPageHanddler;
import team.cheese.domain.SearchDto;
import team.cheese.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/event")
public class EventController {
    @Autowired
    EventService service;

    @GetMapping(value = "")
    public String event(String cd,Integer page, Model model) {
        String nowcd = cd==null?"P":cd;
        int nowpage = page==null?1:page;
        EventPageHanddler ph = new EventPageHanddler(service.count(nowcd),nowpage);
        ArrayList<EventDto> arr = service.getPageList(ph.getStartBno(), nowcd, ph.getMAXCONTENT());
        model.addAttribute("eventarr", arr);
        model.addAttribute("ph", ph);
        model.addAttribute("cd", nowcd);
        return "event";
    }
    @GetMapping(value = "read")
    public String read(Long evt_no, Model model) {
        EventDto dto = service.getContent(evt_no);
        model.addAttribute("dto", dto);
        model.addAttribute("readonly", "readonly");
        return "write";
    }
    @GetMapping(value = "modify")
    public String modify(Long evt_no, Model model) {
        EventDto dto = service.getContent(evt_no);
        model.addAttribute("dto", dto);
        return "write";
    }
    @GetMapping(value = "write")
    public String write() {
        return "write";
    }

    @PostMapping(value = "write")
    public String write(EventDto dto) {
        System.out.println(dto);
        service.eventRegister(dto);
        return "redirect:/event";
    }
    @PostMapping(value = "modify")
    public String modify(Long evt_no,EventDto dto) throws Exception {
        dto.setEvt_no(evt_no);
        int result=service.updateContent(dto);
        if(result==1) {
            throw new Exception("업데이트 실패");
        }
        else{
            return "redirect:/read?evt_no="+dto.getEvt_no();
        }
    }
    @GetMapping(value = "remove")
    public String remove(Long evt_no) {
        EventDto dto=service.getContent(evt_no);
        dto.setActive_s_cd("C");
        service.changeState(dto);
        return "redirect:/event";
    }
    @PostMapping(value = "search")
    @ResponseBody
    public List<EventDto> SearchAjax(@RequestBody SearchDto dto) {
        System.out.println("Welcom Ajax");
        System.out.println(dto);
        int nowpage = 1;
        EventPageHanddler ph = new EventPageHanddler(service.searchCount(dto.getCd(),dto.getContents()),nowpage);
        List<EventDto> arr=service.getSearchList(dto.getCd(), dto.getContents(), ph.getStartBno());
        return arr;
    }
}
