package team.cheese.controller;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.service.ImgService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class test2 {

    @Autowired
    ImgService imgService;

    @Autowired
    SaleDao saleDao;

    @Autowired
    ImgDao imgDao;

//    String folderPath = "/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img"; // 절대 경로 테스트 삼아 지정

    public String path(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/");
        String folderPath = realPath.substring(0, realPath.indexOf("target"))+"src/main/webapp/resources/img";
        return folderPath;
    }

    @GetMapping("/test")
    public String ajax() {
        return "img/test";
    }

    //이미지 보여주기용
    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileName, HttpServletRequest request){

        String folderPath = path(request);
        File file = new File(folderPath+"/"+ fileName);
        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /* 이미지 파일 삭제 */
    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request){
        String folderPath = path(request);

//        System.out.println("delete : "+fileName);
        File file = null;
        try {
            file = new File(folderPath+"/" + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();
            /* 썸네일 파일 삭제 */
            String originFileName = file.getAbsolutePath().replace("s_", "");
            file = new File(originFileName);
            file.delete();
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);

        }
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @GetMapping("/testview")
    public String viewtest(Model model){
        List<ImgDto> list = imgDao.select_s_imglist();
        System.out.println(list);
        model.addAttribute("list", list);

        return "img/testview";
    }

    @PostMapping("/reg_image")
    public String reg_img(@RequestBody ArrayList<ImgDto> imglist, HttpServletRequest request){
        String folderPath = path(request);

        boolean change = true;

        String datePath = Todaystr();

        /* 파일 저장 폴더 이름 */
        File uploadPath = new File(folderPath, datePath);

        //게시글 임의로 넣어둠 테스트를 위해서
        SaleDto saledto = new SaleDto();
        saledto.setSeller_id("user123");
        saledto.setSal_i_cd("016001005");
        saledto.setPro_s_cd("C");
        saledto.setTx_s_cd("S");
        // 거래방법 1개만 작성
        saledto.setTrade_s_cd_1("F");
        saledto.setTrade_s_cd_2("F");
        saledto.setPrice(28000);
        saledto.setSal_s_cd("S");
        saledto.setTitle("자바의 정석");
        saledto.setContents("자바의 정석 2판 팔아요.");
        saledto.setBid_cd("N");
        saledto.setPickup_addr_cd("11060710");
        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saledto.setBrand("자바의 정석");
        saledto.setReg_price(30000);
        saledto.setCheck_addr_cd(0);
        saleDao.insert_sale(saledto);

        for (ImgDto img : imglist) {
            try {
                String fname = folderPath+"/"+img.getFilert()+"/"+img.getO_name()+img.getE_name();

                File imageFile = new File(fname);

                // 예: 이미지 크기 출력
                String uuid = datePath;//UUID.randomUUID().toString();
                String uploadFileName = uuid+"_"+img.getO_name()+img.getE_name();

                File simg_name = new File(uploadPath, "s_" + uploadFileName); // 미리보기이미지
                File wimg_name = new File(uploadPath, "w_" + uploadFileName); // 미리보기이미지

                int s_wid = 292;
                int s_hig = 292;

                int w_wid = 856;
                int w_hig = 856;

                //썸네일 이미지 만들기
                ImgDto imginfo = new ImgDto();
                imginfo.setTb_no(saledto.getNo());

                if(change){
                    BufferedImage image1 = Thumbnails.of(imageFile)
                            .size(s_wid, s_hig)
                            .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                            .asBufferedImage();

                    Thumbnails.of(image1)
                            .size(s_wid, s_hig)
                            .outputQuality(1.0)  // 품질 유지
                            .toFile(simg_name);
                    imginfo.setTb_name("sale");
                    imginfo.setImgtype("s");
                    imginfo.setFilert(datePath);
                    imginfo.setU_name("s_"+uuid+"_");
                    imginfo.setO_name(img.getO_name());
                    imginfo.setE_name(img.getE_name());
                    imginfo.setW_size(s_wid);
                    imginfo.setH_size(s_hig);
                    change = false;
                    //썸네일 이미지 디비에 저장
                    imgService.reg_img(imginfo);
                }

                //본문 이미지 작성
                imginfo.setTb_name("sale");
                imginfo.setImgtype("w");
                imginfo.setFilert(datePath);
                imginfo.setU_name("w_"+uuid+"_");
                imginfo.setO_name(img.getO_name());
                imginfo.setE_name(img.getE_name());
                imginfo.setW_size(w_wid);
                imginfo.setH_size(w_hig);
                imgService.reg_img(imginfo);
                System.out.println("횟수 체크용3");

                // 본문이미지
                BufferedImage image2 = Thumbnails.of(imageFile)
                        .size(w_wid, w_hig)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .asBufferedImage();

                Thumbnails.of(image2)
                        .size(w_wid, w_hig)
                        .outputQuality(1.0)  // 품질 유지
                        .toFile(wimg_name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //교차테이블 작성
        HashMap map = new HashMap();
        map.put("no", saledto.getNo());
        map.put("col_name", "sal_no");
        map.put("no_name", "no");
        map.put("cross_tb_name", "sale");
        map.put("tb_name", "sale_img");
        imgService.view_img(map);

        return "redirect:/testview";
    }

    //----------------test--------------

    @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ImgDto>> uploadImage(@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletRequest request) {
        String folderPath = path(request);

//        String folderPath = "/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img";

        if(!CheckImg(uploadFiles)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        String datePath = Todaystr();

        /* 파일 저장 폴더 이름 (오늘 날짜별로 폴더 생성) */
        File uploadPath = new File(folderPath, datePath);

        /* 오늘 날짜 폴더가 있을시 생성 x 없으면 생성 o */
        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        List<ImgDto> list = Makepreviewimg(uploadFiles, datePath, uploadPath);

        ResponseEntity<List<ImgDto>> result = new ResponseEntity<>(list, HttpStatus.OK);
        return result;
    }

    private boolean CheckImg(MultipartFile[] uploadFiles){
        /* 이미지 파일 체크 */
        for(MultipartFile multipartFile: uploadFiles) {

            File checkfile = new File(multipartFile.getOriginalFilename());
            String type = null;

            try {
                type = Files.probeContentType(checkfile.toPath());
//                System.out.println("MIME TYPE : " + type);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!type.startsWith("image")) {
//                List<ImgDto> list = null;
                return false;
            }
        }
        return true;
    }

    public List<ImgDto> Makepreviewimg(MultipartFile[] uploadFiles, String datePath, File uploadPath){
        /* 이미저 정보 담는 객체 */
        List<ImgDto> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFiles) {
            ImgDto imgvo = new ImgDto();

            String uploadFileName = multipartFile.getOriginalFilename();
            // 파일경로 저장
            imgvo.setFilert(datePath);
            //uuid이름 생성
            String uuid = datePath;//UUID.randomUUID().toString();
            imgvo.setU_name(uuid);
            imgvo.setO_name(uploadFileName.substring(0, uploadFileName.indexOf(".")));
            imgvo.setE_name(uploadFileName.substring(uploadFileName.indexOf("."), uploadFileName.length()));

            /* 파일 위치, 파일 이름을 합친 File 객체 */
            File saveFile = new File(uploadPath, uploadFileName);

            /* 파일 저장 */
            try {
                multipartFile.transferTo(saveFile); // 원본이미지 저장

                uploadFileName = uuid + "_" + uploadFileName;
                File thumbnailFile = new File(uploadPath, "r_" + uploadFileName); // 미리보기이미지

                //미리보기 이미지 크기 지정
                int width = 78;
                int height = 78;

                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
                BufferedImage Previewimage = Thumbnails.of(multipartFile.getInputStream())
                        .size(width, height)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .asBufferedImage();

                Thumbnails.of(Previewimage)
                        .size(width, height)
                        .outputQuality(1.0)  // 품질 유지
                        .toFile(thumbnailFile);

            } catch (Exception e) {
                e.printStackTrace();
            }

            list.add(imgvo);
//            System.out.println("파일 이름 : " + multipartFile.getOriginalFilename());
//            System.out.println("파일 타입 : " + multipartFile.getContentType());
//            System.out.println("파일 크기 : " + multipartFile.getSize());
        }

        return list;
    }

    public String Todaystr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replaceAll("-", "_");
    }
}
