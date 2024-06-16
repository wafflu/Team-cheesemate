package team.cheese.service.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.dao.MyPage.ReviewCommentDao;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.MyPage.ReviewCommentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {
    ReviewCommentDao rvdao;
    UserInfoDao userInfoDao;
    SaleDao saleDao;
    @Autowired
    public ReviewCommentServiceImpl( ReviewCommentDao rvdao,UserInfoDao userInfoDao,SaleDao saleDao) {
        this.rvdao = rvdao;
        this.userInfoDao = userInfoDao;
        this.saleDao = saleDao;
    }

    // count -> getCount
    @Override
    public int getCount(String sal_id) throws Exception {
        return rvdao.count(sal_id);
    }

    @Override
    public int getPageCount(String sal_id, String session_id) throws Exception {
        if(sal_id==null)
            sal_id = session_id;
        return rvdao.count(sal_id);
    }

    // select -> read
    @Override
    public ReviewCommentDTO read(Integer no) throws Exception{
            ReviewCommentDTO reviewCommentDTO = rvdao.select(no);
            if(reviewCommentDTO==null)
                throw new Exception("후기글 조회 중 오류가 발생했습니다");
            return reviewCommentDTO;
    }
    // selectAll -> getList
    @Override
    public List<ReviewCommentDTO> getList(String sal_id) throws Exception {
            List<ReviewCommentDTO> list = rvdao.selectAll(sal_id);
            if(list==null)
                throw new Exception("후기글 목록 조회 중 오류가 발생했습니다");
            return list;
    }

    @Override
    public List<ReviewCommentDTO> getPage(String sal_id, Integer page, Integer pageSize) throws Exception {
        Map map = new HashMap();
        map.put("sal_id",sal_id);
        map.put("offset",(page-1)*pageSize);
        map.put("pageSize",pageSize);
        List<ReviewCommentDTO> list = rvdao.selectPage(map);
        if(list==null)
            throw new Exception("후기글 목록 조회 중 오류가 발생했습니다");
        return list;
    }

    // insert -> write
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(ReviewCommentDTO reviewCommentDTO,Long no) throws Exception {
            int updateCnt = userInfoDao.updateRvCmtCnt(reviewCommentDTO.getSal_id(), 1);
            int updateCnt2 = saleDao.reviewState(no);
            int insertCnt = rvdao.insert(reviewCommentDTO);
            if(updateCnt + updateCnt2+ insertCnt != 3)
                throw new Exception("후기글 작성 중 오류가 발생했습니다");
    }
    // delete -> remove
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String sal_id, String buy_id, Integer no) throws Exception {
        // delete와 update의 리턴값의 합이 2가 아니면 예외발생
        int updateCnt = userInfoDao.updateRvCmtCnt(sal_id, -1);
        int deleteCnt = rvdao.delete(buy_id, no);
        if (updateCnt + deleteCnt != 2)
            throw new Exception("후기글 삭제 중 오류가 발생했습니다");
    }
    // update -> modify
    @Override
    public void modify(ReviewCommentDTO reviewCommentDTO) throws Exception {
            int rowCnt = rvdao.update(reviewCommentDTO);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("후기글 수정 중 오류가 발생헀습니다");
    }
}
