package team.cheese.service.MyPage;

import org.springframework.http.ResponseEntity;
import team.cheese.domain.MyPage.UserInfoDTO;

public interface UserInfoService {
    // count -> getCount
    int getCount() throws Exception;

    // select -> read , incrementViewCnt
    UserInfoDTO read(String ur_id,String session_id) throws Exception;

    UserInfoDTO read(String ur_id) throws Exception;

    // insert -> write
    void write(UserInfoDTO userInfoDTO) throws Exception;

    // update -> modify
    void modify(UserInfoDTO userInfoDTO) throws Exception;

    // delete -> remove
    void remove(String ur_id) throws Exception;

    void successTx(String ur_id) throws Exception;

    void reportView(String ur_id) throws Exception;

    Double starRating(String ur_id) throws Exception;

    ResponseEntity<String> profileimgchange (String imgname, String userid) throws Exception;
}
