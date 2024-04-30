package team.cheese.service.CommunityBoard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;


import java.util.List;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {
    @Autowired
    CommunityBoardDao communityBoardDao;

    @Override
    public List<CommunityBoardDto> selectAll() throws Exception {
        return communityBoardDao.selectAll();
    }

    @Override
    public int write(CommunityBoardDto communityBoardDto) throws Exception {
        return communityBoardDao.insert(communityBoardDto);
    }

    @Override
    public int remove(Integer no) throws Exception {
        return communityBoardDao.delete(no);
    }

    @Override
    public int modify(CommunityBoardDto communityBoardDto) throws Exception {
        return communityBoardDao.update(communityBoardDto);
    }

    @Override
    public CommunityBoardDto read(Integer no) throws Exception {
        CommunityBoardDto communityBoardDto = communityBoardDao.select(no);
        communityBoardDao.increaseViewCnt(no);
        return communityBoardDto;
    }

    @Override
    public int getCount() throws Exception {
        return communityBoardDao.count();
    }

    @Override
    public List<CommunityBoardDto> getTopTen() throws Exception {
        return communityBoardDao.getTopTen();
    }
}
//    @Override
//    public int userStateChange(Integer no) throws Exception {
//        return communityBoardDao.userChangeState(no);
//    }
//}
