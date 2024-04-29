package team.cheese.Service.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.Dao.MyPage.UserInfoDao;
import team.cheese.Domain.MyPage.UserInfoDTO;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    // count -> getCount
    @Override
    public int getCount() throws Exception {
        return userInfoDao.count();
    }
    // select -> read , incrementViewCnt
    @Override
    public UserInfoDTO read(String ur_id) throws Exception {
        userInfoDao.incrementViewCnt(ur_id);
        return userInfoDao.select(ur_id);
    }
    // insert -> write
    @Override
    public int write(UserInfoDTO userInfoDTO) throws Exception {
        return userInfoDao.insert(userInfoDTO);
    }
    // update -> modify
    @Override
    public int modify(UserInfoDTO userInfoDTO) throws Exception {
        return userInfoDao.update(userInfoDTO);
    }
    // delete -> remove
    @Override
    public int remove(String ur_id) throws Exception {
        return userInfoDao.delete(ur_id);
    }
    // incrementCompleteCnt -> SuccessTx
    @Override
    public int SuccessTx(String ur_id) throws Exception {
        return userInfoDao.incrementCompleteCnt(ur_id);
    }
    // incrementLikeCnt -> ClickedLike
    @Override
    public int ClickedLike(String ur_id) throws Exception {
        return userInfoDao.incrementLikeCnt(ur_id);
    }
    // incrementHateCnt -> ClickedHate
    @Override
    public int ClickedHate(String ur_id) throws Exception {
        return userInfoDao.incrementHateCnt(ur_id);
    }
    // incrementRptCnt -> ReportView
    @Override
    public int ReportView(String ur_id) throws Exception {
        return userInfoDao.incrementRptCnt(ur_id);
    }
}
