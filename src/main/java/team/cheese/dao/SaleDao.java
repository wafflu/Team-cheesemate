package team.cheese.dao;

import java.math.BigInteger;
import java.util.List;

import team.cheese.domain.SaleDto;

public interface SaleDao {
    int count() throws Exception;

    int countUse() throws  Exception;

    // 판매글 전부 가져오기
    List<SaleDto> selectAll() throws Exception;

    // 판매글 번호를 이용하여 판매글 가져오기
    SaleDto select(BigInteger no) throws Exception;

    // 판매글 작성하는 경우
    int insert(SaleDto saleDto) throws  Exception;

    // 판매글 수정하는 경우
    int update(SaleDto saleDto) throws Exception;

    // 판매글 사용자가 삭제하는 경우
//    int delete(BigInteger no, String seller_id) throws Exception;
    int delete(SaleDto saleDto) throws Exception;

    int deleteAll() throws Exception;

    int resetAutoIncrement() throws Exception;

    // 판매글 관리자가 삭제하는 경우
    int adminState(SaleDto saleDto) throws  Exception;

    // 판매/나눔을 클릭 했을 때 사용자의 첫번째 주소로 조회되는 경우
    List<SaleDto> selectStandardAddr(String ur_id, int check_addr_cd) throws Exception;


}
