package team.cheese.Controller.imgController;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import team.cheese.Domain.img.ImgVO;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Controller
public class test2 {

//    String folderPath = "/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img"; // 절대 경로 테스트 삼아 지정

    // 현재 작업 디렉토리를 가져옵니다.
//    String currentDirectory = System.getProperty("user.dir");
//    // 파일을 생성할 상대 경로를 지정합니다.
//    String folderPath = "src/main/resources/img";
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

    @PostMapping("/reg_image")
    public String reg_img(@RequestBody ArrayList<ImgVO> imgvo, HttpServletRequest request){
        String folderPath = path(request);

        boolean change = true;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);

        String datePath = str.replaceAll("-", "_");

        /* 파일 저장 폴더 이름 */
        File uploadPath = new File(folderPath, datePath);

        for (ImgVO img : imgvo) {
            try {
                String fname = folderPath+"/"+img.getFilert()+"/"+img.getO_name()+img.getE_name();

                File imageFile = new File(fname);

                // 예: 이미지 크기 출력
                String uuid = UUID.randomUUID().toString();
                String uploadFileName = uuid+"_"+img.getO_name()+img.getE_name();

                File simg_name = new File(uploadPath, "s_" + uploadFileName); // 미리보기이미지
                File wimg_name = new File(uploadPath, "w_" + uploadFileName); // 미리보기이미지

                //썸네일 이미지 만들기
                if(change){
                    BufferedImage image1 = Thumbnails.of(imageFile)
                            .size(292, 292)
                            .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                            .asBufferedImage();

                    Thumbnails.of(image1)
                            .size(292, 292)
                            .outputQuality(1.0)  // 품질 유지
                            .toFile(simg_name);
                    change = false;
                }

                // 본문이미지
                BufferedImage image2 = Thumbnails.of(imageFile)
                        .size(856, 856)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .asBufferedImage();

                Thumbnails.of(image2)
                        .size(856, 856)
                        .outputQuality(1.0)  // 품질 유지
                        .toFile(wimg_name);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // 여기서 필요한 작업 수행
        }


        return "/test";
    }
}
