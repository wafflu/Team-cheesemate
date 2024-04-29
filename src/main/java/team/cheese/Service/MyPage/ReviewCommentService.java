package team.cheese.Service.MyPage;

import org.springframework.transaction.annotation.Transactional;
import team.cheese.Domain.MyPage.ReviewCommentDTO;

import java.util.List;

public interface ReviewCommentService {
    // count -> getCount
    int getCount(String sal_id) throws Exception;

    // select -> read
    ReviewCommentDTO read(Integer no) throws Exception;

    // selectAll -> getList
    List<ReviewCommentDTO> getList(String sal_id) throws Exception;

    // insert -> write
    @Transactional(rollbackFor = Exception.class)
    int write(ReviewCommentDTO reviewCommentDTO) throws Exception;

    // delete -> remove
    @Transactional(rollbackFor = Exception.class)
    int remove(String sal_id, String buy_id, Integer no) throws Exception;

    // update -> modify
    int modify(ReviewCommentDTO reviewCommentDTO) throws Exception;

    // incrementLikeCnt -> ClickedLike
    int ClickedLike(Integer no) throws Exception;
}
