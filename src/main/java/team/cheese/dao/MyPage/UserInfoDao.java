package team.cheese.Dao.MyPage;

import team.cheese.Domain.MyPage.UserInfoDTO;

public interface UserInfoDao {
    int count() throws Exception;

    UserInfoDTO select(String ur_id) throws Exception;

    int insert(UserInfoDTO userInfoDTO) throws Exception;

    int update(UserInfoDTO userInfoDTO) throws Exception;

    int incrementViewCnt(String ur_id) throws Exception;

    int incrementCompleteCnt(String ur_id) throws Exception;

    int updateRvCmtCnt(String ur_id, int cnt) throws Exception;

    int incrementRptCnt(String ur_id) throws Exception;

    Double starAverage(String ur_id) throws Exception;

    int delete(String ur_id) throws Exception;

    int deleteAll() throws Exception;

    int stateChange(String ur_id) throws Exception;
}
