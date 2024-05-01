import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.*;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class ImgDaoTest {
    @Autowired
    ImgDao imgDao;

    @Autowired
    SaleDao saleDao;

    String folderPath = "/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img";
    String datePath = Todaystr();
    String filename = "아보카도.png";
    static public String Todaystr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replaceAll("-", "_");
    }

    @Test
    public void delete(){
        if(imgDao.count("img_group") != 0){
            assertTrue(imgDao.delete("img_group") != 0);
            System.out.println("이미지 그룹 삭제완료");
        }

        if(imgDao.count("img") != 0){
            assertTrue(imgDao.delete("img") != 0);
            System.out.println("이미지 삭제완료");
        }

//        System.out.println("판매 삭제전 카운트 : "+imgDao.count("sale"));
//        assertTrue(imgDao.delete("sale") != 0);
//        System.out.println("판매 삭제 카운트 : "+imgDao.count("sale"));
//
//        System.out.println("이벤트 삭제전 카운트 : "+imgDao.count("event"));
//        assertTrue(imgDao.delete("event") != 0);
//        System.out.println("이벤트 삭제 카운트 : "+imgDao.count("event"));
//
//        System.out.println("이벤트 삭제전 카운트 : "+imgDao.count("user_info"));
//        assertTrue(imgDao.delete("user_info") != 0);
//        System.out.println("이벤트 삭제 카운트 : "+imgDao.count("user_info"));
    }

    @Test
    public void img_insert(){
        if(imgDao.count("img_group") != 0){
            assertTrue(imgDao.delete("img_group") != 0);
            System.out.println("이미지 그룹 삭제완료");
        }

        if(imgDao.count("img") != 0){
            assertTrue(imgDao.delete("img") != 0);
            System.out.println("이미지 삭제완료");
        }


        for(int i = 1; i<=10; i++){
//            int tb_no = makeSale(i);//판매 테이블 만들어지는곳
            int a = imgDao.select_group_max()+1;
            System.out.println("a : "+a);
            HashMap map = new HashMap<>();
            map.put("group_no", a);

            ImgDto simg = makeImg(i, "s");
            ImgDto rimg = makeImg(i, "r");
            ImgDto wimg = makeImg(i, "w");
            assertTrue(imgDao.insert(simg) != 0);
            map.put("img_no", simg.getNo());
            assertTrue(imgDao.insert_group(map) != 0);

            assertTrue(imgDao.insert(rimg) != 0);
            map.put("img_no", rimg.getNo());
            assertTrue(imgDao.insert_group(map) != 0);

            assertTrue(imgDao.insert(wimg) != 0);
            map.put("img_no", wimg.getNo());
            assertTrue(imgDao.insert_group(map) != 0);

            assertTrue(imgDao.insert(wimg) != 0);
            map.put("img_no", wimg.getNo());
            assertTrue(imgDao.insert_group(map) != 0);

            assertTrue(imgDao.insert(wimg) != 0);
            map.put("img_no", wimg.getNo());
            assertTrue(imgDao.insert_group(map) != 0);

            System.out.println(i+"번 끝");
        }
    }

    //판매, 커뮤, 이벤트, 마이페이지 쓰는거 관련 생각해보기

    //전체 이미지 조회
    @Test
    public void select(){
        List<ImgDto> list = imgDao.select_all_img();
        assertTrue(list != null);

        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("이미지 리스트 출력완료");
    }

    @Test
    public void select_img(){
        List<ImgDto> list = imgDao.select_img(1);

        assertTrue(list != null);

        Iterator it = list.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    public int makeSale(int num){
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
        saledto.setTitle(num+"번 자바의 정석 팔아요");
        saledto.setContents("자바의 정석 2판 팔아요.");
        saledto.setImg_full_rt(datePath+"/s_"+datePath+"_"+num+"번_"+filename);
        saledto.setBid_cd("N");
        saledto.setPickup_addr_cd("11060710");
        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saledto.setBrand("자바의 정석");
        saledto.setReg_price(30000);
        saledto.setCheck_addr_cd(0);

        assertTrue(saleDao.insert_sale(saledto) == 1);
        return saledto.getNo();
    }

    public int makecommu(int num){
        CommunityBoardDto cdto = new CommunityBoardDto();
        cdto.setur_id("user123");
        cdto.setaddr_cd("11010720");
        cdto.setaddr_no(1);
        cdto.setcommu_cd("commu_W");
        cdto.setaddr_name("서울특별시 종로구 사직동");
        cdto.setTitle("고민/상담테스트 제목"+num);
        cdto.setContents("내용"+num);
        cdto.setImg_full_rt(datePath+"/s_"+datePath+"_"+num+"번_"+filename);
        cdto.setNick("skyLee");

        assertTrue(imgDao.insert_commu(cdto) == 1);
        return cdto.getno();
    }

    public ImgDto makeImg(int num, String imgtype){
        String o_name = num+"번_아보카도";
        ImgDto img = new ImgDto();
        img.setImgtype(imgtype);
        img.setFilert("2024_04_30");
        img.setU_name(imgtype+"_2024_04_30_");
        img.setO_name(o_name);
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        img.setImg_full_rt(datePath+"/"+imgtype+"_"+datePath+"_"+o_name+".png");
        return img;
    }

}
