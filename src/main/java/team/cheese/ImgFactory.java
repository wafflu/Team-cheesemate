package team.cheese;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.ImgDto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgFactory {

    static String folderPath = "";
    public ImgFactory(){}
    public ImgFactory(String folderPath){
        this.folderPath = folderPath;
    }

    static public String todaystr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        String datePath = str.replaceAll("-", "_");
        return datePath;
    }

    public String getFolderPath(){
        return folderPath;
    }

    public void Makefolder(String datePath){
        /* 파일 저장 폴더 이름 (오늘 날짜별로 폴더 생성) */
        File uploadPath = new File(folderPath, datePath);

        /* 오늘 날짜 폴더가 있을시 생성 x 없으면 생성 o */
        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
    }

    //이미지 파일 정보 셋팅
    public ImgDto setImginfo(MultipartFile multipartFile, String imgtype, int wsize, int hsize){
        //파일 이름 얻기
        String uploadFileName = multipartFile.getOriginalFilename();
        ImgDto img = new ImgDto();
        String datastr = todaystr();
        String fullrt = datastr+"/"+imgtype+"_"+datastr+"_"+uploadFileName;
        //2024_05_01/r_2024_05_01_1번_아보카도.png

        img.setImgtype(imgtype);
        img.setFile_rt(todaystr());
        img.setU_name(datastr);
        img.setO_name(uploadFileName.substring(0, uploadFileName.indexOf(".")));
        img.setE_name(uploadFileName.substring(uploadFileName.indexOf("."), uploadFileName.length()));
        img.setImg_full_rt(fullrt);
        img.setW_size(wsize);
        img.setH_size(hsize);
        return img;
    }

    public ImgDto setImginfo(File imgfile, String imgtype, int wsize, int hsize){
        //파일 이름 얻기
        String uploadFileName = imgfile.getName();
        ImgDto img = new ImgDto();
        String datastr = todaystr();
        String uploadimgname = imgtype+"_"+datastr+"_";
        String fullrt = datastr+"/"+uploadimgname+uploadFileName;
        //2024_05_01/r_2024_05_01_1번_아보카도.png

        img.setImgtype(imgtype);
        img.setFile_rt(todaystr());
        img.setU_name(uploadimgname);
        img.setO_name(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
        img.setE_name(uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length()));
        img.setImg_full_rt(fullrt);
        img.setW_size(wsize);
        img.setH_size(hsize);
        return img;
    }

    //이미지 파일 제작
    public void Makeimg(MultipartFile multipartFile, String imgtype, int wsize, int hsize){
        String uploadFileName = multipartFile.getOriginalFilename();
        String datastr = todaystr();

        File uploadPath = new File(folderPath, datastr);

        /* 파일 위치, 파일 이름을 합친 File 객체 */
        File saveFile = new File(uploadPath, uploadFileName);

        /* 파일 저장 */
        try {
            multipartFile.transferTo(saveFile); // 원본이미지 저장

            uploadFileName = datastr + "_" + uploadFileName;

            File thumbnailFile = new File(uploadPath, imgtype + uploadFileName); // 미리보기이미지

            // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
            BufferedImage Previewimage = Thumbnails.of(multipartFile.getInputStream())
                    .size(wsize, hsize)
                    .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                    .asBufferedImage();

            Thumbnails.of(Previewimage)
                    .size(wsize, hsize)
                    .outputQuality(1.0)  // 품질 유지
                    .toFile(thumbnailFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Makeimg(File imageFile, String imgtype, int wsize, int hsize){
        String datastr = todaystr();

        File uploadPath = new File(folderPath, datastr);

        BCryptPasswordEncoder nameEncoder = new BCryptPasswordEncoder();

//        String uploadFileName = imgtype+"_"+datastr+"_"+imageFile.getName();
        String uploadFileName = nameEncoder.encode(imageFile.getName());

        /* 파일 위치, 파일 이름을 합친 File 객체 */
        File saveFile = new File(uploadPath, uploadFileName);

        /* 파일 저장 */
        try {
            File img_name = new File(uploadPath, uploadFileName);

            // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
            BufferedImage image = Thumbnails.of(imageFile)
                    .size(wsize, hsize)
                    .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                    .asBufferedImage();

            Thumbnails.of(image)
                    .size(wsize, hsize)
                    .outputQuality(1.0)  // 품질 유지
                    .toFile(img_name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 이미지 파일체크
    public boolean CheckImg(MultipartFile[] uploadFiles){
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
                return false;
            }
        }
        return true;
    }
}
