import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.EventDto;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.domain.UserInfoDTO;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
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

    @Test
    public void imgTest(){
        //판매테이블 작성
        SaleDto saledto = new SaleDto();
        saledto.setSeller_id("user123");
        saledto.setSal_i_cd("016001005");
        saledto.setPro_s_cd("C");
        saledto.setTx_s_cd("S");
        saledto.setTrade_s_cd_1("F");
        saledto.setTrade_s_cd_2("F");
        saledto.setPrice(28000);
        saledto.setSal_s_cd("S");
        saledto.setTitle("자바의 정석");
        saledto.setContents("자바의 정석 2판 팔아요.");
        saledto.setBid_cd("N");
        saledto.setPickup_addr_cd("11060710");
        saledto.setDetail_addr("회기역 1번출구 앞(20시 이후만 가능)");
        saledto.setBrand("자바의 정석");
        saledto.setReg_price(30000);
        saledto.setCheck_addr_cd(0);
        assertTrue(saleDao.insert_sale(saledto) == 1);
        System.out.println("--sale 생성--");
        int sno = saledto.getNo();
        //이미지 작성
        ImgDto img = new ImgDto("sale", sno, "s", Todaystr(), "s_"+Todaystr()+"_", "cho", ".png", 50, 50);
        System.out.println(img);
        assertTrue(imgDao.insert_img(img) == 1);
        img = new ImgDto("sale", sno, "w", Todaystr(), "w_"+Todaystr()+"_", "cho", ".png", 50, 50);
        assertTrue(imgDao.insert_img(img) == 1);
        img = new ImgDto("sale", sno, "w", Todaystr(), "w_"+Todaystr()+"_", "마루1", ".jpeg", 50, 50);
        assertTrue(imgDao.insert_img(img) == 1);
        img = new ImgDto("sale", sno, "w", Todaystr(), "w_"+Todaystr()+"_", "아보카도", ".png", 50, 50);
        assertTrue(imgDao.insert_img(img) == 1);
        img = new ImgDto("sale", sno, "r", Todaystr(), "r_"+Todaystr()+"_", "cho", ".png", 50, 50);
        assertTrue(imgDao.insert_img(img) == 1);
        img = new ImgDto("sale", sno, "r", Todaystr(), "r_"+Todaystr()+"_", "마루1", ".jpeg", 50, 50);
        assertTrue(imgDao.insert_img(img) == 1);
        img = new ImgDto("sale", sno, "r", Todaystr(), "r_"+Todaystr()+"_", "아보카도", ".png", 50, 50);
        assertTrue(imgDao.insert_img(img) == 1);

        List<ImgDto> imglist = imgDao.selectAll_img();

        Iterator it = imglist.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("-- 이미지 작성완료 --");

        //교차테이블 작성
        HashMap map = new HashMap();
        map.put("no", sno);
        map.put("col_name", "sal_no");
        map.put("no_name", "no");
        map.put("cross_tb_name", "sale");
        map.put("tb_name", "sale_img");
        assertTrue(imgDao.insert_cross(map) != 0);
        System.out.println("교차테이블 작성완료");

        //썸네일 리스트 보기
        List<ImgDto> list = imgDao.select_s_imglist();
        assertTrue(list != null);
        Iterator it3 = list.iterator();

        while (it3.hasNext()){
            System.out.println(it3.next());
        }
        System.out.println("--썸네일 이미지 리스트 보기 끝--");

        //본문 이미지 리스트 보기
        HashMap map2 = new HashMap();
        map2.put("tb_name", "sale");
        map2.put("tb_name2", "sale_img");
        map2.put("tb_colum", "sal_no");
        map2.put("no", sno);
        List<ImgDto> list2 = imgDao.select_w_imglist(map2);
        assertTrue(list2 != null);
        Iterator it2 = list2.iterator();

        while (it2.hasNext()){
            System.out.println(it2.next());
        }
        System.out.println("--본문 이미지 리스트 보기 끝--");
    }



    //썸네일 리스트 보기
    @Test
    public void viewSlist(){
        List<SaleDto> list = imgDao.selectsimg();

        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void viewSlist2(){
        List<EventDto> list = imgDao.selecteimg();

        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void viewSlist3(){
        UserInfoDTO userprofie = imgDao.selectuimg("user123");
        System.out.println(userprofie);
    }

    //예외
    public String Todaystr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replaceAll("-", "_");
    }

    //세부내용 이미지 리스트보기
//
//    @Test
//    public void insertmyImg(){
//        ImgDto img = new ImgDto();
//        img.setTb_name("user_info");
//        img.setTb_no(1);
//        img.setImgtype("m");
//        img.setFilert("2024_04_28");
//        img.setU_name("m_2024_04_27_");
//        img.setO_name("아보카도");
//        img.setE_name(".png");
//        img.setW_size(50);
//        img.setH_size(50);
//        assertTrue(imgDao.insert_img(img) == 1);
//        System.out.println(imgDao.insert_img(img));
//    }
//
//    @Test
//    public void img_selectAll(){
//        List<ImgDto> list = imgDao.selectAll_img();
//        assertTrue(imgDao.selectAll_img() != null);
//        Iterator it = list.iterator();
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//    }
//
//    @Test
//    public void sale_img_test(){
//        //교차테이블 작성 테스트
//        //assertTrue(imgDao.delete_cross_all("community_img") != 0);
//        HashMap map = new HashMap();
//        map.put("no", 3);
//        map.put("col_name", "commu_no");
//        map.put("cross_tb_name", "community_board");
//        map.put("tb_name", "community_img");
//        int change = imgDao.insert_cross(map);
//        assertTrue(change > 1);
//    }
//
//    @Test
//    public void img_test(){
////        assertTrue(imgDao.delete_sale_img_all("community_img") != 0);
//
//        ImgDto img = new ImgDto();
//        img.setTb_name("post");
//        img.setTb_no(3);
//        img.setImgtype("s");
//        img.setFilert("/Users/jehyeon/Desktop/Team/src/main/webapp/resources/img");
//        img.setU_name("s_52932408-4685-4136-b20a-cacfa1fd478a_");
//        img.setO_name("아보카도");
//        img.setE_name(".png");
//        img.setW_size(292);
//        img.setH_size(292);
//        for(int i = 0; i<5; i++){
//            imgDao.insert_img(img);
//        }
//        assertTrue(imgDao.insert_img(img) == 1);
//
//        List<ImgDto> list = imgDao.selectAll_img();
//        assertTrue(imgDao.selectAll_img() != null);
//        Iterator it = list.iterator();
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//
//        HashMap map = new HashMap();
//        map.put("no", 3);
//        map.put("col_name", "post_no");
//        map.put("no_name", "sn");
//        map.put("cross_tb_name", "post");
//        map.put("tb_name", "community_img");
//        int change = imgDao.insert_cross(map);
//        assertTrue(change > 1);
//    }
//
//    @Test
//    public void SimgList(){
//        List<ImgDto> list = imgDao.select_s_imglist();
//        assertTrue(list != null);
//        Iterator it = list.iterator();
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//    }
//    @Test
//    public void WimgList(){
//        HashMap map = new HashMap();
//        //커뮤 확인용도
////        map.put("tb_name", "community_board");
////        map.put("tb_name2", "community_img");
////        map.put("tb_colum", "commu_no");
////        map.put("no", 3);
//
//        //세일 확인용도
//        map.put("tb_name", "sale");
//        map.put("tb_name2", "sale_img");
//        map.put("tb_colum", "sal_no");
//        map.put("no", 86);
//        List<ImgDto> list = imgDao.select_w_imglist(map);
//        assertTrue(list != null);
//        Iterator it = list.iterator();
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
//    }

//    @Test
//    public void oneimgList(){
//        HashMap map = new HashMap();
//        //이벤트 이미지 확인용도
////        map.put("tb_name", "event");
////        map.put("imgtype", "e");
////        map.put("tb_colum", "evt_no");
////        map.put("tb_value", 1);
//
//        map.put("tb_name", "user_info");
//        map.put("imgtype", "m");
//        map.put("tb_colum", "ur_id");
//        map.put("tb_value", "user123");
//        ImgDto imgone = imgDao.select_oneimg(map);
//        assertTrue(imgone != null);
//
//        System.out.println(imgone);
//    }
//
//    @Test
//    public void update(){
//        HashMap map = new HashMap();
//        map.put("tb_name", "sale");
//        map.put("tb_no", 87);
//        map.put("filename", "마루1");
//        int tt = imgDao.delete_img_state(map);
//        System.out.println(tt);
//        assertTrue(tt != 0);
//    }
//
//    @Test
//    public void select_one_simg(){
//        ImgDto simg = imgDao.select_one_simg(96);
//        System.out.println(simg.getFilert()+"/"+simg.getU_name()+simg.getO_name()+simg.getE_name());
//    }


}
