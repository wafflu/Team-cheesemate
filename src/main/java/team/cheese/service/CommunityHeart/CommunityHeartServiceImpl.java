package team.cheese.service.CommunityHeart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.domain.CommunityHeart.CommunityHeartDto;
import team.cheese.dao.CommunityHeart.CommunityHeartDao;
import team.cheese.service.CommunityBoard.CommunityBoardService;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommunityHeartServiceImpl implements CommunityHeartService {

    @Autowired
    CommunityHeartDao communityHeartDao;


    @Autowired
    CommunityBoardService communityBoardService;


    @Override
    public int doLike(CommunityHeartDto communityHeartDto) throws Exception {
        if (communityHeartDto.getUr_id() == null || communityHeartDto.getPost_no() == null) {
            throw new IllegalArgumentException("필수 정보가 없습니다");
        }

        CommunityBoardDto communityBoardDto = communityBoardService.findCommunityBoardById(communityHeartDto.getPost_no());
        if (communityBoardDto == null) {
            throw new NullPointerException("게시글이 존재하지 않습니다");
        }

        if (communityBoardDto.getur_state() == 'n') {
            throw new IllegalArgumentException(communityHeartDto.getPost_no() + "는 접근할 수 없는 게시글입니다.");
        }

        communityHeartDao.doLike(communityHeartDto);  // 좋아요 처리
        return communityHeartDao.countLike(communityHeartDto.getPost_no());  // 현재 좋아요 수 반환
    }






    @Override
    public String countLike(Integer post_no) throws Exception {
        String result = String.valueOf(communityHeartDao.countLike(post_no));
        return result; // 게시글 번호에 대한 좋아요 수 집계
    }

    @Override
    public int countLike(Integer post_no, String result) throws Exception {
        return 0;
    }


    @Override
    public CommunityHeartDto select(Integer like_no) throws Exception {
        return null;
    }

    @Override
    public CommunityHeartDto findByUserId(String ur_id,Integer post_no) throws Exception {
        try{
            Map<String,String> map = new HashMap<>();
            map.put("ur_id", ur_id);
            map.put("post_no", String.valueOf(post_no));
            communityHeartDao.findByUserId(ur_id, String.valueOf(post_no));

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

}
