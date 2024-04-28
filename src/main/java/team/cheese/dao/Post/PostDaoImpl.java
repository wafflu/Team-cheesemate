package team.cheese.dao.Post;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import team.cheese.Domain.Post.PostDto;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.Post.PostMapper.";
    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int insert(PostDto postDto) throws Exception {
        return session.insert(namespace + "insert", postDto);
    }



    @Override
    public List<PostDto> selectAll() throws Exception {
        return session.selectList(namespace +"selectAll");
    }

    @Override
    public PostDto select(Integer sn) throws Exception {
        return session.selectOne(namespace + "select", sn);
    }

    @Override
    public int update(PostDto postDto) throws Exception {
        return session.update(namespace + "update", postDto);
    }

    @Override
    public int delete(Integer sn) throws Exception {
        return session.delete(namespace + "delete", sn);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace +"deleteAll");
    }

    @Override
    public int increaseViewCnt(Integer sn) throws Exception {
        return session.update(namespace+"increaseViewCnt", sn);
    }

    @Override
    public List<PostDto> getTopTen() throws Exception {
        return session.selectList(namespace + "getTopTen");
    }


}
