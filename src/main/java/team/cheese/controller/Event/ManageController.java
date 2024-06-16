package team.cheese.controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.SearchDto;
import team.cheese.domain.event.EventDto;
import team.cheese.entity.PageHandler;
import team.cheese.service.ImgService;
import team.cheese.service.event.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/Manage")
public class ManageController {
    @Autowired
    EventService eventService;

    @Autowired
    ImgService imgService;
    @GetMapping(value = "/event")
    public String event(String cd,Integer page, Model model) {
        String nowcd = cd==null?"P":cd;
        int nowpage = page==null?1:page;
        PageHandler ph = new PageHandler(eventService.count(nowcd),nowpage);
        ArrayList<EventDto> arr = eventService.getPageList(ph.getOffset(), nowcd, ph.getPageSize());
        model.addAttribute("eventarr", arr);
        model.addAttribute("ph", ph);
        model.addAttribute("cd", nowcd);
        return "/event/manageEvent";
    }
    @GetMapping(value = "/read")
    public String read(Long evt_no, Model model) {
        EventDto dto = eventService.getContent(evt_no);
        model.addAttribute("dto", dto);
        model.addAttribute("readonly", "readonly");
        return "event/manageEventWrite";
    }
    @GetMapping(value = "/modify")
    public String modify(Long evt_no, Model model) {
        EventDto dto = eventService.getContent(evt_no);
        model.addAttribute("dto", dto);
        return "/event/manageEventWrite";
    }
    @GetMapping(value = "/write")
    public String write() {
        return "/event/manageEventWrite";
    }

    @PostMapping(value = "/write")
    public String write(EventDto dto, String imgname) throws IOException {
        eventService.eventRegister(dto, imgname);
        return "redirect:/Manage/read?evt_no="+dto.getEvt_no();
    }
    @PostMapping(value = "/modify")
    public String modify(Long evt_no,EventDto dto) throws Exception {
        dto.setEvt_no(evt_no);
        int result=eventService.modifyEvent(dto);
        return "redirect:/Manage/read?evt_no="+dto.getEvt_no();

    }
    @GetMapping(value = "/remove")
    public String remove(Long evt_no) {
        EventDto dto=eventService.getContent(evt_no);
        dto.setActive_s_cd("C");
        eventService.changeState(dto);
        return "redirect:/Manage";
    }
    @PostMapping(value = "search")
    public String Search(SearchDto dto) {
        int nowpage = 1;
        PageHandler ph = new PageHandler(eventService.searchCount(dto.getCd(),dto.getContents()),nowpage);
        List<EventDto> arr=eventService.getSearchList(dto.getCd(), dto.getContents(), ph.getOffset());
        return "/event/manageEventWrite";
    }
}
