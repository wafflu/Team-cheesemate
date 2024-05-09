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

    @Override
    public CommunityHeartDto select(Integer like_no) throws Exception {
        return session.selectOne(namespace + "selectLike", like_no);
    }

    @Override
    public int countLike(CommunityHeartDto communityHeartDto) throws Exception {
        return session.selectOne(namespace + "countLike", communityHeartDto);
    }


}
