package team.cheese.controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.EventDto;
import team.cheese.service.EventServiceImp;
import team.cheese.service.ImgService;

import java.io.IOException;

@Controller
@RequestMapping(value = "/event")
public class EventController {
    @Autowired
    EventServiceImp eventService;
    
    @Autowired
    ImgService imgService;


    @GetMapping(value = "/write")
    public String write() {
        return "write";
    }

    @PostMapping(value = "/write")
    public String write(EventDto dto, String imgname) throws IOException {
        eventService.eventRegister(dto, imgname);
        System.out.println("완료");
        return "home";
    }
}
