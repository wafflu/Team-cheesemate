package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.AdministrativeDto;

import java.util.List;

@Repository
public class AdministrativeDaoImpl implements AdministrativeDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "team.cheese.dao.AdministrativeMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<AdministrativeDto> selectAddrCd(String addr_cd) throws Exception {
        return session.selectOne(namespace + "selectAddrCd", addr_cd);
    }

    @Override
    public List<AdministrativeDto> searchLetter(String letter) throws Exception {
        return session.selectList(namespace + "searchLetter", letter);
    }

    @Override
    public int notUse(AdministrativeDto administrativeDto) throws Exception {
        return session.update(namespace + "update", administrativeDto);
    }
}
