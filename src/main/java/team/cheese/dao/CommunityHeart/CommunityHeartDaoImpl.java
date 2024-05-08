package team.cheese.dao.CommunityHeart;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.Domain.CommunityHeart.CommunityHeartDto;

@Repository
public class CommunityHeartDaoImpl implements CommunityHeartDao{

    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.CommunityHeart.CommunityHeartMapper.";

    @Override
    public int doLike(CommunityHeartDto communityHeartDto) throws Exception {
        return session.insert(namespace + "doLike", communityHeartDto);
    }
//
//    @Override
//    public int insertLike(CommunityHeartDto communityHeartDto) throws Exception {
//        return session.insert(namespace + "insertLike", communityHeartDto);
//    }
//
//    @Override
//    public int deleteLike(CommunityHeartDto communityHeartDto) throws Exception {
//        return session.delete(namespace + "deleteLike", communityHeartDto);
//    }
//
//    @Override
//    public int countAllLikes(Integer post_no) throws Exception {
//        return session.selectOne(namespace + "countAllLikes", post_no);
//    }
//
//    @Override
//    public int countLikeStatus(CommunityHeartDto heartDto) throws Exception {
//        return session.selectOne(namespace + "countLikeStatus", heartDto);
//    }
//
//    @Override
//    public int deleteAll() throws Exception {
//        return session.delete(namespace + "deleteAll");
//    }
//
//    @Override
//    public int count() throws Exception {
//        return session.selectOne(namespace + "count");
//    }
//
//    @Override
//    public char changeHeartState(CommunityHeartDto heartDto) throws Exception {
//        return (char) session.update(namespace +"changeHeartState",heartDto);
//    }
//
    @Override
    public CommunityHeartDto select(Integer like_no) throws Exception {
        return session.selectOne(namespace + "selectLike", like_no);
    }


}
