package team.cheese.service.CommunityHeart;

import team.cheese.Domain.CommunityHeart.CommunityHeartDto;

public interface CommunityHeartService {
    public int doLike(CommunityHeartDto communityHeartDto)throws Exception;

    public String countLike(Integer post_no) throws Exception;

    int countLike(Integer post_no, String result) throws Exception;

    public CommunityHeartDto select(Integer like_no)throws Exception;
    public CommunityHeartDto findByUserId(String ur_id,Integer post_no) throws Exception;


}
