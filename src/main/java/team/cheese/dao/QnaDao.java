package team.cheese.dao;

import team.cheese.domain.QnaDto;

import java.util.List;
import java.util.Map;

public interface QnaDao {
    //모든 사용자가 작성한 목록을 표출
    List<QnaDto> selectAll();

    //사용자가 등록한 문의글을 번호로 문의글을 조회한다.
    QnaDto selectByNo(Long no);

    //사용자가 작성한 목록을 표출
    List<QnaDto> select(String ur_id);

    //사용자가 등록한 문의글을 취소(삭제)한다.
    int delete(Long no, String ur_id);

    //사용자가 1:1문의하기를 통해 문의를 등록한다.
    int insert(QnaDto qnaDto);

    //사용자가 등록한 문의글을 수정한다.
    int update(QnaDto qnaDto);

    //관리자가 등록된 모든 qna를 조회한다.
    List<QnaDto> selectAllQnaAdmin();

    //나의 문의 내역 페이징
    List<QnaDto> selectPageByUserId(Map map);

    //나의 문의 내역 글 전체 수 조회
    int countQnasByUserId(String ur_id);
}
