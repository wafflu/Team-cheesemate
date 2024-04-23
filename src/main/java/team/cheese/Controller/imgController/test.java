package team.cheese.Controller.imgController;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.Domain.img.ImgVO;

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
    public ResponseEntity<List<ImgVO>> uploadImage(@RequestParam("uploadFile") MultipartFile[] uploadFiles) {

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
                List<ImgVO> list = null;
                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
            }

        }

        String folderPath = "/Users/jehyeon/Desktop/학원/Spring/sp_legacy/src/main/webapp/WEB-INF/uploads"; // 절대 경로 테스트 삼아 지정

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
        List<ImgVO> list = new ArrayList();

        boolean change = true;

        for(MultipartFile multipartFile : uploadFiles) {
            ImgVO imgvo = new ImgVO();

            String uploadFileName = multipartFile.getOriginalFilename();
//            imgvo.setFilert(folderPath+datePath);
            imgvo.setFilert(datePath);
            String uuid = UUID.randomUUID().toString();
            imgvo.setU_name(uuid);
            imgvo.setO_name(uploadFileName.substring(0, uploadFileName.indexOf(".")));
            imgvo.setE_name(uploadFileName.substring(uploadFileName.indexOf("."), uploadFileName.length()));

//            vo.setFileName(uploadFileName);
//            vo.setUploadPath(datePath);

            /* uuid 적용 파일 이름 */

//            vo.setUuid(uuid);

//            uploadFileName = uuid + "_" + uploadFileName;

            /* 파일 위치, 파일 이름을 합친 File 객체 */
            File saveFile = new File(uploadPath, uploadFileName);

            /* 파일 저장 */
            try {
                multipartFile.transferTo(saveFile); // 필수

//                File thumbnailFile = new File(uploadPath, "s_" + uploadFileName); // 썸네일
//                File thumbnailFile2 = new File(uploadPath, "w_" + uploadFileName); // 본글 이미지
                uploadFileName = uuid + "_" + uploadFileName;
                File thumbnailFile3 = new File(uploadPath, "r_" + uploadFileName); // 미리보기이미지

                // 이미지 크기 지정
                int width = 292;
                int height = 292;

                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
//                if(change){
//                    BufferedImage image = Thumbnails.of(multipartFile.getInputStream())
//                            .size(width, height)
//                            .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
//                            .asBufferedImage();
//
//                    Thumbnails.of(image)
//                            .size(width, height)
//                            .outputQuality(1.0)  // 품질 유지
//                            .toFile(thumbnailFile);
//                    change = false;
//                }
//
//                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
//                BufferedImage image2 = Thumbnails.of(multipartFile.getInputStream())
//                        .size(856, 856)
//                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
//                        .asBufferedImage();
//
//                Thumbnails.of(image2)
//                        .size(856, 856)
//                        .outputQuality(1.0)  // 품질 유지
//                        .toFile(thumbnailFile2);


                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
                BufferedImage image3 = Thumbnails.of(multipartFile.getInputStream())
                        .size(78, 78)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .asBufferedImage();

                Thumbnails.of(image3)
                        .size(78, 78)
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
        ResponseEntity<List<ImgVO>> result = new ResponseEntity<>(list, HttpStatus.OK);
        return result;
//        return "test";
    }

    @RequestMapping("/reg_data")
    public void regdate(){

    }

    // 사용안함
//    @PostMapping("/deleteimg")
//    public void delete(String image) {
//        Path filePath = Paths.get("/Users/jehyeon/Desktop/학원/Spring/sp_legacy/src/main/webapp/WEB-INF/uploads");
//        try {
//            // 파일 삭제
//            Files.delete(filePath);
//        } catch (NoSuchFileException e) {
//            System.out.println("삭제하려는 파일/디렉토리가 없습니다");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @PostMapping("/itemUpload")
//    public String send(@RequestParam(value = "images", required = false) List<MultipartFile> imageList,
//                       HttpServletRequest req) {
//        String webPath = "resources/images/items/";
//        String folderPath = req.getSession().getServletContext().getRealPath(webPath);
//        return "test";
//    }
}
