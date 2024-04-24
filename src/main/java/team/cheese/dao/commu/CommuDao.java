package team.cheese.dao.Commu;

import team.cheese.domain.commu.CommuDto;


public interface CommuDao {
   int count() throws Exception;

    int insert(CommuDto commuDto)throws Exception;

    CommuDto select(String commuCd,CommuDto commuDto)throws Exception;

    int update(CommuDto commuDto)throws Exception;

    int delete(String name, String firstId)throws Exception;
}
