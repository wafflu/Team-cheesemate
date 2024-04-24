package team.cheese.dao.Commu;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.Domain.Commu.CommuDto;

import java.util.HashMap;

@Repository
public class CommuDaoImpl implements CommuDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.Commu.CommuMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int insert(CommuDto commuDto) throws Exception {
        return session.insert(namespace + "insert", commuDto);
    }


    @Override
    public CommuDto select(String commuCd,CommuDto commuDto) throws Exception {
        return session.selectOne(namespace + "select", commuDto);
    }

    @Override
    public int update(CommuDto commuDto) throws Exception {
        return session.update(namespace + "update", commuDto);
    }

    @Override
    public int delete(String name, String firstId) throws Exception {
        HashMap<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("firstId",firstId);
        return session.delete(namespace + "delete", map);
    }
}
