package team.cheese.dao.CommunityBoard;

import team.cheese.domain.CommunityBoard.CommunityBoardDto;

import java.util.List;

public interface CommunityBoardDao {

    int count() throws Exception;

    int insert(CommunityBoardDto communityBoardDto) throws Exception;

    List<CommunityBoardDto> selectAll(Character ur_state) throws Exception;

    CommunityBoardDto select(Integer no) throws Exception;

    int update(CommunityBoardDto communityBoardDto) throws Exception;

    int delete(Integer no) throws Exception;

    int deleteAll() throws Exception;

    int increaseViewCnt(Integer no) throws Exception;

    List<CommunityBoardDto> getTopTen() throws Exception;

    int userChangeState(CommunityBoardDto communityBoardDto) throws Exception;


    //추가
    int totalLikeCount(Integer no) throws Exception;

    //추가
    int updateCommentCnt(Integer no,int cnt) throws Exception;
}
