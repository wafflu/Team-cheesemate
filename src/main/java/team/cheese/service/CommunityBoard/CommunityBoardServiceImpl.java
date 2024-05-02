package team.cheese.service.CommunityBoard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;


import java.util.List;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {
    @Autowired
    CommunityBoardDao communityBoardDao;

    @Override
    public List<CommunityBoardDto> readAll() throws Exception {
        Character fixedUrState = 'y';
        return communityBoardDao.selectAll(fixedUrState);
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
    public int removeAll() throws Exception {
        return communityBoardDao.deleteAll();
    }

    @Override
    public int modify(CommunityBoardDto communityBoardDto) throws Exception {
        return communityBoardDao.update(communityBoardDto);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
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

    @Override
    public int userStateChange(CommunityBoardDto communityBoardDto) throws Exception {
        //현재 상태를 읽어온다.
        CommunityBoardDto existingDto = communityBoardDao.select(communityBoardDto.getno());
        if(existingDto == null) {
           throw new Exception();

        }
        //반대 상태로 바꾼다.
        String current_state = String.valueOf(existingDto.getur_state());
        String new_state = current_state.equals("y") ? "n":"y";
        Character state = new_state.charAt(0);
        existingDto.setur_state(state);

        int updateResult = communityBoardDao.userChangeState(existingDto);

        if(updateResult != 1) {
            throw new Exception();
        }
        return updateResult;
    }
}
