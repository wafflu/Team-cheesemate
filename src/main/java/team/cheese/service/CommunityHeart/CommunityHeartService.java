package team.cheese.service.CommunityHeart;

import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;

public interface CommunityHeartService {
    public int doLike(CommunityHeartDto communityHeartDto)throws Exception;
    public int removeLike(CommunityHeartDto communityHeartDto) throws Exception;
    public int countAllLike(Integer post_no) throws Exception;
    public int LikeStatus(CommunityHeartDto communityHeartDto) throws Exception;

}
