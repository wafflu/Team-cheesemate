package team.cheese.service.CommunityHeart;

import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;

public interface CommunityHeartService {
    public int doLike(CommunityHeartDto communityHeartDto)throws Exception;

    public int countLike(CommunityHeartDto communityHeartDto) throws Exception;

    public CommunityHeartDto select(Integer like_no)throws Exception;

}
