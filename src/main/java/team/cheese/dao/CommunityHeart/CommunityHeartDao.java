package team.cheese.dao.CommunityHeart;

import team.cheese.domain.CommunityHeart.CommunityHeartDto;
;

public interface CommunityHeartDao {

    //게시글 좋아요 취소
    int doLike(CommunityHeartDto communityHeartDto)throws Exception;

    //하트 셀랙
    CommunityHeartDto select(Integer like_no)throws Exception;

    //게시글 당 총 하트
    int countLike(Integer post_no)throws Exception;

    String findByUserId(String ur_id,String post_no)throws Exception;
}
