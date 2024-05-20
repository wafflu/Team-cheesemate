package team.cheese.service.MyPage;

import org.springframework.transaction.annotation.Transactional;
import team.cheese.domain.MyPage.ReviewCommentDTO;

import java.util.List;

public interface ReviewCommentService {
    // count -> getCount
    int getCount(String sal_id) throws Exception;

    int getPageCount(String sal_id,String session_id) throws Exception;

    // select -> read
    ReviewCommentDTO read(Integer no) throws Exception;

    // selectAll -> getList
    List<ReviewCommentDTO> getList(String sal_id) throws Exception;

    List<ReviewCommentDTO> getPage(String sal_id,Integer page,Integer pageSize) throws Exception;

    // insert -> write
    @Transactional(rollbackFor = Exception.class)
    void write(ReviewCommentDTO reviewCommentDTO,Long no) throws Exception;

    // delete -> remove
    @Transactional(rollbackFor = Exception.class)
    void remove(String sal_id, String buy_id, Integer no) throws Exception;

    // update -> modify
    void modify(ReviewCommentDTO reviewCommentDTO) throws Exception;

}
