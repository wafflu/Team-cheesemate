package team.cheese.dao.CommunityHeart;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.CommunityHeart.CommunityHeartDto;

import java.util.HashMap;
import java.util.Map;

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
    public int countLike(Integer post_no) throws Exception {
        return session.selectOne(namespace + "countLike", post_no);
    }

    @Override
    public String findByUserId(String ur_id,String post_no) throws Exception {
        Map<String,String> map = new HashMap();
        map.put("ur_id", ur_id);
        map.put("post_no", post_no.toString());
        return session.selectOne(namespace + "selectUser", map);
    }


}
