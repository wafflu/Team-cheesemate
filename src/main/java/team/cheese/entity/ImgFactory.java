package team.cheese.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.ImgDto;

import javax.imageio.ImageIO;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ImgFactory {

    private static String folderPath = System.getProperty("user.home")+"/"+"Desktop"+"/";
    private static String foldername = "ImgRepository";
    private String datePath = "";

    private static final ObjectMapper objectMapper = new ObjectMapper();


    private String userid = "";
    public ImgFactory(){
        checkimgfolder();
        todaystr();
    }

    //로그인 할때 유저 아이디 가져오기용도
    public void setUserid(String userid){
        this.userid = userid;
    }

    // "/" -> /

    //바탕화면에 이미지 폴더 없을시 이미지저장소 폴더 생성
    public void checkimgfolder(){
        File imgfolder = new File(folderPath, foldername);

        if(!imgfolder.exists()){
            imgfolder.mkdirs();
        }
    }

    public void todaystr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        datePath = str.replaceAll("-", "_");

        /* 파일 저장 폴더 이름 (오늘 날짜별로 폴더 생성) */
        File uploadPath = new File(folderPath+foldername, datePath);

        /* 오늘 날짜 폴더가 있을시 생성 x 없으면 생성 o */
        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
    }

    // 이미지 저장소 경로
    public String getFolderPath(){
        return folderPath+foldername;
    }

    // 현재 날짜
    public String getDatePath(){
        return datePath;
    }

    /* 파일등록으로 파일 만들때 */
    public List<ImgDto> makeImg(MultipartFile[] uploadFiles, String imgtype, boolean ckeck, String userid){
        File uploadPath = new File(getFolderPath(), getDatePath());

        List<ImgDto> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFiles) {
            String fileName = multipartFile.getOriginalFilename();

            File saveFile = new File(getFolderPath()+"/"+getDatePath(), fileName);
            ImgDto img = null;
            /* 파일 저장 */
            try {
                /* 원본 파일 저장 */
                multipartFile.transferTo(saveFile);
                if(!ckeck) {
                    BufferedImage bi = ImageIO.read( saveFile );
                    int width = (int) bi.getWidth();
                    int height = (int) bi.getHeight();

                    img = setImginfo(saveFile, fileName, "original", width, height, userid);
                    list.add(img);
                    return list;
                }


                // 판매, 커뮤니티만 아래 만들기 허용
                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
                BufferedImage image = Thumbnails.of(saveFile)
                        .size(78, 78)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .imageType(BufferedImage.TYPE_INT_RGB)
                        .asBufferedImage();

                long currentTimeMillis = System.currentTimeMillis();
                //이미지 jpg로 변환
                File img_name = new File(uploadPath, hash(currentTimeMillis + fileName) + ".jpg");
                ImageIO.write(image, "jpg", img_name);

                //이미지 객체 만들기
                img = setImginfo(img_name, fileName, imgtype, 78, 78, userid);
                list.add(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    /* 등록하기 누를시 파일 제작 */
    public ImgDto makeImg(File file, String imgtype, int gno, int wsize, int hsize, String userid){
        long currentTimeMillis = System.currentTimeMillis();
        ImgDto img = null;

        /* 파일 저장 */
        try {
            String fileName = file.getName();

            String fullrt = folderPath+foldername+"/"+datePath;
            String imgname = (gno+currentTimeMillis)+ ".jpg";

            File img_name = new File(fullrt, imgname);

            //수정때문에 만든부분
            if(imgtype.equals("r")){
                img_name = new File(fullrt, hash(currentTimeMillis + fileName) + ".jpg");
            }

            // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
            BufferedImage image = Thumbnails.of(file)
                    .size(wsize, hsize)
                    .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                    .asBufferedImage();

            // RGB 형식으로 이미지 변환
            BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            rgbImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);

            ImageIO.write(rgbImage, "jpg", img_name);

            img = setImginfo(img_name, fileName, imgtype, wsize, hsize, userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    //이미지 파일 정보 셋팅
    public ImgDto setImginfo(File imgfile, String orifilename, String imgtype, int wsize, int hsize, String userid){
        ImgDto img = new ImgDto();
        String uploadFileName = imgfile.getName();
        String fullrt = datePath+"/"+uploadFileName;
        img.setImgtype(imgtype);
        img.setFile_rt(datePath);
        img.setU_name(uploadFileName);
        img.setO_name(orifilename.substring(0, orifilename.lastIndexOf(".")));
        img.setE_name(orifilename.substring(orifilename.lastIndexOf("."), orifilename.length()));
        img.setImg_full_rt(fullrt);
        img.setW_size(wsize);
        img.setH_size(hsize);
        img.setFirst_id(userid);
        img.setLast_id(userid);
        return img;
    }


    // 이미지 파일체크
    public boolean CheckImg(MultipartFile[] uploadFiles){
        /* 이미지 파일 체크 */
        for(MultipartFile multipartFile: uploadFiles) {

            File checkfile = new File(multipartFile.getOriginalFilename());
            String type = null;

            try {
                type = Files.probeContentType(checkfile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!type.startsWith("image")) {
                return false;
            }
        }
        return true;
    }

    //해시
    public String hash(String filename) throws NoSuchAlgorithmException {
        // SHA-256 해시 알고리즘 인스턴스 생성
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // 입력된 비밀번호를 바이트 배열로 변환하여 해싱
        byte[] encodedHash = digest.digest(filename.getBytes());

        // 해시값을 16진수 문자열로 변환
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public ArrayList<ImgDto> checkimgfile(Map map){
        ArrayList<ImgDto> imgList;
        try {
            imgList = objectMapper.convertValue(map.get("imgList"), new TypeReference<ArrayList<ImgDto>>() {});
        } catch (Exception e) {
            return null;
        }

        // Null 및 빈 리스트 검사
        if (imgList == null || imgList.isEmpty()) {
            return null;
        }

        // Validator 설정
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // 개별 객체 유효성 검사
        for (ImgDto img : imgList) {
            if (img == null) {
                //ImgDto object is null
                return null;
            }

            // Bean Validation 검사
            Set<ConstraintViolation<ImgDto>> violations = validator.validate(img);
            if (!violations.isEmpty()) {
                return null;
            }

            // 추가 필드 유효성 검사
            if (img.getO_name().equals("") || img.getE_name().equals("")) {
                return null;
            }
        }
        return imgList;
    }
}