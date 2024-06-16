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
    public List<AdministrativeDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
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

    @Override
    public List<AdministrativeDto> selectLargeCategory() {
        return session.selectList(namespace + "selectLargeCategory");
    }

    @Override
    public List<AdministrativeDto> selectMediumCategory(String largeAddrCd) {
        return session.selectList(namespace + "selectMediumCategory", largeAddrCd);
    }

    @Override
    public List<AdministrativeDto> selectSmallCategory(String largeAddrCd) {
        return session.selectList(namespace + "selectSmallCategory", largeAddrCd);
    }

    @Override
    public AdministrativeDto selectAddrCdByAddrCd(String addr_cd) {
        return session.selectOne(namespace + "selectAddrCdByAddrCd", addr_cd);
    }
}
