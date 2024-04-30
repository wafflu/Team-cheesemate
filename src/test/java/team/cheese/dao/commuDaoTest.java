package team.cheese.dao;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.Commu.CommuDao;
import team.cheese.Domain.Commu.CommuDto;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class commuDaoTest {
    @Autowired
    CommuDao commuDao;


    @Test
    public void testCount() throws Exception{
        System.out.println(commuDao.count());
    }




    @Test
    public void testDelete()throws Exception{
        System.out.println(commuDao.count());
        commuDao.delete("테스트 수정 카테고리2","asdf");
        System.out.println(commuDao.count());

    }


    @Test
    public void testInsert()throws Exception{
        CommuDto commuDto = new CommuDto();
        commuDto.setcommu_cd("commu_T");
        commuDto.setName("테스트 카테고리");
        commuDto.setfirst_id("asdf");

        int result = commuDao.insert(commuDto);
        assertEquals(1,result);
    }

    @Test
    public void testSelect()throws Exception{


        CommuDto commuDto = new CommuDto();


        commuDto.setcommu_cd("commu_T");
        commuDto.setName("테스트 카테고리");
        commuDto.setfirst_id("asdf");
        commuDao.insert(commuDto);

        commuDao.select("commu_T",commuDto);
        System.out.println(commuDto.toString());
    }

    @Test
    public void testUpdate()throws Exception{
        int deleteResult = commuDao.delete("테스트 카테고리" ,"asdf");
        assertEquals(1,deleteResult);

        CommuDto commuDto = new CommuDto();
        commuDto.setcommu_cd("commu_T");
        commuDto.setName("테스트 카테고리");
        commuDto.setfirst_id("asdf");
        commuDao.insert(commuDto);



        commuDto.setName("테스트 수정 카테고리");
        commuDto.setlast_id("admin001");

        commuDao.update(commuDto);

        System.out.println(commuDto.toString());
//        assertEquals("테스트 수정 카테고리", commuDto.getName());

    }

//    @Test
//    public void testUpdate2()throws Exception{
//        int deleteResult = commuDao.delete("테스트 수정 카테고리fff" ,"asdf");
//        assertEquals(1,deleteResult);
//
//        CommuDto commuDto = new CommuDto();
//        commuDto.setcommu_cd("commu_t");
//        commuDto.setName("테스트 수정 카테고리");
//        commuDto.setfirst_id("asdf");
//        commuDao.insert(commuDto);
//
//
//        commuDto.setcommu_cd("commu_T");
//        commuDto.setName("테스트 수정 카테고리2");
//        commuDto.setlast_id("admin001");
//
//        commuDao.update(commuDto);
//
//        System.out.println(commuDto.toString());
////        assertEquals("테스트 수정 카테고리", commuDto.getName());

    }
//}
