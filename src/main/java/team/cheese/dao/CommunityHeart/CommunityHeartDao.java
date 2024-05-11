package team.cheese.dao.CommunityHeart;

import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
;

public interface CommunityHeartDao {
    int doLike(CommunityHeartDto communityHeartDto)throws Exception;

    CommunityHeartDto select(Integer like_no)throws Exception;


    int countLike(Integer post_no)throws Exception;

    String findByUserId(String ur_id,String post_no)throws Exception;
}
