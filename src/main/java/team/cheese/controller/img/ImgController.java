package team.cheese.controller.img;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.entity.ImgFactory;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.service.ImgService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public ResponseEntity<List<ImgDto>> uploadImage(@RequestParam("uploadFile") MultipartFile[] uploadFiles) {
        return imgService.uploadimg(uploadFiles, true);
    }

    @PostMapping(value="/uploadoneimg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ImgDto>> uploadoneimg(@RequestParam("uploadFile") MultipartFile[] uploadFiles) {
        return imgService.uploadimg(uploadFiles, false);
    }

    //이미지 보여주기용
    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileName){
        return imgService.display(fileName);
    }

    @RequestMapping(value = "/saveporfile", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> saveporfile(@RequestBody ArrayList<ImgDto> imgList) throws Exception {
        System.out.println("asdas");
        ImgDto profile = imgList.get(0);
        String imgname = profile.getO_name()+profile.getE_name();
        ImgDto profileimg = imgService.reg_img_one(imgname);
        HashMap map = new HashMap<>();
        map.put("img_full_rt", profileimg.getImg_full_rt());
        map.put("group_no", profileimg.getGroup_no());
        map.put("ur_id", "1");
        userInfoDao.updateProfile(map);
        return new ResponseEntity<String>("/myPage/main", HttpStatus.OK);
    }

    //위에까지는 공용

    //이거 체크
    @RequestMapping(value = "/reg_image2", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> reg_img(@RequestBody Map<String, Object> map) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SaleDto sdto = objectMapper.convertValue(map.get("sale"), SaleDto.class);
        ArrayList<ImgDto> imgList = objectMapper.convertValue(map.get("img"), new TypeReference<ArrayList<ImgDto>>() {});

        int gno = imgService.getGno()+1;
        String full_file_rt = imgService.reg_img(imgList, gno, true);
        saleDao.insert_sale(makesale(sdto.getTitle(), sdto.getContents(), gno, full_file_rt));
        return new ResponseEntity<String>("/img/testview", HttpStatus.OK);
    }

    @RequestMapping(value = "/reg_image3", produces = "application/json; charset=UTF-8")
    public String reg_img3(SaleDto sdto, ArrayList<ImgDto> imglist) throws Exception {
        System.out.println("sdto : "+sdto);
        System.out.println("imglist : "+imglist);
        return "img/test";
    }

    // 이 아래로 다 테스트코드
    @RequestMapping("/testview")
    public String viewtest(Model model){
        List<SaleDto> list = saleDao.select_all();
        model.addAttribute("list", list);

        return "img/testview";
    }
    @RequestMapping("/testdetail")
    public String testdetail(int no, Model model){
        SaleDto sdto = saleDao.select(no);
        List<ImgDto> list = imgService.read(sdto.getGroup_no());

        model.addAttribute("list", list);
        model.addAttribute("sale", sdto);
        return "img/testdetail";
    }
    @RequestMapping("/modifyview")
    public String modifyview(int no, Model model){
        SaleDto sdto = saleDao.select(no);
        List<ImgDto> list = imgService.read(sdto.getGroup_no());

        model.addAttribute("list", list);
        model.addAttribute("sale", sdto);
        return "img/modifyimg";
    }

    @RequestMapping(value = "/modify_image", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> modify_image(@RequestBody Map<String, Object> map) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SaleDto sdto = objectMapper.convertValue(map.get("sale"), SaleDto.class);
        ArrayList<ImgDto> imgList = objectMapper.convertValue(map.get("img"), new TypeReference<ArrayList<ImgDto>>() {});

        int gno = imgService.getGno()+1;

        String full_file_rt = imgService.modify_img(imgList, gno, sdto.getGroup_no());
        System.out.println(full_file_rt);
//        saleDao.insert_sale(makesale(sdto.getTitle(), sdto.getContents(), gno, full_file_rt));
        return new ResponseEntity<String>("/img/testview", HttpStatus.OK);
    }


    public SaleDto makesale(String title, String con, int gno, String file_rt){
        SaleDto saledto = new SaleDto();
        saledto.setAddr_cd("11010720");
        saledto.setAddr_name("서울특별시 종로구 사직동");
        saledto.setSeller_id("user123");
        saledto.setSeller_nick("minyoung");
        saledto.setSal_i_cd("001002002");
        saledto.setSal_name("후드티/후드집업");
        saledto.setGroup_no(gno);
        saledto.setImg_full_rt(file_rt);
        saledto.setPro_s_cd("C");
        saledto.setTx_s_cd("S");
        saledto.setTrade_s_cd_1("F");
        saledto.setTrade_s_cd_2("F");
        saledto.setSal_s_cd("S");
        saledto.setTitle(title);
        saledto.setContents(con);
        saledto.setPrice(35000);
        saledto.setBid_cd("N");
        saledto.setPickup_addr_cd("11060710");
        saledto.setPickup_addr_name("서울특별시 종로구 청운효자동");
        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saledto.setBrand("북북");
        saledto.setReg_price(100);
        saledto.setBuyer_id("");
        saledto.setBuyer_nick("");
        saledto.setLike_cnt(0);
        saledto.setView_cnt(0);
        saledto.setHoist_cnt(0);
        saledto.setBid_cnt(0);
        saledto.setUr_state('Y');
        saledto.setAd_state('N');
        saledto.setFirst_id("user123");
        saledto.setLast_id("user123");
        return saledto;
    }

}
