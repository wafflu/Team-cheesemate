package team.cheese.dao.Commu;

import team.cheese.domain.Commu.CommuDto;


public interface CommuDao {
   int count() throws Exception;

    int insert(CommuDto commuDto)throws Exception;

    CommuDto select(String commu_cd,CommuDto commuDto)throws Exception;

    int update(CommuDto commuDto)throws Exception;

    int delete(String name, String first_id)throws Exception;

}
