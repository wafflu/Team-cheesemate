//package team.cheese.service.CommunityHeart;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
//import team.cheese.Domain.CommunityHeart.CommunityHeartDto;
//import team.cheese.dao.CommunityHeart.CommunityHeartDao;
//import team.cheese.service.CommunityBoard.CommunityBoardService;
//
//import java.util.Objects;
//
//@Service
//public class CommunityHeartServiceImpl implements CommunityHeartService {
//
//    @Autowired
//    CommunityHeartDao communityHeartDao;
//
//
//
//    @Autowired
//    CommunityBoardService communityBoardService;
//
//
//    public char getPostState(Integer post_no) throws Exception {
//        CommunityBoardDto communityBoardDto = communityBoardService.findCommunityBoardById(post_no);
//        return communityBoardDto != null ? communityBoardDto.getur_state(): 'n';
//    }
//
//    //좋아요
//    @Override
//    public int doLike(CommunityHeartDto communityHeartDto) throws Exception {
//        //1. ur_id와 post_no가 있으면 좋아요 가능
//        //2. 예외
//            //2.1 ur_id가 없을때
//            //2.2 post_no가 없을때
//
//        if(communityHeartDto.getUr_id()==null || Objects.equals(communityHeartDto.getUr_id(), "")) {
//            throw new IllegalArgumentException("id가 존재하지않거나 로그인 되지않았습니다.");
//        }
//        if(communityHeartDto.getPost_no() == null){
//            throw new NullPointerException("게시물 " + communityHeartDto.getPost_no() + "가 존재하지 않습니다");
//        }
//        return communityHeartDao.insertLike(communityHeartDto);
//    }
//
//    //좋아요 해제
//    @Override
//    public int removeLike(CommunityHeartDto communityHeartDto) throws Exception {
//        //1. ur_id와 post_no가 일치할때만 삭제
//        //2. 예외
//            //2.1 기존에 누르지않았는데도 삭제되는 경우
//            //2.2 ur_id가 서로 같지 않을 경우에도 삭제되는 경우
//                //doLike
//            //2.3 ur_id혹은 post_no가 존재하지않을 경우(null)
//            //2.4 ur_id혹은 post_no의 상태가 n일 경우
//
//
//        if(communityHeartDao.countLikeStatus(communityHeartDto) == 0){
//            communityHeartDao.insertLike(communityHeartDto);
//        }
//        if(communityHeartDto.getUr_id()==null || Objects.equals(communityHeartDto.getUr_id(), "")){
//            throw new IllegalArgumentException("id가 존재하지않거나 로그인되지않았습니다");
//        }
//        if(communityHeartDto.getPost_no() == null){
//            throw new NullPointerException("게시물번호가 존재하지않습니다.");
//        }
//
//        if(getPostState(communityHeartDto.getPost_no()) == 'n'){
//            throw new IllegalArgumentException("게시글이 이미 삭제되었습니다.");
//        }
//        return communityHeartDao.deleteLike(communityHeartDto);
//    }
//
//
//    //게시글 당 총 좋아요 개수
//    @Override
//    public int countAllLike(Integer post_no) throws Exception {
//
//        return communityHeartDao.countAllLikes(post_no);
//    }
//
//
//    //좋아요 했는지에 대한 상태(한 사용자는 한 게시글 당 좋아요 하난만 가능)
//    @Override
//    public int LikeStatus(CommunityHeartDto communityHeartDto) throws Exception {
//        if(communityHeartDto.getUr_id()==null || Objects.equals(communityHeartDto.getUr_id(), "")){
//            communityHeartDao.insertLike(communityHeartDto);
//        }
//        if(communityHeartDto.getUr_id() != null){
//            communityHeartDao.deleteLike(communityHeartDto);
//        }
//        return communityHeartDao.countLikeStatus(communityHeartDto);
//    }
//}
