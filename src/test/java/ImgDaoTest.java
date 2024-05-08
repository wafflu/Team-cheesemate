import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.*;
import team.cheese.entity.ImgFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class ImgDaoTest {
    @Autowired
    ImgDao imgDao;

    @Autowired
    SaleDao saleDao;

    ImgFactory ifc = new ImgFactory();

    String folderPath = ifc.getFolderPath()+ifc.getDatePath();
    String datePath = ifc.getDatePath();

    //    @Test
    public void delete(){
        if(imgDao.count("sale") != 0){
            assertTrue(imgDao.delete("sale") != 0);
            System.out.println("세일 삭제완료");
        }

        if(imgDao.count("img_group") != 0){
            assertTrue(imgDao.delete("img_group") != 0);
            System.out.println("이미지 그룹 삭제완료");
        }

        if(imgDao.count("img") != 0){
            assertTrue(imgDao.delete("img") != 0);
            System.out.println("이미지 삭제완료");
        }
    }

    //실제 이미지를 가지고 테스트
    @Test
    public void imginsert(){
        delete();

        ArrayList<ImgDto> list = new ArrayList<>();

        String path = ifc.getFolderPath();
        //날짜 폴더 있는지 체크하고 만들기
        Makefolder(path, ifc.getDatePath());

        File directory = new File(path);

        File[] files = directory.listFiles();

        boolean scheck = true;
        int gno = imgDao.select_group_max()+1;
        for (File file : files) {
            String mimeType = getMimeType(file);
            if (mimeType != null && mimeType.startsWith("image/")) {
                HashMap map = new HashMap<>();
                map.put("group_no", gno);
                if(scheck){
                    //이미지 제작
                    ImgDto simg = ifc.makeImg(file, "s", gno, 292,292);
                    //이미지 디비 등록
                    assertTrue(imgDao.insert(simg) == 1);
                    map.put("img_no", simg.getNo());
                    //이미지 그룹 디비 등록
                    assertTrue(imgDao.insert(map) == 1);
                    list.add(simg);
                    scheck = false;
                }
                ImgDto rimg = ifc.makeImg(file, "r", gno, 78,78);
                //이미지 디비 등록
                assertTrue(imgDao.insert(rimg) == 1);
                map.put("img_no", rimg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                list.add(rimg);

                ImgDto wimg = ifc.makeImg(file, "w", gno, 856,856);
                //이미지 디비 등록
                assertTrue(imgDao.insert(wimg) == 1);
                map.put("img_no", wimg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                list.add(wimg);
            }
        }
        //전체 조회
        selectall(list);
    }

    //이미지 10번 넣기
    @Test
    public void imginsert10(){
        delete();

        ArrayList<ImgDto> list = new ArrayList<>();

        boolean scheck = true;
        int gno = imgDao.select_group_max()+1;
        for (int i = 0; i<10; i++) {
            File file = new File(i+"번 아보카도.png");

            HashMap map = new HashMap<>();
            map.put("group_no", gno);
            if(scheck){
                //이미지 제작
                ImgDto simg = ifc.setImginfo(file, file.getName(), "s", 292, 292);
                //이미지 디비 등록
                assertTrue(imgDao.insert(simg) == 1);
                map.put("img_no", simg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                list.add(simg);
                scheck = false;
            }
            ImgDto rimg = ifc.setImginfo(file, file.getName(), "r", 78,78);
            //이미지 디비 등록
            assertTrue(imgDao.insert(rimg) == 1);
            map.put("img_no", rimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(rimg);

            ImgDto wimg = ifc.setImginfo(file, file.getName(), "w", 856,856);
            //이미지 디비 등록
            assertTrue(imgDao.insert(wimg) == 1);
            map.put("img_no", wimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(wimg);
        }
        //전체 조회
        selectall(list);
    }

    //이미지 100번 넣기
    @Test
    public void imginsert100(){
        delete();

        ArrayList<ImgDto> list = new ArrayList<>();

        boolean scheck = true;
        int gno = imgDao.select_group_max()+1;
        for (int i = 0; i<100; i++) {
            File file = new File(i+"번 아보카도.png");

            HashMap map = new HashMap<>();
            map.put("group_no", gno);
            if(scheck){
                //이미지 제작
                ImgDto simg = ifc.setImginfo(file, file.getName(), "s", 292, 292);
                //이미지 디비 등록
                assertTrue(imgDao.insert(simg) == 1);
                map.put("img_no", simg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                list.add(simg);
                scheck = false;
            }
            ImgDto rimg = ifc.setImginfo(file, file.getName(), "r", 78,78);
            //이미지 디비 등록
            assertTrue(imgDao.insert(rimg) == 1);
            map.put("img_no", rimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(rimg);

            ImgDto wimg = ifc.setImginfo(file, file.getName(), "w", 856,856);
            //이미지 디비 등록
            assertTrue(imgDao.insert(wimg) == 1);
            map.put("img_no", wimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(wimg);
        }
        //전체 조회
        selectall(list);
    }

    //10개의 이미지 업데이트
    @Test
    public void imgupdate10(){
        delete();

        ArrayList<ImgDto> list = new ArrayList<>();

        boolean scheck = true;
        int gno = imgDao.select_group_max()+1;
        for (int i = 0; i<10; i++) {
            File file = new File(i+"번 아보카도.png");

            HashMap map = new HashMap<>();
            map.put("group_no", gno);
            if(scheck){
                //이미지 제작
                ImgDto simg = ifc.setImginfo(file, file.getName(), "s", 292, 292);
                //이미지 디비 등록
                assertTrue(imgDao.insert(simg) == 1);
                map.put("img_no", simg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                list.add(simg);
                scheck = false;
            }
            ImgDto rimg = ifc.setImginfo(file, file.getName(), "r", 78,78);
            //이미지 디비 등록
            assertTrue(imgDao.insert(rimg) == 1);
            map.put("img_no", rimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(rimg);

            ImgDto wimg = ifc.setImginfo(file, file.getName(), "w", 856,856);
            //이미지 디비 등록
            assertTrue(imgDao.insert(wimg) == 1);
            map.put("img_no", wimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(wimg);
        }
        //전체 조회
        selectall(list);

        //비활성화
        HashMap map = new HashMap();
        map.put("state", "N");
        map.put("no",  gno);
        assertTrue(imgDao.update(map) != 0);

        map.put("state", "Y");
        map.put("no",  gno);
        assertTrue(imgDao.update(map) != 0);
    }

    //100개의 이미지 업데이트
    @Test
    public void imgupdate100(){
        delete();

        ArrayList<ImgDto> list = new ArrayList<>();

        boolean scheck = true;
        int gno = imgDao.select_group_max()+1;
        for (int i = 0; i<100; i++) {
            File file = new File(i+"번 아보카도.png");

            HashMap map = new HashMap<>();
            map.put("group_no", gno);
            if(scheck){
                //이미지 제작
                ImgDto simg = ifc.setImginfo(file, file.getName(), "s", 292, 292);
                //이미지 디비 등록
                assertTrue(imgDao.insert(simg) == 1);
                map.put("img_no", simg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                list.add(simg);
                scheck = false;
            }
            ImgDto rimg = ifc.setImginfo(file, file.getName(), "r", 78,78);
            //이미지 디비 등록
            assertTrue(imgDao.insert(rimg) == 1);
            map.put("img_no", rimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(rimg);

            ImgDto wimg = ifc.setImginfo(file, file.getName(), "w", 856,856);
            //이미지 디비 등록
            assertTrue(imgDao.insert(wimg) == 1);
            map.put("img_no", wimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(wimg);
        }
        //전체 조회
        selectall(list);

        //비활성화
        HashMap map = new HashMap();
        map.put("state", "N");
        map.put("no",  gno);
        assertTrue(imgDao.update(map) != 0);

        map.put("state", "Y");
        map.put("no",  gno);
        assertTrue(imgDao.update(map) != 0);
    }

    // 판매 + 이미지 조합
    @Test
    public void saleimg_insert(){
        delete();

        ArrayList<ImgDto> list = new ArrayList<>();

        boolean scheck = true;
        int gno = imgDao.select_group_max()+1;
        String img_full_rt = "";
        for (int i = 0; i<10; i++) {
            File file = new File(i+"번 아보카도.png");

            HashMap map = new HashMap<>();
            map.put("group_no", gno);
            if(scheck){
                //이미지 제작
                ImgDto simg = ifc.setImginfo(file, file.getName(), "s", 292, 292);
                //이미지 디비 등록
                assertTrue(imgDao.insert(simg) == 1);
                map.put("img_no", simg.getNo());
                //이미지 그룹 디비 등록
                assertTrue(imgDao.insert(map) == 1);
                img_full_rt = simg.getImg_full_rt();
                list.add(simg);
                scheck = false;
            }
            ImgDto rimg = ifc.setImginfo(file, file.getName(), "r", 78,78);
            //이미지 디비 등록
            assertTrue(imgDao.insert(rimg) == 1);
            map.put("img_no", rimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(rimg);

            ImgDto wimg = ifc.setImginfo(file, file.getName(), "w", 856,856);
            //이미지 디비 등록
            assertTrue(imgDao.insert(wimg) == 1);
            map.put("img_no", wimg.getNo());
            //이미지 그룹 디비 등록
            assertTrue(imgDao.insert(map) == 1);
            list.add(wimg);
        }
        //전체 조회
        selectall(list);

        int saleno = makeSale(gno, img_full_rt);
        select_saleimg(saleno);
    }

    //전체 이미지 조회
    public void selectall(ArrayList<ImgDto> imglist){
        ArrayList<ImgDto> list = imgDao.select_all_img();
        assertTrue(list != null);

        Iterator it = list.iterator();

        int i = 0;
        while (it.hasNext()){
            assertEquals(it.next(), imglist.get(i));
            i++;
        }
        System.out.println("이미지 리스트 조회완료");
    }


    //선택한 게시물에 대한 이미지 불러오기

    public void select_saleimg(int gno){
        HashMap map = new HashMap();
        map.put("tb_name", "sale");
        map.put("no", gno);
        List<ImgDto> list = imgDao.select_img(map);

        assertTrue(list != null);

        Iterator it = list.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }


    public int makeSale(int gno, String img_full_rt){
        SaleDto saledto = new SaleDto();
        saledto.setAddr_cd("11010720");
        saledto.setAddr_name("서울특별시 종로구 사직동");
        saledto.setSeller_id("user123");
        saledto.setSeller_nick("minyoung");
        saledto.setSal_i_cd("001002002");
        saledto.setSal_name("후드티/후드집업");
        saledto.setGroup_no(gno);
        saledto.setImg_full_rt(img_full_rt);
        saledto.setPro_s_cd("C");
        saledto.setTx_s_cd("S");
        saledto.setTrade_s_cd_1("F");
        saledto.setTrade_s_cd_2("F");
        saledto.setSal_s_cd("S");
        saledto.setTitle("title");
        saledto.setContents("contents");
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

        assertTrue(saleDao.insert_sale(saledto) == 1);
        return saledto.getGroup_no();
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
}
