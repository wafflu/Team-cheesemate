package team.cheese.entity;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.ImgDto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImgFactory {

    private static String folderPath = System.getProperty("user.home")+File.separator+"Desktop"+File.separator;
    private static String foldername = "ImgRepository";
    private String datePath = "";
    public ImgFactory(){
        checkimgfolder();
        todaystr();
    }

    // File.separator -> /

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
    public List<ImgDto> makeImg(MultipartFile[] uploadFiles, String imgtype, int wsize, int hsize){
        File uploadPath = new File(getFolderPath(), getDatePath());

        List<ImgDto> list = new ArrayList();

        for(MultipartFile multipartFile : uploadFiles) {
            String fileName = multipartFile.getOriginalFilename();
            String ename = fileName.substring(fileName.lastIndexOf('.'));

            File saveFile = new File(getFolderPath()+File.separator+getDatePath(), fileName);
            /* 파일 저장 */
            try {
                File img_name = new File(uploadPath, hash("user123"+fileName)+ename);
                /* 원본 파일 저장 */
                multipartFile.transferTo(saveFile);

                // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
                BufferedImage image = Thumbnails.of(saveFile)
                        .size(wsize, hsize)
                        .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                        .asBufferedImage();

                Thumbnails.of(image)
                        .size(wsize, hsize)
                        .outputQuality(1.0)  // 품질 유지
                        .toFile(img_name);

                //이미지 객체 만들기
                ImgDto img = setImginfo(img_name, fileName, imgtype, wsize, hsize);
                list.add(img);

            } catch (Exception e) {
                System.out.println("fail");
                e.printStackTrace();
            }
        }
        return list;
    }

    /* 등록하기 누를시 파일 제작 */
    public ImgDto makeImg(File file, String imgtype, int gno, int wsize, int hsize){
        long currentTimeMillis = System.currentTimeMillis();
        ImgDto img = null;
        /* 파일 저장 */
        try {
            String fileName = file.getName();
            String ename = fileName.substring(fileName.lastIndexOf('.'));

            String fullrt = folderPath+foldername+File.separator+datePath;
            String imgname = (gno+currentTimeMillis)+ename;

            File img_name = new File(fullrt, imgname);

            //수정때문에 만든부분
            if(imgtype.equals("r")){
                img_name = new File(fullrt, hash("user123"+fileName)+ename);
            }

            // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
            BufferedImage image = Thumbnails.of(file)
                    .size(wsize, hsize)
                    .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
                    .asBufferedImage();

            Thumbnails.of(image)
                    .size(wsize, hsize)
                    .outputQuality(1.0)  // 품질 유지
                    .toFile(img_name);
            img = setImginfo(img_name, fileName, imgtype, wsize, hsize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    //이미지 파일 정보 셋팅
    public ImgDto setImginfo(File imgfile, String orifilename, String imgtype, int wsize, int hsize){
        ImgDto img = new ImgDto();
        String uploadFileName = imgfile.getName();
        String fullrt = datePath+File.separator+uploadFileName;
        img.setImgtype(imgtype);
        img.setFile_rt(datePath);
        img.setU_name(uploadFileName);
        img.setO_name(orifilename.substring(0, orifilename.lastIndexOf(".")));
        img.setE_name(orifilename.substring(orifilename.lastIndexOf("."), orifilename.length()));
        img.setImg_full_rt(fullrt);
        img.setW_size(wsize);
        img.setH_size(hsize);
        return img;
    }


//
//    public void Makeimg(File imageFile, String imgtype, int wsize, int hsize){
//        String datastr = todaystr();
//
//        File uploadPath = new File(folderPath, datastr);
//
//        BCryptPasswordEncoder nameEncoder = new BCryptPasswordEncoder();
//
////        String uploadFileName = imgtype+"_"+datastr+"_"+imageFile.getName();
//        String uploadFileName = nameEncoder.encode(imageFile.getName());
//
//        /* 파일 위치, 파일 이름을 합친 File 객체 */
//        File saveFile = new File(uploadPath, uploadFileName);
//
//        /* 파일 저장 */
//        try {
//            File img_name = new File(uploadPath, uploadFileName);
//
//            // 이미지 비율 유지하며 크기 조정하여 1:1 비율로 만들기
//            BufferedImage image = Thumbnails.of(imageFile)
//                    .size(wsize, hsize)
//                    .crop(Positions.CENTER)  // 이미지 중앙을 기준으로 자르기
//                    .asBufferedImage();
//
//            Thumbnails.of(image)
//                    .size(wsize, hsize)
//                    .outputQuality(1.0)  // 품질 유지
//                    .toFile(img_name);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
}
