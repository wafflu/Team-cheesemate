package team.cheese.service.CommunityBoard;

import net.bytebuddy.description.type.TypeList;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.support.JstlUtils;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;

import team.cheese.dao.CommunityBoard.CommunityBoardDao;


import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommunityBoardServiceImplTest {
    @Autowired
    CommunityBoardDao communityBoardDao;

    @Autowired
    CommunityBoardService communityBoardService;

    @Test
    public void selectAll() throws Exception {
        List<CommunityBoardDto> list = communityBoardService.selectAll();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    @Test
    public void write() throws Exception {
        int beforeCount = communityBoardService.getCount();
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");
        communityBoardService.write(communityBoardDto);
        int afterCount = communityBoardService.getCount();
        assertTrue(afterCount== beforeCount + 1);
        System.out.println(communityBoardService.getCount());
    }


    @Test
    public void remove() throws Exception {
        if(communityBoardDao.select(33)!= null){
            communityBoardService.remove(33);
        }

    }

    @Test
    public void modify() throws Exception {
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");
         communityBoardService.write(communityBoardDto);



        int beforeCnt =  communityBoardService.getCount();


        communityBoardDto.setno(32);
        communityBoardDto.setTitle("라이트수정테스트");
        communityBoardDto.setContents("라이트 수정 테스트");
        communityBoardService.modify(communityBoardDto);

//        communityBoardDao.select(60);

        int afterCnt =  communityBoardService.getCount();
        assertEquals(beforeCnt, afterCnt);


    }

@Test
    public void read()throws Exception{


        // 테스트 데이터
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");

        // 게시물 작성
        int writeResult = communityBoardService.write(communityBoardDto);

        // 게시물 작성이 성공했는지 확인
        assertEquals(1, writeResult);


        // 게시물 읽기
        communityBoardService.read(30);

        assertTrue(communityBoardService.read(30).getview_cnt() > 0);

    }


    @Test
    public void getTopTen()throws Exception{
        List<CommunityBoardDto> getTopTen = communityBoardService.getTopTen();
        Iterator it = getTopTen.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

    }


    @Test
    public void userStateChange()throws Exception{
        communityBoardDao.deleteAll();
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("스테이트체인지 테스트");
        communityBoardDto.setContents("스테이트체인자 테스트");
        communityBoardDto.setNick("skyLee");

        communityBoardService.write(communityBoardDto);


        communityBoardDto = communityBoardDao.select(50);
        assertEquals('y',communityBoardDto.getur_state());

        communityBoardDto = communityBoardDao.select(50);

        assertEquals('n',communityBoardDto.getur_state());
    }

}