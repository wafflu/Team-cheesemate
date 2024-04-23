package team.cheese.Dao;

import team.cheese.domain.UserInfoDTO;

public interface UserInfoDao {
    int count() throws Exception;

    UserInfoDTO select(String ur_id) throws Exception;

    int insert(UserInfoDTO userInfoDTO) throws Exception;

    int update(UserInfoDTO userInfoDTO) throws Exception;

    int incrementViewCnt(String ur_id) throws Exception;

    int incrementCompleteCnt(String ur_id) throws Exception;

    int updateRvCmtCnt(String ur_id, int cnt) throws Exception;

    int incrementLikeCnt(String ur_id) throws Exception;

    int incrementHateCnt(String ur_id) throws Exception;

    int incrementRptCnt(String ur_id) throws Exception;

    int delete(String ur_id) throws Exception;

    int deleteAll() throws Exception;
}
