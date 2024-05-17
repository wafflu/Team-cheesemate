package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.AddrCdDto;

import java.util.List;

@Repository
public interface AddrCdDao {

    public void deleteAllAddrCd();
    public int getAddrCdCnt ();
    public List<AddrCdDto> getAllAddrCd();
    public List<AddrCdDto> getAddrCdByUserId (String userId);
}
