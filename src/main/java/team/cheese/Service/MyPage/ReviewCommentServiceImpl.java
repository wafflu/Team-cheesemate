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
    public ReviewCommentDTO read(Integer no) throws Exception{
        try {
            ReviewCommentDTO reviewCommentDTO = rvdao.select(no);
            if(reviewCommentDTO==null)
                throw new Exception("select ERR");
            return reviewCommentDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    // selectAll -> getList
    @Override
    public List<ReviewCommentDTO> getList(String sal_id) throws Exception {
        try{
            List<ReviewCommentDTO> list = rvdao.selectAll(sal_id);
            if(list==null)
                throw new Exception("selectAll ERR");
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    // insert -> write
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean write(ReviewCommentDTO reviewCommentDTO) throws Exception {
        try {
            // insert와 update의 리턴값의 합이 2가 아니면 예외발생
            int updateCnt = userInfoDao.updateRvCmtCnt(reviewCommentDTO.getSal_id(), 1);
            int insertCnt = rvdao.insert(reviewCommentDTO);
            if(updateCnt + insertCnt!=2)
                throw new Exception("write ERR");
            // 합이 2가 맞으면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    // delete -> remove
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(String sal_id, String buy_id, Integer no) throws Exception {
        try{
            // delete와 update의 리턴값의 합이 2가 아니면 예외발생
            int updateCnt = userInfoDao.updateRvCmtCnt(sal_id, -1);
            int deleteCnt = rvdao.delete(buy_id, no);
            if(updateCnt + deleteCnt!=2)
                throw new Exception("remove ERR");
            // 합이 2가 맞으면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    // update -> modify
    @Override
    public boolean modify(ReviewCommentDTO reviewCommentDTO) {
        try {
            int rowCnt = rvdao.update(reviewCommentDTO);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("update ERR");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 2. 예외 발생시 false를 반환
            return false;
        }
    }
}
