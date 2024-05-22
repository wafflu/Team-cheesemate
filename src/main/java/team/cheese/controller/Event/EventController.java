package team.cheese.controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import team.cheese.domain.event.EventDto;
import team.cheese.entity.PageHandler;
import team.cheese.service.event.EventService;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/event")
public class EventController {
    @Autowired
    EventService service;

    @GetMapping(value = "")
    public String event(String cd,Integer page, Model model) {
        String nowcd = cd==null?"P":cd;
        int nowpage = page==null?1:page;
        PageHandler ph = new PageHandler(service.count(nowcd),nowpage);
        ArrayList<EventDto> arr = service.getPageList(ph.getOffset(), nowcd, ph.getPageSize());
        model.addAttribute("eventarr", arr);
        model.addAttribute("ph", ph);
        model.addAttribute("cd", nowcd);
        return "/event/event";
    }

    @GetMapping(value = "read")
    public String read(Long evt_no, Model model) {
        EventDto dto = service.getContent(evt_no);
        model.addAttribute("dto", dto);
        return "/event/read";
    }
}
