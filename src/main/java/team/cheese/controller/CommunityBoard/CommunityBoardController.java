package team.cheese.controller.CommunityBoard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team.cheese.domain.AddrCdDto;
import team.cheese.domain.Comment.CommentDto;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.domain.CommunityHeart.CommunityHeartDto;
import team.cheese.entity.PageHandler;
import team.cheese.service.Comment.CommentService;
import team.cheese.service.CommunityBoard.CommunityBoardService;
import team.cheese.service.CommunityHeart.CommunityHeartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;


@Controller
@RequestMapping(value = "/community")
public  class CommunityBoardController {
    @Autowired
    CommunityBoardService communityBoardService;

    @Autowired
    CommunityHeartService communityHeartService;

    @Autowired
    CommentService commentService;


    //community메인페이지
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String communityBoardHome(Model m) throws Exception {

        List<CommunityBoardDto> list = communityBoardService.readAll();
        m.addAttribute("list", list);

        return "/CommunityHome";

    }





    //community세부 리스트 페이지
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String communityBoardList(CommunityBoardDto communityBoardDto, Model m) throws Exception {

        List<CommunityBoardDto> list = communityBoardService.readAll();
        m.addAttribute("list", list);
        return "/CommunityList";
    }

//    community세부 리스트 페이지ajax
    @RequestMapping(value = "/home/story", method = RequestMethod.GET)
    @ResponseBody
    public List test(Character ur_state) throws Exception {
        List<CommunityBoardDto> list = communityBoardService.readAll();

        return list;
    }


