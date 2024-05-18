package team.cheese.dao.MyPage;

import team.cheese.domain.MyPage.UserInfoDTO;

import java.util.HashMap;

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

     int updateProfile(HashMap map) throws Exception;
}

