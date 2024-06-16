package team.cheese.dao.MyPage;

import team.cheese.domain.MyPage.JjimDTO;
import team.cheese.domain.SaleDto;

import java.util.List;
import java.util.Map;

public interface JjimDao {
    JjimDTO findLike(JjimDTO jjimDTO) throws Exception;

    int insertLike(JjimDTO jjimDTO) throws Exception;

    int deleteLike(JjimDTO jjimDTO) throws Exception;

    int countAll(String buyer_id) throws Exception;

    int countLikes(Map map) throws Exception;

    List<SaleDto> selectAllLike(Map map) throws Exception;

    int deleteAllLike(String buyer_id) throws Exception;

    int deleteSelectedSales(Map map) throws Exception;
}