    @GetMapping("/story")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getBoards(@RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "5") int pageSize,
                                                         @RequestParam(defaultValue = "commu_A") String category) throws Exception {
        List<CommunityBoardDto> list = communityBoardService.getPageByCategory(page, pageSize, category);
        int totalCount = communityBoardService.getCountByCategory(category);
        PageHandler ph = new PageHandler(totalCount, page, pageSize);
        System.out.println("Page: " + page + ", PageSize: " + pageSize + ", Category: " + category);
        System.out.println(ph);
        System.out.println(list);
        Map<String, Object> response = new HashMap<>();
        response.put("content", list);
        response.put("ph", ph);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //글쓰기 페이지로 이동
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String communityBoard() throws Exception {
        return "/CommunityWriteBoard";
    }


    //세션값 필요
    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> map,
                                           Model m,
                                           HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String userNick = (String) session.getAttribute("userNick");



        List<AddrCdDto> addrCdList = (List<AddrCdDto>) session.getAttribute("userAddrCdDtoList");
        System.out.println(addrCdList);




        if (userId == null || userNick == null) {
            return new ResponseEntity<>("User not logged in or session expired", HttpStatus.UNAUTHORIZED);
        }

        AddrCdDto selectedAddr = null;
        for (AddrCdDto addrCdDto : addrCdList) {
            if (addrCdDto.getUr_id().equals(userId)) {
                selectedAddr = addrCdDto;
                break;
            }
        }

        if (selectedAddr == null) {
            return new ResponseEntity<>("Address not found for user", HttpStatus.BAD_REQUEST);
        }

        // ObjectMapper : JSON 형태를 JAVA 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        CommunityBoardDto communityBoardDto = objectMapper.convertValue(map.get("communityBoardDto"), CommunityBoardDto.class);

        // 유효성 검사를 위한 값 설정
        communityBoardDto.setur_id(userId);
        communityBoardDto.setNick(userNick);
        communityBoardDto.setaddr_cd(selectedAddr.getAddr_cd());
        communityBoardDto.setaddr_no((int) selectedAddr.getNo());
        communityBoardDto.setaddr_name(selectedAddr.getAddr_name());

        System.out.println(communityBoardDto.toString());
        // 유효성 검사 수행
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CommunityBoardDto>> violations = validator.validate(communityBoardDto);


        // 유효성 검사 결과 확인
        if (!violations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
            for (ConstraintViolation<CommunityBoardDto> violation : violations) {
                errorMessages.append(violation.getMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }

        System.out.println(communityBoardDto.toString());
        try {
            communityBoardService.write(communityBoardDto);
            return new ResponseEntity<>("/community/list", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while writing the post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(Integer no, Model m, RedirectAttributes redirectAttributes) throws Exception {
        try {
            CommunityBoardDto communityBoardDto = communityBoardService.read(no);
            m.addAttribute("communityBoardDto", communityBoardDto);

            //이미지 지움
//            String imagePath = loadImagePath(communityBoardDto.getImg_full_rt());
//            m.addAttribute("imagePath", imagePath);


            //하트수
            String totalLikeCount = communityHeartService.countLike(no);
            m.addAttribute("totalLikeCount", totalLikeCount);


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
    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<String> update(@RequestBody Map<String,Object>map, Model m, HttpServletRequest request) throws Exception {




        //Interceptor 사전에 들림
        // ObjectMapper : JSON 형태를 JAVA 객체로 변환

        ObjectMapper objectMapper = new ObjectMapper();
        CommunityBoardDto communityBoardDto = objectMapper.convertValue(map.get("communityBoardDto"), CommunityBoardDto.class);

        //    유효성 검사 수행
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CommunityBoardDto>> violations = validator.validate(communityBoardDto);

         // 유효성 검사 결과 확인
        if (!violations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            communityBoardService.modify(communityBoardDto);
            return new ResponseEntity<>("/community/list",HttpStatus.OK);

        }catch (Exception e) {
           return new ResponseEntity<>("죄송합니다.글 수정에 실패했습니다.",HttpStatus.BAD_REQUEST);
        }

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

//    private String loadImagePath(String imgPath) {
//        if (imgPath == null || imgPath.isEmpty()) {
//            return "";
//        }
//        return imgPath;
//    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String Edit(Integer no, Model m, HttpServletRequest request, RedirectAttributes redirectAttributes)throws Exception {

        CommunityBoardDto communityBoardDto = communityBoardService.findCommunityBoardById(no);

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String userNick = (String) session.getAttribute("userNick");


        if (userId == null || userNick == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 정보가 없습니다. 로그인 해주세요.");
            return "redirect:/loginForm";
        }

        if(!Objects.equals(userId, communityBoardDto.getur_id())) {
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 정보가 일치하지 않습니다.");
            return "redirect:/community/read";
        }else {
            m.addAttribute("communityBoardDto", communityBoardDto);
        }


        return "CommunityWriteBoard";
    }


    //하트 누를때 상태

    @RequestMapping(value = "/doLike", method = RequestMethod.PATCH)
    public ResponseEntity<Map<String, Object>> doLike(@RequestBody Map<String, Integer> requestBody,HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        int postNo = requestBody.get("no");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "로그인 먼저 해주세요"));
        }

        try{
        CommunityHeartDto communityHeartDto = new CommunityHeartDto();
        communityHeartDto.setUr_id(userId);
        communityHeartDto.setPost_no(postNo);
        communityHeartService.doLike(communityHeartDto);


        int totalLikeCount = communityBoardService.totalLike(postNo);
        Map<String, Object> response = new HashMap<>();
        response.put("totalLikeCount", totalLikeCount);
        System.out.println(response);


        return ResponseEntity.ok(response);


    }catch (Exception e) {
        e.printStackTrace();
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "서버 에러가 발생했습니다."));
        }
    }



    //댓글쓰기
    @PostMapping("/writeComment")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> write(@RequestBody CommentDto commentDto,HttpServletRequest request) throws Exception {
        try {
            // 세션에서 ur_id와 nick 가져오기, 기본값 설정
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            String userNick = (String) session.getAttribute("userNick");
            // DTO에 세션에서 가져온 데이터 설정
            commentDto.setUr_id(userId);
            commentDto.setNick(userNick);

            // 최대 번호 찾기 및 예외 처리
            Integer maxNo = commentService.findMaxByPostNo(commentDto.getPost_no());
            commentDto.setNo(maxNo + 1);


            //    유효성 검사 수행
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto);

            // 댓글 작성
            commentService.write(commentDto);

            // 댓글 목록 읽기
            List<CommentDto> comments = commentService.readAll(commentDto.getPost_no());
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            // 로깅 및 에러 응답 처리

            e.printStackTrace();

        }
        return null;
    }

    //댓글 읽어오기
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> readComments(@RequestParam int postId) throws Exception {
        try{
            List<CommentDto> comments = commentService.readAll(postId);
            return ResponseEntity.ok(comments);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}






