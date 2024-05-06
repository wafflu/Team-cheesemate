//package team.cheese.service.sale;
//
//import team.cheese.domain.SaleDto;
//
//import java.math.BigInteger;
//import java.util.List;
//
//public interface SaleService {
//    // 전체 게시글 수 count
//    int getCount() throws Exception;
//
//    // 판매자가 자신의 게시글을 삭제할 때
//    int remove(BigInteger no, String writer) throws Exception;
//
//    // 판매자가 판매 게시글을 작성할 때
//    int write(SaleDto saleDto) throws Exception;
//
//    // 전체 게시글 list를 가지고 올 때
//    List<SaleDto> getList() throws Exception;
//
//    // 판매글 하나에 들어가서 게시글을 읽을 때
//    SaleDto read(Long no) throws Exception;
//
//    // 판매 게시글을 수정할 때
//    int modify(SaleDto saleDto) throws Exception;
//}
