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
    boolean write(ReviewCommentDTO reviewCommentDTO) throws Exception;

    // delete -> remove
    @Transactional(rollbackFor = Exception.class)
    boolean remove(String sal_id, String buy_id, Integer no) throws Exception;

    // update -> modify
    boolean modify(ReviewCommentDTO reviewCommentDTO);

}
