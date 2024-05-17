package team.cheese.service;

import team.cheese.domain.EventDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface EventService {
    ArrayList<EventDto> getAlllist();

    int count(String nowcd);

    int searchCount(String cd, String contents);

    ArrayList<EventDto> getPageList(int startnum, String cd, int maxcontents);

    List<EventDto> getSearchList(String cd, String contents, int startnum);

    int eventRegister(EventDto dto, String imgname) throws IOException;

    EventDto getContent(Long evt_no);

    int updateContent(EventDto dto);

    int changeState(EventDto dto);
}
