package team.cheese.Dao.MyPage;

import team.cheese.Domain.MyPage.ReviewCommentDTO;

import java.util.List;

public interface ReviewCommentDao {
    int count(String sal_id) throws Exception;

    ReviewCommentDTO select(Integer no) throws Exception;

    List<ReviewCommentDTO> selectAll(String sal_id) throws Exception;

    int insert(ReviewCommentDTO reviewCommentDTO) throws Exception;

    int update(ReviewCommentDTO reviewCommentDTO) throws Exception;

    int incrementLikeCnt(Integer no) throws Exception;

    int stateChange(String buy_id) throws Exception;

    int delete(String buy_id, Integer no) throws Exception;

    int deleteAll(String sal_id) throws Exception;
}
