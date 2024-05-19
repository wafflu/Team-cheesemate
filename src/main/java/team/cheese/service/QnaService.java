package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.QnaCategoryDao;
import team.cheese.dao.QnaDao;
import team.cheese.domain.QnaCategoryDto;
import team.cheese.domain.QnaDto;

import java.util.List;
import java.util.Map;

@Service
public class QnaService {

    @Autowired
    private QnaCategoryDao qnaCategoryDao;

    @Autowired
    private QnaDao qnaDao;

    // 대분류 카테고리 조회
    public List<QnaCategoryDto> getMajorCategories() {
        try {
            return qnaCategoryDao.selectMajorCategory();
        } catch (Exception e) {
            throw new RuntimeException("대분류 카데고리 에러", e);
        }
    }

    // 중분류 카테고리 조회
    public List<QnaCategoryDto> getSubCategories(long majorCategoryId) {
        try {
            return qnaCategoryDao.selectSubCategory(majorCategoryId);
        } catch (Exception e) {
            throw new RuntimeException("중분류 카데고리 에러", e);
        }
    }

    // 1:1 문의내역을 등록
    public int write(QnaDto qnaDto)  {
        try{
            return qnaDao.insert(qnaDto);
        } catch (Exception e) {
            throw new RuntimeException("1:1 문의내역을 등록 에러", e);
        }
    }

    // 나의 문의 내역 목록 조회
    public List<QnaDto> select(String ur_id)  {
        try{
            return qnaDao.select(ur_id);
        } catch (Exception e) {
            throw new RuntimeException("1:1 문의내역 목록 조회 에러", e);
        }
    }

    // 나의 문의 내역 읽기
    public QnaDto read(long no) { // 변경된 부분
        try{
            QnaDto qnaDto = qnaDao.selectByNo(no);
            return qnaDto;
        } catch (Exception e) {
            throw new RuntimeException("나의 문의 내역 읽기 에러", e);
        }
    }

    // 나의 문의 내역 삭제
    public int remove(long no, String ur_id) {
        try{
            return qnaDao.delete(no, ur_id);
        } catch (Exception e) {
            throw new RuntimeException("나의 문의 내역 삭제 에러", e);
        }
    }

    // 나의 문의 내역 수정
    public int modify(QnaDto qnaDto) {
        try{
            return qnaDao.update(qnaDto);
        } catch (Exception e) {
            throw new RuntimeException("나의 문의 내역 수정 에러", e);
        }
    }

    // 나의 문의 내역 페이징
    public List<QnaDto> selectPageByUserId(Map<String, Object> params) {
        try{
            return qnaDao.selectPageByUserId(params);
        } catch (Exception e) {
            throw new RuntimeException("나의 문의 내역 페이징 에러", e);
        }
    }

    // 나의 문의 내역 전체 카운트
    public int countQnasByUserId(String ur_id)  {
        try{
            return qnaDao.countQnasByUserId(ur_id);
        } catch (Exception e) {
            throw new RuntimeException("나의 문의 내역 전체 카운트 에러", e);
        }
    }
}
