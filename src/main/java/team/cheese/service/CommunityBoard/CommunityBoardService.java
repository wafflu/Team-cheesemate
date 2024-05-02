package team.cheese.service.CommunityBoard;

import team.cheese.Domain.CommunityBoard.CommunityBoardDto;

import java.util.List;

public interface CommunityBoardService {
     List<CommunityBoardDto>readAll()throws Exception;




    public int write(CommunityBoardDto communityBoardDto)throws Exception;

    public int remove(Integer no)throws Exception;

    public int removeAll()throws Exception;

    public int modify(CommunityBoardDto communityBoardDto)throws Exception;

    public CommunityBoardDto read(Integer no)throws Exception;

    public int getCount()throws Exception;

    List<CommunityBoardDto> getTopTen()throws Exception;

    public int userStateChange(CommunityBoardDto communityBoardDto)throws Exception;
}
