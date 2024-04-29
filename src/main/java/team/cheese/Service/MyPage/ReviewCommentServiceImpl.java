package team.cheese.Service.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.Dao.MyPage.ReviewCommentDao;
import team.cheese.Dao.MyPage.UserInfoDao;
import team.cheese.Domain.MyPage.ReviewCommentDTO;

import java.util.List;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {
    ReviewCommentDao rvdao;
    UserInfoDao userInfoDao;

    @Autowired
    public ReviewCommentServiceImpl( ReviewCommentDao rvdao,UserInfoDao userInfoDao) {
        this.rvdao = rvdao;
        this.userInfoDao = userInfoDao;
    }


    // count -> getCount
    @Override
    public int getCount(String sal_id) throws Exception {
        return rvdao.count(sal_id);
    }
    // select -> read
    @Override
    public ReviewCommentDTO read(Integer no) throws Exception {
        return rvdao.select(no);
    }
    // selectAll -> getList
    @Override
    public List<ReviewCommentDTO> getList(String sal_id) throws Exception {
        return rvdao.selectAll(sal_id);
    }
    // insert -> write
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(ReviewCommentDTO reviewCommentDTO) throws Exception {
        userInfoDao.updateRvCmtCnt(reviewCommentDTO.getSal_id(), 1);
//        throw  new Exception("test");
        return rvdao.insert(reviewCommentDTO);
    }
    // delete -> remove
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(String sal_id, String buy_id, Integer no) throws Exception {
         userInfoDao.updateRvCmtCnt(sal_id, -1);
//                throw  new Exception("test");
         return  rvdao.delete(buy_id, no);
    }
    // update -> modify
    @Override
    public int modify(ReviewCommentDTO reviewCommentDTO) throws Exception {
        return rvdao.update(reviewCommentDTO);
    }
    // incrementLikeCnt -> ClickedLike
    @Override
    public int ClickedLike(Integer no) throws Exception {
        return rvdao.incrementLikeCnt(no);
    }

}
