package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.TagDto;

@Repository
public class TagDaoImpl implements TagDao{
    @Autowired
    private SqlSession session;

    private static String namespace = "team.cheese.dao.TagMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int deleteAll() throws Exception {

        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int insert(TagDto tagDto) throws Exception {
        return session.insert(namespace + "insert", tagDto);
    }

    @Override
    public TagDto selectTagContents(String contents) throws Exception {
        return session.selectOne(namespace + "selectTagContents", contents);
    }

    @Override
    public int update(TagDto tagDto) throws Exception {
        return session.update(namespace + "update", tagDto);
    }

    @Override
    public int resetAutoIncrement() throws Exception {
        return session.update(namespace + "resetAutoIncrement");
    }
}
