import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.ImgFactory;
import team.cheese.domain.ImgDto;
import team.cheese.service.ImgService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Test(expected = Exception.class)
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
    public void r_reg_img2() throws Exception {
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
        //INSERT 문에 지정된 열의 수와 값의 수가 일치하지 않는 경우 발생합니다.
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
        //Incorrect integer value: 정수형 데이터 타입의 열에 문자열 값을 삽입
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

    //강제 예외 처리 발생
    @Test(expected = Exception.class)
    public void readall(){
        ArrayList<ImgDto> list = imgService.readall();
        assertTrue(list != null);
        System.out.println(list.size());
    }

    @Test
    public void read(){
        ArrayList<ImgDto> list = imgService.read(1);
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
        ImgDto img = new ImgDto();
        img.setImgtype(imgtype);
        img.setFile_rt("2024_04_30");
        img.setU_name(imgtype+"_2024_04_30_");
        img.setO_name(o_name);
        img.setE_name(".png");
        img.setW_size(292);
        img.setH_size(292);
        img.setImg_full_rt(datePath+"/"+imgtype+"_"+datePath+"_"+o_name+".png");
        return img;
    }
}
