package team.cheese.dao.Comment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.Comment.CommentDto;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{

    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.Comment.CommentMapper.";

    @Override
    public int count(Integer no) throws Exception {
        return session.selectOne(namespace + "count",no);
    }

    @Override
    public int insert(CommentDto commentDto) throws Exception {
        return session.insert(namespace + "insert", commentDto);
    }

    @Override
    public CommentDto select(CommentDto commentDto) throws Exception {
        return session.selectOne(namespace + "select", commentDto);
    }

    @Override
    public int delete(Integer no) throws Exception {
        return session.delete(namespace + "delete", no);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }



    @Override
    public int update(CommentDto commentDto) throws Exception {
        return session.update(namespace + "update", commentDto);
    }

    @Override
    public int userChangeState(CommentDto commentDto) throws Exception {
        return session.update(namespace + "userChangeState", commentDto);
    }

    @Override
    public int findMaxByPostNo(Integer post_no) throws Exception {
        return session.selectOne(namespace + "findMaxByPostNo", post_no);
    }


    @Override
    public List<CommentDto> selectAll(Integer post_no) throws Exception {
        return session.selectList(namespace + "selectAll", post_no);
    }


}
