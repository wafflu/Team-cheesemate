package team.cheese.Service.MyPage;

import team.cheese.Domain.MyPage.UserInfoDTO;

public interface UserInfoService {
    // count -> getCount
    int getCount() throws Exception;

    // select -> read , incrementViewCnt
    UserInfoDTO read(String ur_id) throws Exception;

    // insert -> write
    int write(UserInfoDTO userInfoDTO) throws Exception;

    // update -> modify
    int modify(UserInfoDTO userInfoDTO) throws Exception;

    // delete -> remove
    int remove(String ur_id) throws Exception;

    // incrementCompleteCnt -> SuccessTx
    int SuccessTx(String ur_id) throws Exception;

    // incrementLikeCnt -> ClickedLike
    int ClickedLike(String ur_id) throws Exception;

    // incrementHateCnt -> ClickedHate
    int ClickedHate(String ur_id) throws Exception;

    // incrementRptCnt -> ReportView
    int ReportView(String ur_id) throws Exception;
}
