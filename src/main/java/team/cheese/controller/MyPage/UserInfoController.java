package team.cheese.controller.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.service.MyPage.UserInfoService;

import javax.servlet.http.HttpSession;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    // 소개글 읽기
    @GetMapping("/userInfo/{ur_id}") // controller -> service
    public ResponseEntity<UserInfoDTO> read(@PathVariable String ur_id,HttpSession session) throws Exception{
        UserInfoDTO userInfoDTO = null;
        // ur_id에 해당하는 소개글 읽어오기
        userInfoDTO = userInfoService.read(ur_id);
        System.out.println(userInfoDTO);
        return new ResponseEntity<UserInfoDTO>(userInfoDTO,HttpStatus.OK);
    }

    // 소개글 쓰기
    @PostMapping("/userInfo")
    public ResponseEntity<String> write(@RequestBody UserInfoDTO userInfoDTO, HttpSession session) throws Exception {
//       String ur_id = (String) session.getAttribute("id");
//       String ur_id = "rudtlr";
//       userInfoDTO.setUr_id(ur_id);
        userInfoService.write(userInfoDTO);
        return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
    }

    // 소개글 수정하기
    @PatchMapping("/userInfo/{ur_id}")
    public ResponseEntity<String> modify(@PathVariable String ur_id,@RequestBody UserInfoDTO userInfoDTO) throws Exception{
        userInfoDTO.setUr_id(ur_id);
        userInfoService.modify(userInfoDTO);
        return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
    }

    // 소개글 좋아요 수 증가
//    @PatchMapping("/userInfo/like/{ur_id}")
//    public ResponseEntity<String> changeLikeCnt(@PathVariable String ur_id) {
//        if(!userInfoService.clickedLike(ur_id))
//            return new ResponseEntity<>("Like MOD_ERR", HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<>("Like MOD_OK", HttpStatus.OK);
//    }

    // 소개글 싫어요 수 증가
//    @PatchMapping("/userInfo/hate/{ur_id}")
//    public ResponseEntity<String> changeHateCnt(@PathVariable String ur_id) {
//        if(!userInfoService.clickedHate(ur_id))
//            return new ResponseEntity<>("Hate MOD_ERR", HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<>("Hate MOD_OK", HttpStatus.OK);
//    }
    
}
