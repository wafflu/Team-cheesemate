package team.cheese.controller.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.cheese.domain.ImgDto;
import team.cheese.domain.MyPage.UserInfoDTO;
import team.cheese.service.MyPage.UserInfoService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    // 소개글 읽기
    @GetMapping("/userInfo/{ur_id}") // controller -> service
    public ResponseEntity<UserInfoDTO> read(@PathVariable String ur_id,HttpSession session) throws Exception{
        // ur_id에 해당하는 소개글 읽어오기
        UserInfoDTO userInfoDTO = userInfoService.read(ur_id);
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

    //프로필 이미지 저장
    @RequestMapping(value = "/saveporfile", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> saveporfile(@RequestBody ArrayList<ImgDto> imgList, HttpSession session) throws Exception {
        String ur_id = (String) session.getAttribute("userId");
        ImgDto profile = imgList.get(0);

        if(profile == null){
            return new ResponseEntity<String>("이미지 등록 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String imgname = profile.getO_name()+profile.getE_name();

        return userInfoService.profileimgchange(imgname, ur_id);
    }

}
