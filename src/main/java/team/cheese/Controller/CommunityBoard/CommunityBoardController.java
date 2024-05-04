package team.cheese.Controller.CommunityBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.service.CommunityBoard.CommunityBoardService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping(value = "/community")
public  class CommunityBoardController {
    @Autowired
    CommunityBoardService communityBoardService;


        //community메인페이지
        @RequestMapping(value = "/home", method = RequestMethod.GET)
        public String communityBoardHome(Model m)throws Exception{
            try{
                List<CommunityBoardDto> list = communityBoardService.readAll();
                m.addAttribute("list", list);
            }catch (Exception e){
                e.printStackTrace();
                return "/ErrorPage";
            }
            return "/CommunityHome";

        }

        //community세부 리스트 페이지
        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public String communityBoardList(CommunityBoardDto communityBoardDto, Model m) throws Exception {

            List<CommunityBoardDto> list = communityBoardService.readAll();
            m.addAttribute("list", list);
            return "/CommunityList";
        }

        //community세부 리스트 페이지ajax
        @RequestMapping(value = "/story", method = RequestMethod.GET)
        @ResponseBody
        public List test(Character ur_state) throws Exception {
            List<CommunityBoardDto> list = communityBoardService.readAll();

            return list;
        }

        //글쓰기 페이지로 이동
        @RequestMapping(value = "/write", method = RequestMethod.GET)
        public String communityBoard() throws Exception {
            return "/Board";
        }


        //세션값 필요

        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public String write(MultipartHttpServletRequest multi, CommunityBoardDto communityBoardDto, Model m, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception {
            //1.세션값에 저장된 아이디와 세션이 일치하는지 확인해아햔다.->현재 임의 설정
            HttpSession session = request.getSession(true);
            session.setAttribute("ur_id", "user123");
            String session_id = (String) session.getAttribute("ur_id");

            //2.세션값에 저장된 아이디가 일치하지않거나 없는 경우 예외처리
            if(session_id==null){
                redirectAttributes.addFlashAttribute("error","유효하지 않습니다.");
                return "redirect:/ErrorPage";
            }



            communityBoardDto.setur_id(session_id);
            communityBoardDto.setaddr_cd("11010720");
            communityBoardDto.setaddr_no(2);
            communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
            communityBoardDto.setNick("skyLee");

            String title = communityBoardDto.getTitle();
            String contents = communityBoardDto.getContents();
            String commu_cd = communityBoardDto.getcommu_cd();



            System.out.println("연결1");
            System.out.println("-----------------");



            MultipartFile file = multi.getFile("image");


            if (file != null && file.getSize() != 0) {
                communityBoardDto.setImg_full_rt(communityBoardService.saveFile(file));
            } else {
                communityBoardDto.setImg_full_rt(null);
            }
            try {
                System.out.println(communityBoardDto);
                communityBoardService.write(communityBoardDto);
                System.out.println("연결2");
                m.addAttribute("multi", multi);
                m.addAttribute("communityBoardDto", communityBoardDto);

               redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록되었습니다.");
                return "redirect:/community/list"; // 성공 시 게시판 목록 페이지로 리다이렉트

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "파일 저장 중 에러가 발생했습니다.");
                return "redirect:/Board"; // 에러 시 사용자를 등록 페이지로 리다이렉트
            }


        }
     //읽기
     @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Integer no, Model m) throws Exception {


        CommunityBoardDto communityBoardDto = communityBoardService.read(no);
        m.addAttribute("communityBoardDto", communityBoardDto);


        String imagePath = loadImagePath(communityBoardDto.getImg_full_rt());
        m.addAttribute("imagePath", imagePath);
         System.out.println(communityBoardDto);
        return "/CommunityBoard";
    }




    //세션값 필요
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String update(CommunityBoardDto communityBoardDto, Model m,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {

        //1.세션값에 저장된 아이디와 세션이 일치하는지 확인해아햔다.->현재 임의 설정
        HttpSession session = request.getSession(true);
        session.setAttribute("ur_id", "user123");
        String session_id = (String) session.getAttribute("ur_id");

        //2.세션값에 저장된 아이디가 일치하지않거나 없는 경우 예외처리
        if(session_id==null){
            redirectAttributes.addFlashAttribute("error","유효하지 않습니다.");
            return "redirect:/ErrorPage";
        }else if(!Objects.equals(communityBoardDto.getur_id(), session_id)){
            redirectAttributes.addFlashAttribute("error","유효하지 않습니다.");
            return "redirect:/ErrorPage";
        }


        //3.수정
        communityBoardService.modify(communityBoardDto);

        System.out.println(communityBoardDto.toString());
        m.addAttribute("communityBoardDto", communityBoardDto);
        return "/Board";
    }

    //세션값 필요
    //삭제(상태변경)
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

    private String loadImagePath(String imgPath){
        if(imgPath == null || imgPath.isEmpty()){
            return "";
        }
        return imgPath;
    }

}




