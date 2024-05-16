package team.cheese.Controller.CommunityBoard;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team.cheese.Domain.Comment.CommentDto;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.service.Comment.CommentService;
import team.cheese.service.CommunityBoard.CommunityBoardService;
import team.cheese.service.CommunityHeart.CommunityHeartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


@Controller
@RequestMapping(value = "/community")
public  class CommunityBoardController {
    @Autowired
    CommunityBoardService communityBoardService;

    @Autowired
    CommunityHeartService communityHeartService;

    //community메인페이지
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String communityBoardHome(Model m) throws Exception {
        try {
            List<CommunityBoardDto> list = communityBoardService.readAll();
            m.addAttribute("list", list);
        } catch (Exception e) {
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
    public String write(MultipartHttpServletRequest multi, CommunityBoardDto communityBoardDto, Model m, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String postOwnerUser = (String) session.getAttribute("ur_id");

        communityBoardDto.setur_id(postOwnerUser);
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


    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Integer no, Model m, RedirectAttributes redirectAttributes) throws Exception {
        try {
            CommunityBoardDto communityBoardDto = communityBoardService.read(no);
            m.addAttribute("communityBoardDto", communityBoardDto);

            String imagePath = loadImagePath(communityBoardDto.getImg_full_rt());
            m.addAttribute("imagePath", imagePath);


            //하트수
            String totalLikeCount = communityHeartService.countLike(no);
            m.addAttribute("totalLikeCount", totalLikeCount);
            System.out.println(totalLikeCount);

            //댓글수
            int totalCommentCount = communityBoardDto.getComment_count();
            m.addAttribute("totalCommentCount", totalCommentCount);


            return "/CommunityBoard";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "/ErrorPage";
        }


    }





    //세션값 필요
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String update(CommunityBoardDto communityBoardDto, Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        try {
            communityBoardService.modify(communityBoardDto);
            m.addAttribute("communityBoardDto", communityBoardDto);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/community/home" + communityBoardDto.getno();
        }

        return "redirect:/community/read?no=" + communityBoardDto.getno();
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

            } else {
                // 정상적으로 처리되지 않은 경우, 내부 서버 오류 응답
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상태 변경 실패");
            }
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류 발생: " + e.getMessage());
        }

    }

    private String loadImagePath(String imgPath) {
        if (imgPath == null || imgPath.isEmpty()) {
            return "";
        }
        return imgPath;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String Edit(Integer no, Model m, HttpServletRequest request) throws Exception {
        System.out.println(no);
        CommunityBoardDto communityBoardDto = communityBoardService.findCommunityBoardById(no);
        m.addAttribute("communityBoardDto", communityBoardDto);
        return "Board";
    }


    //하트 누를때 상태

    @RequestMapping(value = "/doLike", method = RequestMethod.PATCH)
    public ResponseEntity<Map<String, Object>> doLike(@RequestBody Map<String, Integer> requestBody, HttpSession session) throws Exception {


        String userId = (String) session.getAttribute("ur_id");
        int postNo = requestBody.get("no");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not authenticated"));
        }

        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setUr_id(userId);
        communityHeartDto.setPost_no(postNo);
        communityHeartService.doLike(communityHeartDto);


        int totalLikeCount = communityBoardService.totalLike(postNo);
        Map<String, Object> response = new HashMap<>();
        response.put("totalLikeCount", totalLikeCount);
        System.out.println(response);


        return ResponseEntity.ok(response);


    }


    @Autowired
    CommentService commentService;

    //댓글 등록 메서드
    @PostMapping("/writeComment")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> write(@RequestBody CommentDto commentDto, HttpSession session, HttpServletRequest request) throws Exception {
        //1.세션 객체에서 ur_id 갖고오기
        //2.세션 객체에서 nick 갖고오기

        //String ur_id = (String)session.getAttribute("ur_id")
        //String nick = (String)session.getAttribute("nick")


        String ur_id = session.getAttribute("ur_id").toString();
        String nick = session.getAttribute("nick").toString();



        commentDto.setUr_id(ur_id);
        commentDto.setNick(nick);

        System.out.println(commentDto.toString());
        int maxNo = commentService.findMaxByPostNo(commentDto.getPost_no());

         commentDto.setNo(maxNo + 1);



        System.out.println(commentDto.getUr_id());
        System.out.println(commentDto.getNick());
        System.out.println(commentDto.getNo());

        System.out.println(commentDto.toString());


        try {
            if (commentService.write(commentDto) != 1) {
                throw new Exception("Write comment failed");
            }
            List<CommentDto> comments = commentService.readAll(commentDto.getPost_no());

            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> readComments(@RequestParam int postId) throws Exception {
        try{
            List<CommentDto> comments = commentService.readAll(postId);
            Iterator it = comments.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().toString());
            }
            return ResponseEntity.ok(comments);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}






