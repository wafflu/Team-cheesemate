package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.QnaCategoryDao;
import team.cheese.dao.QnaDao;
import team.cheese.domain.QnaCategoryDto;
import team.cheese.domain.QnaDto;

import java.util.List;

@Service
public class QnaService {

    @Autowired
    private QnaCategoryDao qnaCategoryDao;
    @Autowired
    private QnaDao qnaDao;

//    대분류 카테고리 조회
    public List<QnaCategoryDto> getMajorCategories() throws Exception {
        return qnaCategoryDao.selectMajorCategory();
    }
//    중분류 카테고리 조회
    public List<QnaCategoryDto> getSubCategories(Integer majorCategoryId) throws Exception {
        return qnaCategoryDao.selectSubCategory(majorCategoryId);
    }
//    1:1 문의내역을 등록
    public int write(QnaDto qnaDto) throws Exception {
        return qnaDao.insert(qnaDto);
    }
//    나의 문의 내역 목록 조회
    public List<QnaDto> select(String ur_id) throws Exception {
        return qnaDao.select(ur_id);
    }
//    나의 문의 내역 읽기
    public QnaDto read(Long no) throws Exception {
        QnaDto qnaDto = qnaDao.selectByNo(no);
        return qnaDto;
    }
//    나의 문의 내역 삭제
    public int remove(Long no, String ur_id) throws Exception {
        return qnaDao.delete(no, ur_id);
    }
//    나의 문의 내역 수정
    public int modify(QnaDto qnaDto) throws Exception {
        return qnaDao.update(qnaDto);
    }

}
