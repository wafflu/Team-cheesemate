package team.cheese.Service.MyPage;

import team.cheese.Domain.MyPage.UserInfoDTO;

public interface UserInfoService {
    // count -> getCount
    int getCount() throws Exception;

    // select -> read , incrementViewCnt
    UserInfoDTO read(String ur_id,String session_id) throws Exception;

    UserInfoDTO read(String ur_id) throws Exception;

    // insert -> write
    boolean write(UserInfoDTO userInfoDTO);

    // update -> modify
    boolean modify(UserInfoDTO userInfoDTO);

    // delete -> remove
    boolean remove(String ur_id);

    boolean successTx(String ur_id);

    boolean clickedLike(String ur_id);

    boolean clickedHate(String ur_id);

    boolean reportView(String ur_id);
}
