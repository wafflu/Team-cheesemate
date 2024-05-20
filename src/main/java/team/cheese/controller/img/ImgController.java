package team.cheese.controller.img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.entity.ImgFactory;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.service.ImgService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/img")
public class ImgController {

    @Autowired
    ImgService imgService;

    @Autowired
    SaleDao saleDao;

    @Autowired
    UserInfoDao userInfoDao;

    ImgFactory ifc = new ImgFactory();

    @RequestMapping ("/test")
    public String ajax(HttpServletRequest request) {
        return "img/test";
    }

    //이미지 업로드 과정
    @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ImgDto>> uploadImage(@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpSession session) {
        String userid = (String) session.getAttribute("userId");
        return imgService.uploadimg(uploadFiles, true, userid);
    }

    @PostMapping(value="/uploadoneimg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ImgDto>> uploadoneimg(@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpSession session) {
        String userid = (String) session.getAttribute("userId");
        return imgService.uploadimg(uploadFiles, false, userid);
    }

    //이미지 보여주기용
    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileName){
        return imgService.display(fileName);
    }


    //위에까지는 공용

}
