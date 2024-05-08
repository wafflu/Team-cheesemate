import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.entity.ImgFactory;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.exception.DataFailException;
import team.cheese.exception.ImgNullException;
import team.cheese.service.ImgService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@Transactional

public class ImgServiceTest {
    @Autowired
    ImgService imgService;

    @Autowired
    SaleDao saleDao;

    ImgFactory ifc = new ImgFactory();
    String datePath = "";
    // 테스트용 패스 찾기용

    @Test
    @Rollback(false)
    public void delete(){
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
    }

    //롤백안하고 데이터 입력
    @Test
    @Rollback(false)
    public void nr_reg_img() throws Exception {
        File file = new File("test 아보카도.png");
        ImgDto img = ifc.setImginfo(file, file.getName(), "s", 292, 292);
        int gno = imgService.getGno()+1;
        assertTrue(imgService.reg_img(gno, img));
        List<ImgDto> list = imgService.readall();
        assertTrue(list != null);

    }

    //등록 관련
    //mock 사용
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
            File file = new File("test 아보카도.png");
            ImgDto simg = ifc.setImginfo(file, file.getName(), "s", 292, 292);
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
    public void exnull() throws Exception {
        ImgDto img = imginfo("아보카도.png");
        img.setImg_full_rt(null);
        try {
            imgService.reg_img(1, img);
            System.out.println("Expected DataFailException was not thrown");
        } catch (DataIntegrityViolationException e) {
            // 예외가 발생했을 때의 처리
            // 여기서는 테스트가 성공적으로 수행되었음을 확인합니다.
            throw new DataIntegrityViolationException("데이터 입력 오류");
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

    @Test(expected = Exception.class)
    public void Ex_reg_img2() throws Exception {
        ImgDto img = null;
        try {
            imgService.reg_img(1, img);
            System.out.println("Expected DataFailException was not thrown");
        } catch (DataFailException e) {
            System.out.println("데이터 입력 오류");
        } catch (Exception e) {
            System.out.println("Unexpected exception was thrown");
        }
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

//        pathtest();

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
//        pathtest();
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

    //멀티파트 테스트용도입니다.
//    @Test
    public List<ImgDto> filerequest() {
//    public void filerequest() {
        // 파일삭제 - 테스트용도
        deleteFile(ifc.getFolderPath() + File.separator + ifc.getDatePath());

        String path = ifc.getFolderPath();

        File directory = new File(path);

        // 폴더에 있는 이미지파일만 담는 리스트
        ArrayList<File> fileList = new ArrayList<>();
        // 해당 경로에 있는 파일을 담는 배열
        File[] files = directory.listFiles();

        List<ImgDto> list = null;
        try {
            for (File file : files) {
                String mimeType = getMimeType(file);
                if (mimeType != null && mimeType.startsWith("image/")) {
                    fileList.add(file);
                }
            }

            // 파일들을 MockMultipartFile 객체로 변환
            ArrayList<MultipartFile> multipartFileList = new ArrayList<>();

            MultipartFile[] multipartFiles = new MultipartFile[fileList.size()];
            int i = 0;
            System.out.println(ifc.getFolderPath());
            for (File file : fileList) {
                // 파일 내용을 바이트 배열로 읽어오기
                byte[] fileContent = Files.readAllBytes(file.toPath());

                // 바이트 배열을 사용하여 MockMultipartFile 생성
                MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), "image/jpeg", fileContent);
                multipartFileList.add(multipartFile);
                multipartFiles[i++] = multipartFile;
            }

            list = ifc.makeImg(multipartFiles, "r", 78, 78);

            // 변환된 파일들을 사용하여 멀티파트 테스트 수행
            for (MultipartFile multipartFile : multipartFileList) {
                System.out.println("File name: " + multipartFile.getOriginalFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Test
    @Rollback(false)
    public void filetest() throws Exception {
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

        if(filerequest() == null){
            System.out.println("시스템 에러 종료");
            System.exit(0);
        }

        int gno = imgService.getGno()+1;
        String folderpath = ifc.getFolderPath() + File.separator + ifc.getDatePath();

        boolean cnt = true;

        String spath = "";

        for(ImgDto img : filerequest()){
            File file = new File(folderpath, img.getO_name()+img.getE_name());
            if(cnt) {
                spath = img.getImg_full_rt();
                // 썸네일은 한개만
                ImgDto simg = ifc.makeImg(file, "s", gno, 292, 292);
                assertTrue(imgService.reg_img(gno, simg));
                cnt = false;
            }
            ImgDto wimg = ifc.makeImg(file, "w", gno, 856, 856);
            assertTrue(imgService.reg_img(gno, img));
            assertTrue(imgService.reg_img(gno, wimg));
        }
        saleDao.insert_sale(makesale("테스트", "테스트내용", gno, spath));
        System.out.println(spath);
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

    public ImgDto imginfo(String uploadFileName){
        ImgDto img = new ImgDto();
        String fullrt = datePath+File.separator+uploadFileName;
        img.setImgtype("s");
        img.setFile_rt(datePath);
        img.setU_name(uploadFileName);
        img.setO_name(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
        img.setE_name(uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length()));
        img.setImg_full_rt(fullrt);
        img.setW_size(0);
        img.setH_size(0);
        return img;
    }
}
