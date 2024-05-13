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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public  class CommunityBoardServiceImplTest {
    @Autowired
    CommunityBoardDao communityBoardDao;

    @Autowired
    CommunityBoardService communityBoardService;



    @Test
    public void readAll() throws Exception {
        communityBoardService.removeAll();



        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");
        communityBoardDto.setur_state('y'); // 상태 설정
        String session_id = communityBoardDto.getur_id();
        communityBoardService.write(communityBoardDto);

        assertEquals(1, communityBoardService.readAll().size());


        communityBoardDto.setur_state('n');
        communityBoardDto.setad_state('n');
        communityBoardService.userStateChange(communityBoardDto);
//        communityBoardDto = (CommunityBoardDto) communityBoardService.readAll('y');
        System.out.println(communityBoardDto.toString());
        assertEquals(0, communityBoardService.readAll().size());
    }



    @Test
    public void write() throws Exception {
        communityBoardService.removeAll();

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

        communityBoardService.read(communityBoardDto.getno());
        int afterCount = communityBoardService.getCount();
        assertTrue(afterCount== beforeCount + 1);
        assertEquals(1,afterCount);


    }


    @Test
    public void remove() throws Exception {
        communityBoardService.removeAll();
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");
        String session_id = communityBoardDto.getur_id();
        communityBoardService.write(communityBoardDto);
        communityBoardService.read(communityBoardDto.getno());
        assertEquals(1,communityBoardService.getCount());

        communityBoardService.remove(communityBoardDto.getno());
        assertEquals(0,communityBoardService.getCount());

    }

    @Test
    public void modify() throws Exception {
        communityBoardService.removeAll();
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("라이트 테스트");
        communityBoardDto.setContents("라이트 테스트");
        communityBoardDto.setNick("skyLee");
        String session_id = communityBoardDto.getur_id();
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
        communityBoardDao.deleteAll();

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

        String session_id = communityBoardDto.getur_id();
         communityBoardService.write(communityBoardDto);
        communityBoardDto = communityBoardService.read(communityBoardDto.getno());
        assertEquals(1,communityBoardService.getCount());
        assertEquals(communityBoardDto.getno(), communityBoardService.read(communityBoardDto.getno()).getno());
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

        String session_id = communityBoardDto.getur_id();
        communityBoardService.write(communityBoardDto);
        assertEquals(1,communityBoardService.getCount());

        communityBoardDto = communityBoardService.read(communityBoardDto.getno());
        communityBoardService.findCommunityBoardById(communityBoardDto.getno());


        communityBoardDto.setur_state('n');
        communityBoardService.userStateChange(communityBoardService.read(communityBoardDto.getno()));
        assertEquals('n',communityBoardDto.getur_state());
    }

    @Test
    public void userStateChange2()throws Exception{
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

        String session_id = communityBoardDto.getur_id();
        communityBoardService.write(communityBoardDto);
        assertEquals(1,communityBoardService.getCount());

        communityBoardDto = communityBoardService.read(communityBoardDto.getno());
        assertEquals('y',communityBoardDto.getur_state());
        System.out.println(communityBoardDto.getur_state());

        Character state = communityBoardDto.getur_state()== 'y' ? 'n' : 'y';
        communityBoardDto.setur_state(state);
        assertEquals('n',communityBoardDto.getur_state());
        System.out.println(communityBoardDto.getur_state());
    }


    @Test
    public void findCommunityBoardById()throws Exception{
        //init
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
        assertEquals(1,communityBoardService.getCount());

        //given
        communityBoardDto = communityBoardService.read(communityBoardDto.getno());
        communityBoardService.findCommunityBoardById(communityBoardDto.getno());

        //assert
        assertEquals('y',communityBoardDto.getur_state());
        System.out.println(communityBoardDto.toString());
    }


    @Test
    public void totalLikeCount()throws Exception{
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
        assertEquals(1,communityBoardService.getCount());
        communityBoardDto = communityBoardService.read(communityBoardDto.getno());
        int result = communityBoardService.totalLike(communityBoardDto.getno());
        assertEquals(0,result);

        communityBoardDto.setlike_cnt(1);
        result = communityBoardService.totalLike(communityBoardDto.getno());
        assertEquals(1,result);

    }
}