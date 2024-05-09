package team.cheese.service.CommunityHeart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;
import team.cheese.service.CommunityBoard.CommunityBoardService;

import javax.print.attribute.standard.NumberUp;
import java.util.Objects;

@Service
public class CommunityHeartServiceImpl implements CommunityHeartService {

    @Autowired
    CommunityHeartDao communityHeartDao;



    @Autowired
    CommunityBoardService communityBoardService;


    @Override
    public int doLike(CommunityHeartDto communityHeartDto) throws Exception {

        if (communityHeartDto.getUr_id() == null) {
            throw new IllegalArgumentException("세션 정보가 없습니다.");
        }

        if(communityHeartDto.getPost_no() == null) {
            throw new NullPointerException("게시글 번호가 없습니다.");
        }
        System.out.println(communityHeartDto);
        CommunityBoardDto communityBoardDto = communityBoardService.findCommunityBoardById(communityHeartDto.getPost_no());

        if(communityBoardDto.getur_state() == 'n'){
            throw new IllegalArgumentException(communityHeartDto.getPost_no() + "는 접근할 수 없는 게시글입니다.");
        }
        if(communityBoardDto == null){
            throw new NullPointerException("게시글이 존재하지 않습니다");
        }
        return communityHeartDao.doLike(communityHeartDto);
    }

    @Override
    public int countLike(CommunityHeartDto communityHeartDto) throws Exception {
        return 0;
    }

    @Override
    public CommunityHeartDto select(Integer like_no) throws Exception {
        return null;
    }
}
