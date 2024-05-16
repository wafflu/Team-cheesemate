package team.cheese.dao;

import org.apache.ibatis.annotations.Param;
import team.cheese.domain.SaleTagDto;

import javax.servlet.jsp.tagext.Tag;
import java.util.List;

public interface SaleTagDao {

    // 전체 개수
    int count() throws Exception;

    List<SaleTagDto> selectSalNo(Long sal_no) throws Exception;

    int insert(SaleTagDto saleTagDto) throws Exception;

    int delete(Long sal_no) throws Exception;

    int deleteAll() throws Exception;

    int resetAutoIncrement() throws Exception;


}
