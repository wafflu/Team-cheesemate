package team.cheese.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.cheese.domain.QnaDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class QnaDaoTest {

    @Autowired
    QnaDao qnaDao;

    //1.조회테스트
    //  1-1.모든 항목을 조회하여 row수를 비교한다.
    @Test
    public void selectAll() {
        assertTrue("QnaDao is null",qnaDao !=null);

        List<QnaDto> list = qnaDao.selectAll();

        int cnt = list.size();
        assertTrue(cnt == 13);
    }

    //  1-2.존재하는 row를 no를 검색해서 NotNull여부를 확인한다.
    @Test
    public void selectByNo(){
        assertTrue("QnaDao is null",qnaDao !=null);

        QnaDto qnaDto;
        qnaDto = qnaDao.selectByNo(11L);
        System.out.println(qnaDto);
        assertNotNull(qnaDto);
    }
    //  1-3.존재하지 않는 row를 no를 검색해서 Null여부를 확인한다.
    @Test
    public void selectEmptyByNo(){
        assertTrue("QnaDao is null",qnaDao !=null);

        QnaDto qnaDto;
        qnaDto = qnaDao.selectByNo(99999L);
        System.out.println(qnaDto);
        assertNull(qnaDto);
    }

    //2등록 테스트
    //  2-1.insert 실행 시 1개의 row가 추가되는 것을 확인한다.
    @Test
    public void insert(){
        assertTrue("QnaDao is null",qnaDao !=null);

        QnaDto qnaDto = new QnaDto();
        qnaDto.setUr_id("user123");
        qnaDto.setQ_s_cd("Q001U");
        qnaDto.setQue_i_cd(2002L);
        qnaDto.setTitle("TDD 문의");
        qnaDto.setContents("TDD TEST입니다.");
        qnaDto.setFirst_id("user123");

        System.out.println(qnaDto);
        int cnt = qnaDao.insert(qnaDto);
        assertTrue(cnt == 1);
    }

    //  2-2.insert 실행 시 NotNull로 들어오면 안되는 값에 null을 넣어 insert한다.
    @Test
    public void insertNull(){
        assertTrue("QnaDao is null",qnaDao !=null);

        QnaDto qnaDto = new QnaDto();
        qnaDto.setUr_id(null);
        qnaDto.setQ_s_cd(null);
        qnaDto.setQue_i_cd(null);
        qnaDto.setTitle(null);
        qnaDto.setContents(null);
        qnaDto.setFirst_id(null);

        System.out.println(qnaDto);
        int cnt = qnaDao.insert(qnaDto);
        assertTrue(cnt == 0);
    }

    //3수정 테스트
    //  3-1.문의글을 수정 후 완료하면 성공
    @Test
    public void update(){
        assertTrue("QnaDao is null",qnaDao !=null);

        QnaDto qnaDto = new QnaDto();

        qnaDto.setUr_id("user123");
        qnaDto.setQ_s_cd("Q001U");
        qnaDto.setQue_i_cd(2002L);
        qnaDto.setTitle("제 어머니가 사기를 당했습니다.");
        qnaDto.setContents("그제 거래 중 사기를 당했습니다. 빈 상자가 오더라구요");
        qnaDto.setLast_id("user123");
        qnaDto.setNo(11L);
        System.out.println(qnaDto);

        int cnt = qnaDao.update(qnaDto);

        assertTrue("insert fail",cnt == 1);
    }

    //  3-2.존재하지 않는 문의 번호가 들어왔을때 insert 테스트
    @Test
    public void updateNullNo() {
        QnaDto qnaDto = new QnaDto();
        qnaDto.setNo(99999L); // 존재하지 않는 문의 번호
        qnaDto.setUr_id("user123");
        qnaDto.setQ_s_cd("Q001U");
        qnaDto.setQue_i_cd(2002L);
        qnaDto.setTitle("제 어머니가 사기를 당했습니다.");
        qnaDto.setContents("그제 거래 중 사기를 당했습니다. 빈 상자가 오더라구요");
        qnaDto.setLast_id("user123");

        int cnt = qnaDao.update(qnaDto);
        assertTrue(cnt == 0); //존재하지 않는 문의 번호이므로 0을 반환해야함
    }

    //  3-3.update 후 수정된 시간이 update되었는지 (null이 아닌지)
    @Test
    public void updateLastDateCheck() {
        QnaDto qnaDto = new QnaDto();
        qnaDto.setNo(11L);
        qnaDto.setUr_id("user123");
        qnaDto.setQ_s_cd("Q001U");
        qnaDto.setQue_i_cd(2002L);
        qnaDto.setTitle("제 어머니가 사기를 당했습니다.");
        qnaDto.setContents("그제 거래 중 사기를 당했습니다. 빈 상자가 오더라구요");
        qnaDto.setLast_id("user123");

        qnaDao.update(qnaDto);
        QnaDto updatedQnaDto = qnaDao.selectByNo(qnaDto.getNo());
        System.out.println(updatedQnaDto.getLast_date());
        assertNotNull(updatedQnaDto.getLast_date());
    }

    //취소(삭제) 테스트
    //  취소가 정상적으로 되면 성공하는 테스트
    @Test
    public void delete(){
        assertTrue("QnaDao is null",qnaDao !=null);

        Long no = 54L;
        String ur_id = "user123";

        int cnt = qnaDao.delete(no,ur_id);

        assertTrue("delete fail",cnt == 1);
    }

    //  존재하지 않는 문의 번호가 들어왔을때, 삭제가 안될시 성공
    @Test
    public void deleteNullNo() {
        assertTrue("QnaDao is null",qnaDao !=null);

        Long no = 9999L;
        String ur_id = "user123";

        int cnt = qnaDao.delete(no,ur_id);

        assertTrue("delete fail",cnt == 0);
    }
}