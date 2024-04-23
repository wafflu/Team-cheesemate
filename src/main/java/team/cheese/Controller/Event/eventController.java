package team.cheese.Controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.cheese.Service.Event.eventService;
import team.cheese.Domain.Event.eventDto;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/event")
public class eventController {
    @Autowired
    eventService service;

    @GetMapping(value = "/")
    public String event(Model model) {
        ArrayList<eventDto> arr = service.getDtolist();
        model.addAttribute("eventarr", arr);
        return "event";
    }
}
