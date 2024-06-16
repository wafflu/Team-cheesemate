package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.QnaCategoryDao;
import team.cheese.dao.QnaDao;
import team.cheese.domain.QnaCategoryDto;
import team.cheese.domain.QnaDto;

import java.util.*;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    private QnaCategoryDao qnaCategoryDao;

    @Autowired
    private QnaDao qnaDao;

    //
    private static final Set<Long> VALID_CATEGORY_CODES = new HashSet<>(Arrays.asList(
            1001L, 1002L, 2001L, 2002L, 3001L, 3002L, 3003L, 4001L, 4002L, 4003L, 4004L, 4005L, 5001L
    ));

    // 대분류 카테고리 조회
    @Override
    public List<QnaCategoryDto> getMajorCategories() throws Exception {
        return qnaCategoryDao.selectMajorCategory();
    }

    // 중분류 카테고리 조회
    @Override
    public List<QnaCategoryDto> getSubCategories(long majorCategoryId) throws Exception {
        return qnaCategoryDao.selectSubCategory(majorCategoryId);
    }

    // 1:1 문의내역을 등록
    @Override
    public int write(QnaDto qnaDto) throws Exception {
            if(qnaDto.getTitle() ==null || qnaDto.getTitle().trim().equals("")){
                throw new IllegalArgumentException("제목이 비어있습니다.");
            }
            if(qnaDto.getContents() ==null || qnaDto.getContents().trim().equals("")){
                throw new IllegalArgumentException("내용이 비어있습니다.");
            }
            if(!VALID_CATEGORY_CODES.contains(qnaDto.getQue_i_cd())) {
                throw new IllegalArgumentException("유형과 상세유형이 올바르지 않습니다.");
        }
            return qnaDao.insert(qnaDto);
    }

    // 나의 문의 내역 목록 조회
    @Override
    public List<QnaDto> select(String ur_id)  throws Exception {
        try{
            return qnaDao.select(ur_id);
        } catch (Exception e) {
            throw new RuntimeException("1:1 문의내역 목록 조회 에러", e);
        }
    }

    // 나의 문의 내역 읽기
    @Override
    public QnaDto read(long no) throws Exception {
            QnaDto qnaDto = qnaDao.selectByNo(no);
            if(qnaDto ==null){
                System.out.println("해당 게시물 없음");
                throw new RuntimeException("나의 문의 내역 읽기 에러");}
            return qnaDto;
    }

    // 나의 문의 내역 삭제
    @Override
    public int remove(long no, String ur_id) throws Exception {
        int cnt = qnaDao.delete(no, ur_id);
        if (cnt == 0) {
            throw new RuntimeException("나의 문의 내역 삭제 에러: 해당 문의 내역이 존재하지 않거나 삭제할 권한이 없습니다.");
        }
        return cnt;
    }

    // 나의 문의 내역 수정

    @Override
    public int modify(QnaDto qnaDto) throws Exception {
        if (qnaDto.getTitle() == null || qnaDto.getTitle().trim().equals("")) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
        if (qnaDto.getContents() == null || qnaDto.getContents().trim().length() < 20) {
            throw new IllegalArgumentException("내용이 20글자 이상이어야 합니다.");
        }
        return qnaDao.update(qnaDto);
    }

    // 나의 문의 내역 페이징
    @Override
    public List<QnaDto> selectPageByUserId(Map<String, Object> params) throws Exception{
        try{
            return qnaDao.selectPageByUserId(params);
        } catch (Exception e) {
            throw new RuntimeException("나의 문의 내역 페이징 에러", e);
        }
    }

    // 나의 문의 내역 전체 카운트
    @Override
    public int countQnasByUserId(String ur_id)  throws Exception {
            return qnaDao.countQnasByUserId(ur_id);
    }
}
