package team.cheese.service.CommunityHeart;

import team.cheese.domain.CommunityHeart.CommunityHeartDto;

public interface CommunityHeartService {

    //게시글 좋아요, 취소(상태 같이 넣음)
    public int doLike(CommunityHeartDto communityHeartDto)throws Exception;

    //게시글당 총 좋아요
    public String countLike(Integer post_no) throws Exception;









}
