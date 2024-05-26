package team.cheese.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.domain.event.EventDto;
import team.cheese.service.ImgService;
import team.cheese.service.event.EventService;
import team.cheese.service.sale.SaleService;

import java.util.*;

@ControllerAdvice
@Slf4j
public class GlobalController {

    @Autowired
    ImgService imgService;

    @Autowired
    EventService eventService;

    @Autowired
    SaleService saleService;

    @ModelAttribute("headerimglist")
    public List<ImgDto> populateImgList() {
        ArrayList<ImgDto> list = (ArrayList<ImgDto>) imgService.load_cssimg("home");
        return list;
    }

    @ModelAttribute("eventlist")
    public ArrayList<EventDto> eventList() {
        ArrayList<EventDto> eventlist = (ArrayList<EventDto>) eventService.getPageList(1, "", 10);
        return eventlist;
    }

    @ModelAttribute("salelist")
    public ArrayList<SaleDto> saleList() throws Exception {

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 30);

        ArrayList<SaleDto> saleList = (ArrayList<SaleDto>) saleService.getList(map);
        return saleList;
    }

}
