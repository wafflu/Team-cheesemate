import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.ImgFactory;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.service.ImgService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@Transactional

public class ImgServiceTest {
    @Autowired
    ImgService imgService;

    @Autowired
    SaleDao saleDao;

    ImgFactory ifc;
    String datePath = "";
    // 테스트용 패스 찾기용
    public void pathtest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        ifc = imgService.path(request);
        datePath = ifc.todaystr();
    }

    @Test
    public void delete(){
        if(imgService.count("img_group") != 0){
            imgService.delete("img_group");
            System.out.println("이미지 그룹 삭제완료");
        }

        if(imgService.count("img") != 0){
            imgService.delete("img");
            System.out.println("이미지 삭제완료");
        }
    }

    //롤백안하고 데이터 입력
    @Test
    @Rollback(false)
    public void nr_reg_img() throws Exception {
        ImgDto img = makeImg(100, "w");
        int gno = imgService.getGno()+1;
        imgService.reg_img(gno, img);
    }

    //등록 관련
    //mock을 사용하여 실제 데이터 입력없이 메서드가 실행되는지 확인
    @Test
    public void r_reg_img() throws Exception {
        ArrayList<ImgDto> imglist = new ArrayList();

        ImgService imgServiceMock = mock(ImgService.class);
        for(int i = 0; i<10; i++){
            ImgDto simg = makeImg(i, "s");
            int gno = imgServiceMock.getGno()+1;

            imgServiceMock.reg_img(gno, simg);
            imglist.add(simg);
            verify(imgServiceMock, times(1)).reg_img(gno, simg);
        }

        ArrayList<ImgDto> mocklist = imgServiceMock.readall();

        Iterator it = mocklist.iterator();

        int i = 0;
        while (it.hasNext()){
            assertEquals(it.next(), imglist.get(i));
            i++;
        }
    }

    //디비에 넣고 롤백시켜서 데이터 확인
    // Insert부문 체크할 필요가 없음 문제발생시 예외 발생하여 롤백
    // select로 값이 제대로 들어갔는지 비교확인 필요
    @Test
    @Rollback(false)
    public void r_reg_img2() throws Exception {
        if(imgService.count("sale") != 0){
            imgService.delete("sale");
            System.out.println("sale 삭제완료");
        }

        if(imgService.count("img_group") != 0){
            imgService.delete("img_group");
            System.out.println("이미지 그룹 삭제완료");
        }

        if(imgService.count("img") != 0){
            imgService.delete("img");
            System.out.println("이미지 삭제완료");
        }

        ArrayList<ImgDto> imglist = new ArrayList();

        for(int i = 1; i<=10; i++){
            ImgDto simg = makeImg(i, "s");
            int gno = imgService.getGno()+1;

            imgService.reg_img(gno, simg);
            imglist.add(simg);
        }

        ArrayList<ImgDto> list = imgService.readall();

        Iterator it = list.iterator();

        int i = 0;
        while (it.hasNext()){
            assertEquals(it.next(), imglist.get(i));
            i++;
        }
    }

    @Test(expected = Exception.class)
    public void Ex_reg_img() throws Exception {
        //Column count doesn't match value count
        //INSERT 문에 지정된 열의 수와 값의 수가 일치하지 않는 경우
        ImgDto img = new ImgDto();
        String o_name = "아보카도";
        img.setImgtype("w");
        img.setFile_rt("2024_04_30");
        img.setU_name("w_2024_04_30_");
        img.setO_name(o_name);
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);

        imgService.reg_img(1, img);
    }

    @Test(expected = Exception.class)
    public void Ex_reg_img2() throws Exception {
        //음수값을 넣을시 예외 발생
        ImgDto img = new ImgDto();
        String o_name = "아보카도";
        img.setImgtype("w");
        img.setFile_rt("2024_04_30");
        img.setU_name("w_2024_04_30_");
        img.setO_name(o_name);
        img.setE_name(".png");
        img.setW_size(-292);
        img.setH_size(292);
        img.setImg_full_rt(datePath+"/"+img.getImgtype()+"_"+datePath+"_"+o_name+".png");

        imgService.reg_img(1, img);
    }

    @Test(expected = Exception.class)
    public void Ex_reg_img3() throws Exception {
        //Column count doesn't match value count: INSERT 문에 지정된 열의 수와 값의 수가 일치하지 않는 경우
        ImgDto img = new ImgDto();
        String o_name = "아보카도";
        img.setImgtype("ㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㅁㄴㅇㄹ");
        img.setFile_rt("2024_04_30");
        img.setU_name("w_2024_04_30_");
        img.setO_name(o_name);
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        img.setImg_full_rt(datePath+"/"+img.getImgtype()+"_"+datePath+"_"+o_name+".png");
        imgService.reg_img(1, img);
    }

    @Test(expected = Exception.class)
    public void DbConnectionError() throws Exception{
        ImgDao imgDaoMock = mock(ImgDao.class);
        ImgService imgServiceMock = new ImgService(imgDaoMock);
        ImgDto imgDto = makeImg(100, "e");

        // DAO 메서드 호출 시 데이터베이스 연결이 끊어졌다는 상황을 시뮬레이션
        String errorMessage = "Database connection error";
        doThrow(new RuntimeException(errorMessage)).when(imgDaoMock).insert(any(ImgDto.class));

        // when
        try {
            imgServiceMock.reg_img(1, imgDto); // 예외 발생할 것을 예상하므로 예외가 발생하는지 확인하는 테스트
        } catch (Exception e) {
            // 예외 메시지를 콘솔에 출력
            System.out.println("Caught exception: " + e.getMessage());
            throw e; // 예외를 다시 던져서 테스트가 실패하도록 함
        }
    }

    //예외가 발생해야 성공
    @Test(expected = Exception.class)
    public void readallException() throws Exception {
        ImgDao imgDaoMock = mock(ImgDao.class);
        ImgService imgServiceMock = new ImgService(imgDaoMock);

        when(imgDaoMock.select_all_img()).thenThrow(new SQLException());

        ArrayList<ImgDto> list = imgServiceMock.readall();

        verify(imgDaoMock, times(1)).select_all_img();
        assertTrue(list != null);
        System.out.println(list.size());
    }

    @Test
    @Rollback(false)
    public void readall() throws Exception {
        ArrayList<ImgDto> list = imgService.readall();
        assertTrue(list != null);

        Iterator it = list.iterator();

        pathtest();

        System.out.println(ifc.getFolderPath());

        while (it.hasNext()){
            ImgDto img = (ImgDto) it.next();
            String path = ifc.getFolderPath()+"/"+img.getImg_full_rt();

            File file = new File(path);

            if(file.exists()){
                file.delete();
                HashMap map = new HashMap();
                map.put("state", "N");
                map.put("no",  img.getNo());
                imgService.update(map);
                System.out.println("파일 존재");
            } else {
                System.out.println("파일 없음");
            }
            System.out.println(img.getState());
        }
        System.out.println(list.size());
    }

    @Test
    public void read(){
        HashMap map = new HashMap();
        map.put("tb_name", "sale");
        map.put("no", 1);
        ArrayList<ImgDto> list = imgService.read(map);
        assertTrue(list != null);
    }
    @Test
    public void count(){
        int count = imgService.count("img");
        assertTrue(count >= 0);
        System.out.println("count : "+count);
    }

    public ImgDto makeImg(int num, String imgtype){
        pathtest();
        String o_name = num+"번_아보카도";

        String uname = o_name+".png";

        ImgDto img = new ImgDto();
        img.setImgtype(imgtype);
        img.setFile_rt("2024_04_30");
//        img.setU_name(imgtype+"_2024_04_30_");
        img.setU_name(uname);
        img.setO_name(o_name);
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        img.setImg_full_rt(datePath+"/"+imgtype+"_"+datePath+"_"+o_name+".png");
        return img;
    }

    @Test
    @Rollback(false)
    public void filetest() throws Exception {
//        if(imgService.count("sale") != 0){
//            imgService.delete("sale");
//            System.out.println("sale 삭제완료");
//        }
//
//        if(imgService.count("img_group") != 0){
//            imgService.delete("img_group");
//            System.out.println("이미지 그룹 삭제완료");
//        }
//
//        if(imgService.count("img") != 0){
//            imgService.delete("img");
//            System.out.println("이미지 삭제완료");
//        }

        MockHttpServletRequest request = new MockHttpServletRequest();
        ifc = imgService.path(request);
        datePath = ifc.todaystr();

        String path = System.getProperty("user.home")+"/Desktop/Img";

        Makefolder(path, datePath);

        // 파일삭제 - 테스트용도
        deleteFile(path+"/"+datePath);

        // 디렉토리 객체 생성
        File directory = new File(path);

        //디렉토리에 있는 파일과 디렉토일 확인
        File[] files = directory.listFiles();

        boolean check = true;

        if(files != null){

            int gno = imgService.getGno()+1;

            String sfilename = "";
            for(File file : files) {
                String mimeType = getMimeType(file);
                if (mimeType != null && mimeType.startsWith("image/")) {
                    String fileName = file.getName();
                    String ename = fileName.substring(fileName.lastIndexOf('.'));
                    String wname = fileName.substring(0, fileName.lastIndexOf('.'));

                    ImgDto imgDto = null;

                    long currentTimeMillis = System.currentTimeMillis();
                    File newFile = new File(path+"/"+datePath+"/"+(gno+currentTimeMillis+ename));
                    String filename = gno+currentTimeMillis+ename;

                    if(check){
                        sfilename = gno+currentTimeMillis+ename;
                        makeimg(path, datePath, file, filename,292,292);
                        imgDto = setImginfo(newFile, wname, datePath, "s", 292, 292);
                        imgService.reg_img(gno, imgDto);
                        check = false;
                    }
                    makeimg(path, datePath, file, filename, 856,856);
                    imgDto = setImginfo(newFile, wname, datePath, "w", 292, 292);
                    imgService.reg_img(gno, imgDto);
                    makeimg(path, datePath, file, filename, 78,78);
                    imgDto = setImginfo(newFile, wname, datePath, "r", 292, 292);
                    imgService.reg_img(gno, imgDto);
                }
            }
            saleDao.insert_sale(makesale("테스트", "테스트내용", gno, datePath+"/"+sfilename));

        } else {
            System.out.println("해당 경로에 아무것도 없음");
        }
    }

    private static String getMimeType(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void Makefolder(String folderPath, String datePath) {
        /* 파일 저장 폴더 이름 (오늘 날짜별로 폴더 생성) */
        File uploadPath = new File(folderPath, datePath);

        /* 오늘 날짜 폴더가 있을시 생성 x 없으면 생성 o */
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
    }

    public void makeimg(String folderPath, String datastr, File imageFile, String filename, int wsize, int hsize) throws Exception {

        File uploadPath = new File(folderPath, datastr);

        String fileName = filename;

        System.out.println(fileName);

        /* 파일 저장 */
        try {
            File img_name = new File(uploadPath, fileName);

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

    public ImgDto setImginfo(File imgfile, String orifilename, String datastr, String imgtype, int wsize, int hsize){
        //파일 이름 얻기
        ImgDto img = new ImgDto();
        String uploadFileName = imgfile.getName();
        String fullrt = datastr+"/"+uploadFileName;
        img.setImgtype(imgtype);
        img.setFile_rt(datastr);
        img.setU_name(uploadFileName);
        img.setO_name(orifilename);
        img.setE_name(uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length()));
        img.setImg_full_rt(fullrt);
        img.setW_size(wsize);
        img.setH_size(hsize);
        return img;
    }

    public void deleteFile(String path){
        File directory2 = new File(path);
        File[] files2 = directory2.listFiles();
        if(files2 != null) {
            for (File file : files2) {
                File Dfile = null;
                try {
                    Dfile = new File(path +"/"+ URLDecoder.decode(file.getName(), "UTF-8"));
                    Dfile.delete();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
