package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;

import team.cheese.dao.CommunityBoard.CommunityBoardDao;
import team.cheese.service.CommunityBoard.CommunityBoardService;
import team.cheese.service.CommunityBoard.CommunityBoardServiceImpl;


import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommunityBoardDaoImplTest {
   @Autowired
   CommunityBoardDao communityBoardDao;
    @Autowired
    private CommunityBoardService communityBoardService;

    @Test
    public void count() throws Exception {
        System.out.println(communityBoardDao.count());
    }

    @Test
    public void insert() throws Exception {
        communityBoardDao.deleteAll();
        assertEquals(0,communityBoardDao.count());
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("좋아하는사람 제목");
        communityBoardDto.setContents("좋아하는 사람 내용");
        communityBoardDto.setNick("skyLee");

        int result = communityBoardDao.insert(communityBoardDto);

        assertEquals(1,result);
        assertEquals(1,communityBoardDao.count());
//        System.out.println(communityBoardDao.select(communityBoardDto.getno()));

    }

    @Test
    public void insertAll()throws Exception{
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        for (int i = 0; i < 10; i++) {
            communityBoardDto.setur_id("user123");
            communityBoardDto.setaddr_cd("11010720");
            communityBoardDto.setaddr_no(1);
            communityBoardDto.setcommu_cd("commu_L");
            communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
            communityBoardDto.setTitle("좋아하는사람 제목" + i);
            communityBoardDto.setContents("좋아하는 사람 내용" + i) ;
            communityBoardDto.setNick("skyLee");
            communityBoardDao.insert(communityBoardDto);
        }

        for (int i = 0; i < 10; i++) {
            communityBoardDto.setur_id("david234");
            communityBoardDto.setaddr_cd("11010530");
            communityBoardDto.setaddr_no(2);
            communityBoardDto.setcommu_cd("commu_B");
            communityBoardDto.setaddr_name("서울특별시 종로구 청운효자동");
            communityBoardDto.setTitle("잡담제목" + i);
            communityBoardDto.setContents("잡담내용" + i) ;
            communityBoardDto.setNick("minyoung");
            communityBoardDao.insert(communityBoardDto);
        }




    }

    @Test
    public void selectAll() throws Exception {
        communityBoardDao.deleteAll();
        assertEquals(0,communityBoardDao.count());

        CommunityBoardDto communityBoardDto = new CommunityBoardDto();

        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("테스트 제목");
        communityBoardDto.setContents("테스트 내용");
        communityBoardDto.setNick("skyLee");

        communityBoardDao.insert(communityBoardDto);

        List<CommunityBoardDto> list = communityBoardDao.selectAll('y');

        assertEquals(1,communityBoardDao.count());
        assertEquals(1,list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println("before:" + it.next().toString());
        }
        communityBoardDto.setur_state('n');
        communityBoardDao.userChangeState(communityBoardDto);
        communityBoardDao.selectAll('y');
        assertEquals(1,communityBoardDao.count());
        assertEquals(0,communityBoardDao.selectAll('y').size());
        System.out.println("after:" + communityBoardDto.toString());


    }

    @Test
    public void select()throws Exception{
        communityBoardDao.deleteAll();
        assertEquals(0,communityBoardDao.count());
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();

        communityBoardDto.setur_id("user123");
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("셀렉트 테스트제목");
        communityBoardDto.setContents("셀렉트 테스트내용");
        communityBoardDto.setNick("skyLee");

        communityBoardDao.insert(communityBoardDto);
        communityBoardDao.select(communityBoardDto.getno());


       assertEquals('y',communityBoardDao.select(communityBoardDto.getno()).getur_state());

    }

    @Test
    public void delete()throws Exception{
        //given:테스트 테이블 insert
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("테스트 제목");
        communityBoardDto.setContents("테스트 내용");
        communityBoardDto.setNick("skyLee");

        int insertResult = communityBoardDao.insert(communityBoardDto);
        assertEquals(1,insertResult);
        //do:테스트 테이블 delete

        communityBoardDto = communityBoardDao.select(communityBoardDto.getno());

        //assert:delete되었는지 확인
        assertEquals(1, communityBoardDao.delete(communityBoardDto.getno()));
    }

    @Test
    public void deleteAll()throws Exception{
        communityBoardDao.deleteAll();

    };


    @Test
    public void update()throws Exception {
        communityBoardDao.deleteAll();

        CommunityBoardDto communityBoardDtoBefore = new CommunityBoardDto();
        CommunityBoardDto communityBoardDtoAfter = new CommunityBoardDto();

        communityBoardDtoBefore.setur_id("user123");
        communityBoardDtoBefore.setaddr_cd("11010720");
        communityBoardDtoBefore.setaddr_no(1);
        communityBoardDtoBefore.setcommu_cd("commu_L");
        communityBoardDtoBefore.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDtoBefore.setTitle("비포 제목");
        communityBoardDtoBefore.setContents("비포 내용");
        communityBoardDtoBefore.setNick("skyLee");

        communityBoardDao.insert(communityBoardDtoBefore);
        communityBoardDtoAfter = communityBoardDao.select(communityBoardDtoBefore.getno());


        communityBoardDtoAfter.setTitle("애프터 수정제목");
        communityBoardDtoAfter.setContents("애프터 수정내용");


        communityBoardDao.update(communityBoardDtoAfter);
        communityBoardDtoAfter = communityBoardDao.select(communityBoardDtoAfter.getno());

       assertTrue(communityBoardDtoBefore.getTitle()!= communityBoardDtoAfter.getTitle());
        System.out.println(communityBoardDtoAfter.getTitle());
        System.out.println(communityBoardDtoBefore.getTitle());
    }



    @Test
    public void increaseViewCnt()throws Exception{
        //given : 특정 no을 select
        //do view_cnt++
        //assert
        communityBoardDao.deleteAll();
        assertEquals(0,communityBoardDao.count());

        CommunityBoardDto communityBoardDto = new CommunityBoardDto();
        communityBoardDto.setur_id("user123");
        communityBoardDto.setaddr_cd("11010720");
        communityBoardDto.setaddr_no(1);
        communityBoardDto.setcommu_cd("commu_L");
        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
        communityBoardDto.setTitle("테스트 뷰");
        communityBoardDto.setContents("테스트 뷰");
        communityBoardDto.setNick("skyLee");

        communityBoardDao.insert(communityBoardDto);
        assertEquals(1,communityBoardDao.count());




        communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
        System.out.println(communityBoardDto.getview_cnt());

        communityBoardDao.increaseViewCnt(communityBoardDto.getno());

        communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
        System.out.println(communityBoardDto.getview_cnt());








    }

    @Test
    public void getTopTen()throws Exception{
        List<CommunityBoardDto>getTopTen = communityBoardDao.getTopTen();
        getTopTen.forEach(System.out::println);


    }


    //사용자 삭제(업데이트) (ur_state를 y->n으로 바꾼다)
    @Test
    public void userChangeState()throws Exception{
        //완전 삭제
        //dto삽입
        //dto삽입후 상태 확인
        //communityBoardDao.userDelete()
        //dto삽입후 상태 확인
        communityBoardDao.deleteAll();
        assertEquals(0,communityBoardDao.count());
        CommunityBoardDto communityBoardDto = new CommunityBoardDto();

            communityBoardDto.setur_id("user123");
            communityBoardDto.setaddr_cd("11010720");
            communityBoardDto.setaddr_no(1);
            communityBoardDto.setcommu_cd("commu_L");
            communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
            communityBoardDto.setTitle("y상태 제목");
            communityBoardDto.setContents("y상태 내용");
            communityBoardDto.setNick("skyLee");
            communityBoardDto.setur_state('y');
            communityBoardDto.setad_state('n');

            communityBoardDao.insert(communityBoardDto);
            assertEquals(1,communityBoardDao.count());

            communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
            assertEquals('y',communityBoardDto.getur_state());

             System.out.println(communityBoardDto.toString());
//            communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
//

            communityBoardDto.setur_state('n');
            communityBoardDao.userChangeState(communityBoardDto);


            communityBoardDto = communityBoardDao.select(communityBoardDto.getno());
            assertEquals('n',communityBoardDto.getur_state());

            System.out.println(communityBoardDto.toString());



        }
    }
