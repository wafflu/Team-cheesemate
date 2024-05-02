package team.cheese.Controller.CommunityBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.service.CommunityBoard.CommunityBoardService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
//게시물이 없을때는 어떻게 할것인지 생각 (예외에 대한 생각)
//dto가 null인 경우 생각
//경로 중요하기 때문에 나눠야한다. community/home
//등록/수정 잘 되었는지 메세지 출력
@Controller
@RequestMapping(value = "/community")
public class CommunityBoardController {
    @Autowired
    CommunityBoardService communityBoardService;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String communityBoardHome(Model m)throws Exception{


        try{
            List<CommunityBoardDto>list = communityBoardService.readAll();
            m.addAttribute("list", list);

        }catch (Exception e){
            e.printStackTrace();
            return "/ErrorPage";
        }
        return "/CommunityHome";

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String communityBoardList(CommunityBoardDto communityBoardDto, Model m) throws Exception {

        List<CommunityBoardDto> list = communityBoardService.readAll();
        m.addAttribute("list", list);
        return "/CommunityList";
    }

    @RequestMapping(value = "/story", method = RequestMethod.GET)
    @ResponseBody
    public List test(Character ur_state) throws Exception {
        List<CommunityBoardDto> list = communityBoardService.readAll();

        return list;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String communityBoard() throws Exception {
        //만약 user123이라면
        //만약 아이디가 없다면
        return "/Board";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String write(CommunityBoardDto communityBoardDto, Model m) throws Exception {

        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(2);
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setNick("skyLee");


        String title = communityBoardDto.getTitle();
        String contents = communityBoardDto.getContents();
        String commu_cd = communityBoardDto.getcommu_cd();


        communityBoardDto.setcommu_cd(commu_cd);
        communityBoardDto.setTitle(title);
        communityBoardDto.setContents(contents);

        System.out.println("연결1");


        communityBoardService.write(communityBoardDto);
        System.out.println("연결2");
        m.addAttribute("communityBoardDto", communityBoardDto);

        return "/Board";

    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Integer no, Model m) throws Exception {

        CommunityBoardDto communityBoardDto = communityBoardService.read(no);
        m.addAttribute("communityBoardDto", communityBoardDto);
        return "/Board";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String update(CommunityBoardDto communityBoardDto, Model m) throws Exception {
        communityBoardService.modify(communityBoardDto);

        System.out.println(communityBoardDto.toString());
        m.addAttribute("communityBoardDto", communityBoardDto);
        return "/Board";
    }
    @RequestMapping(value = "/userStateChange", method = RequestMethod.POST)
    public ResponseEntity<?> userStateChange(@RequestBody CommunityBoardDto communityBoardDto) throws Exception {

        try {
            int updateResult = communityBoardService.userStateChange(communityBoardDto);

            if (updateResult == 1) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "상태 변경에 성공하였습니다.");
                response.put("newState", communityBoardDto.getur_state());
                return ResponseEntity.ok(response);

            }
            else {
                // 정상적으로 처리되지 않은 경우, 내부 서버 오류 응답
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상태 변경 실패");
            }
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류 발생: " + e.getMessage());
        }

    }

}
