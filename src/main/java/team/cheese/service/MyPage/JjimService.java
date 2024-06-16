package team.cheese.service.MyPage;

import team.cheese.domain.MyPage.JjimDTO;
import team.cheese.domain.SaleDto;

import java.util.List;
import java.util.Map;

public interface JjimService {
    int checkLike(JjimDTO jjimDTO) throws Exception;

    int countAll(String buyer_id) throws Exception;

    int countLikes(String buyer_id,String option) throws Exception;

    List<SaleDto> selectAllLike(String buyer_id,int page,int pageSize,String option) throws Exception;

    int deleteAllLike(String buyer_id) throws Exception;

    int deleteSelectedSales(String buyer_id, List<Long> salNos) throws Exception;
}
