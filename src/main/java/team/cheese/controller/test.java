package team.cheese.controller;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.ImgDto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class test {
    @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ImgDto>> uploadImage(@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletRequest request) {

        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/");
        String folderPath = realPath.substring(0, realPath.indexOf("target"))+"src/main/webapp/resources/img";


//        String folderPath = "/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img";

        if(!CheckImg(uploadFiles)){
            System.out.println("너 벤");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);

        String datePath = str.replaceAll("-", "_");

        /* 파일 저장 폴더 이름 (오늘 날짜별로 폴더 생성) */
        File uploadPath = new File(folderPath, datePath);

        /* 오늘 날짜 폴더가 있을시 생성 x 없으면 생성 o */
        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        /* 이미저 정보 담는 객체 */
        List<ImgDto> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFiles) {
            ImgDto imgvo = new ImgDto();

            String uploadFileName = multipartFile.getOriginalFilename();
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
                File thumbnailFile3 = new File(uploadPath, "r_" + uploadFileName); // 미리보기이미지

                // 이미지 크기 지정
                int width = 78;
                int height = 78;

                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
                BufferedImage image3 = Thumbnails.of(multipartFile.getInputStream())
                        .size(width, height)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .asBufferedImage();

                Thumbnails.of(image3)
                        .size(width, height)
                        .outputQuality(1.0)  // 품질 유지
                        .toFile(thumbnailFile3);

            } catch (Exception e) {
                e.printStackTrace();
            }

            list.add(imgvo);

//            System.out.println("파일 이름 : " + multipartFile.getOriginalFilename());
//            System.out.println("파일 타입 : " + multipartFile.getContentType());
//            System.out.println("파일 크기 : " + multipartFile.getSize());
        }
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
}
