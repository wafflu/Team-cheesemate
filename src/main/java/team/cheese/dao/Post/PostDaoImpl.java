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
}
