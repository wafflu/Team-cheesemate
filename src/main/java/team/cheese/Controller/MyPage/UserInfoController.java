package team.cheese.Controller.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.cheese.Domain.MyPage.UserInfoDTO;
import team.cheese.Service.MyPage.UserInfoService;

import javax.servlet.http.HttpSession;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/userInfo/{ur_id}")
    public ResponseEntity<UserInfoDTO> read(@PathVariable String ur_id) {
        UserInfoDTO userInfoDTO = null;
        try {
            if(userInfoService.read(ur_id)==null) {
                userInfoDTO = new UserInfoDTO(ur_id,"");
                int rowCnt = userInfoService.write(userInfoDTO);
                System.out.println(rowCnt);
                if(rowCnt!=1) {
                    throw new Exception("Read ERR");
                }
            }
            userInfoDTO = userInfoService.read(ur_id);
            System.out.println(ur_id);
            System.out.println(userInfoDTO);
            return new ResponseEntity<UserInfoDTO>(userInfoDTO,HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserInfoDTO>(userInfoDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/userInfo")
    public ResponseEntity<String> write(@RequestBody UserInfoDTO userInfoDTO, HttpSession session) {
//        String ur_id = (String) session.getAttribute("id");
//        String ur_id = "rudtlr";
//        userInfoDTO.setUr_id(ur_id);
        try {
            int rowCnt =  userInfoService.write(userInfoDTO);
            if (rowCnt != 1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRT_OK", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/userInfo/{ur_id}")
    public ResponseEntity<String> modify(@PathVariable String ur_id,@RequestBody UserInfoDTO userInfoDTO) {
        userInfoDTO.setUr_id(ur_id);
        try {
            int rowCnt = userInfoService.modify(userInfoDTO);

            if (rowCnt != 1)
                throw new Exception("Modify failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/userInfo/like/{ur_id}")
    public ResponseEntity<String> changeLikeCnt(@PathVariable String ur_id) {
        try {
            int rowCnt = userInfoService.ClickedLike(ur_id);

            if (rowCnt != 1)
                throw new Exception("Like Modify failed.");

            return new ResponseEntity<>("Like MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Like MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/userInfo/hate/{ur_id}")
    public ResponseEntity<String> changeHateCnt(@PathVariable String ur_id) {
        try {
            int rowCnt = userInfoService.ClickedHate(ur_id);

            if (rowCnt != 1)
                throw new Exception("Hate Modify failed.");

            return new ResponseEntity<>("Hate MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Hate MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
    
}
