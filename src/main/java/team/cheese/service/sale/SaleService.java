package team.cheese.service.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.domain.MyPage.SearchCondition;
import team.cheese.dao.*;
import team.cheese.domain.SaleDto;
import team.cheese.domain.SaleTagDto;
import team.cheese.domain.TagDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleService {
    @Autowired
    SaleDao saleDao;
    @Autowired
    team.cheese.dao.SaleCategoryDao saleCategoryDao;
    @Autowired
    team.cheese.dao.AdministrativeDao administrativeDao;

    @Autowired
    team.cheese.dao.TagDao tagDao;
    @Autowired
    team.cheese.dao.AddrCdDao addrCdDao;
    @Autowired
    team.cheese.dao.SaleTagDao saleTagDao;

    @Autowired
    team.cheese.dao.TestSession testSession;

    // 전체 게시글 수 count
    public int getCount() throws Exception {
        // 총 작성된 글을 count
        return saleDao.countUse();
    }

    public List<SaleDto> getPage(Map map) throws Exception {
        System.out.println("Service map startPage : " + map.get("startPage"));
        System.out.println("Service map endPage : " + map.get("endPage"));
        return saleDao.selectList(map);
    }

    // 판매자가 자신의 게시글을 삭제할 때
    @Transactional(propagation = Propagation.REQUIRED)
    public int remove(Long no, String seller_id) throws Exception {
        // 현재 상태를 'N'으로 변경해주는거 여기서 처리
        return saleDao.delete(no, seller_id);
    }

    // 판매자가 판매 게시글을 작성할 때
    @Transactional(propagation = Propagation.REQUIRED)
    public Long write(Map<String, Object> map) throws Exception {
        // insert 해주는 거 여기서 처리

        // 1. 필수로 들어와야 되는 값 체크
        /*  addr_cd(행정동코드), addr_name(주소명),
         *   seller_id(판매자id), seller_nick(판매자명),
         *   sal_i_cd(판매카테고리), sal_name(판매카테고리명),
         *   group_no(이미지그룹번호), img_full_rt(이미지루트),
         *   pro_s_cd(사용감), tx_s_cd(거래방법),
         *   trade_s_cd_1(거래방식), title(제목), contents(내용),
         *   price(가격), bid_cd(가격제시/나눔신청여부)
         */
        //     1.1. 값이 들어와 있지 않으면 rollback -> @Valid 사용

        // 2. sale 테이블에 insert

        // 3. sale_history 테이블에 insert
        //     3.1. 실패하면 rollback

        // 4. tag테이블에 tag정보 저장
        // 5. saleTag테이블에 교차정보 저장

        SaleDto saleDto = (SaleDto) map.get("saleDto");
        System.out.println("service write: " + saleDto);

        //      세션에서 ID 값을 가지고 옴
        // TestSession 클래스를 사용하여 세션을 설정
        String ur_id = saleDto.getSeller_id();
        System.out.println("판매자id : " + ur_id);

        int insertSale = 0;
        try {
            insertSale = saleDao.insertSale(saleDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sale insert 성공? :  " + insertSale);

        Long sal_no = saleDto.getNo();

        List<String> tagList = (List<String>) map.get("tagList");
        int insertTagTx = insertTagTx(sal_no, ur_id, tagList);

        System.out.println("sal_no : " + sal_no);
        return sal_no;
    }
    
    // tag 데이터를 insert하는 트렌젝션 문
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertTagTx(Long sal_no, String ur_id, List<String> tagList) throws Exception {
        System.out.println("insertTagTx 들어옴");
        int insertTagTx = 0;
        int resultSaleTag = 0;
        for (String contents : tagList) {
            TagDto tagDto = tagDao.selectTagContents(contents);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                tagDto = new TagDto(contents, ur_id); // 새로운 객체 생성
                insertTagTx = tagDao.insert(tagDto);
            } else { // contents가 중복값이 있는 경우
                tagDto.setLast_id(ur_id);
                insertTagTx = tagDao.updateSys(tagDto);
            }
            Long tag_no = tagDto.getNo();
            resultSaleTag = insertSaleTagTx(sal_no, tag_no, ur_id);
        }
        return insertTagTx + resultSaleTag;
    }

    // saleTag 교차 테이블 데이터를 insert하는 트렌젝션 문
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertSaleTagTx(Long sal_no, Long tag_no, String ur_id) throws Exception {
        SaleTagDto saleTagDto = new SaleTagDto(sal_no, tag_no, ur_id, ur_id);

        int insertSaleTagTx = saleTagDao.insert(saleTagDto);
        return insertSaleTagTx;
    }

    // 판매자가 판매 게시글을 수정할 때
    @Transactional(propagation = Propagation.REQUIRED)
    public Long update(Map<String, Object> map) throws Exception {

        SaleDto saleDto = (SaleDto) map.get("saleDto");
        System.out.println("service write: " + saleDto);

        //      세션에서 ID 값을 가지고 옴
        // TestSession 클래스를 사용하여 세션을 설정
        String ur_id = saleDto.getSeller_id();

        int update = saleDao.update(saleDto);
        System.out.println("sale update 성공? :  " + update);

        Long sal_no = saleDto.getNo();

        List<String> tagList = (List<String>) map.get("tagList");
        updateTagTx(sal_no, ur_id, tagList);

        System.out.println("sal_no : " + sal_no);
        return sal_no;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateTagTx(Long sal_no, String ur_id, List<String> tagList) throws Exception {
        int insertTagTx = 0;
        int resultSaleTag = 0;
        deleteSaleTagTx(sal_no); // sale_tag 테이블의 판매글 번호와 일치하는 행 삭제
        for (String contents : tagList) {
            TagDto tagDto = tagDao.selectTagContents(contents);
            if (tagDto == null) { // contents가 중복값이 없는 경우
                tagDto = new TagDto(contents, ur_id); // 새로운 객체 생성
                insertTagTx = tagDao.insert(tagDto);
            } else { // contents가 중복값이 있는 경우
                tagDto.setLast_id(ur_id);
                insertTagTx = tagDao.updateSys(tagDto);
            }
            Long tag_no = tagDto.getNo();
            resultSaleTag = insertSaleTagTx(sal_no, tag_no, ur_id);
        }
        return insertTagTx + resultSaleTag;
    }

    // saleTag 교차 테이블 데이터를 insert하는 트렌젝션 문
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleTagTx(Long sal_no) throws Exception {
        saleTagDao.delete(sal_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<SaleDto> getList(Map map) throws Exception {
        List<SaleDto> saleList = saleDao.selectSaleList(map);

        return saleList;
    }

    // 페이징된 게시글 list를 가지고 올 때
    @Transactional(propagation = Propagation.REQUIRED)
    public List<SaleDto> getPageList(Map map) throws Exception {
        List<SaleDto> saleList = saleDao.selectList(map);

        return saleList;
    }

//    // 사용자가 속한 주소의 전체 게시글 list를 가지고 올 때
//    @Transactional(propagation = Propagation.REQUIRED)
//    public List<SaleDto> getUserAddrCdList(String addr_cd) throws Exception {
//        List<SaleDto> saleList = saleDao.selectUserAddrCd(addr_cd);
//        System.out.println(saleList.size());
//
//        return saleList;
//    }

    // 판매글 하나에 들어가서 게시글을 읽을 때
    @Transactional(propagation = Propagation.REQUIRED)
    public Map read(Long no) throws Exception {
        increaseViewCnt(no);

        // 판매글 번호를 넘겨 받아서 Dao에서 select로 처리
        SaleDto saleDto = saleDao.select(no);
        List<TagDto> tagDto = saleTagRead(no);

        Map map = new HashMap();
        map.put("saleDto", saleDto);
        map.put("tagDto", tagDto);

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<TagDto> saleTagRead(Long no) throws Exception {

        // 판매글 번호를 넘겨 받아서 Dao에서 select로 처리
        List<SaleTagDto> saleTagList = saleTagDao.selectSalNo(no);
        System.out.println(saleTagList);
        System.out.println(saleTagList.size());
        List<TagDto> tagDto = null;
        if(saleTagList.size() != 0) {
            tagDto = tagRead(saleTagList);
        }

        System.out.println("여기까지 완료?");

        return tagDto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<TagDto> tagRead(List<SaleTagDto> saleTagList) throws Exception {

        List<TagDto> tagDto = new ArrayList<>();

        for( SaleTagDto saleTagDto : saleTagList) {
            Long tagNo = saleTagDto.getTag_no();
            TagDto tag = tagDao.select(tagNo);
            System.out.println("tag값 확인 : " + tag);
            tagDto.add(tag);
        }

        return tagDto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void increaseViewCnt(Long no) throws Exception {
        saleDao.increaseViewCnt(no);
    }

//    // 판매 게시글을 수정할 때
//    @Transactional(propagation = Propagation.REQUIRED)
//    public int update(SaleDto saleDto) throws Exception {
//        // 판매글 내용을 받아서 수정하도록 처리
//        return saleDao.update(saleDto);
//    }

    // 판매 게시글을 수정할 때
    @Transactional(propagation = Propagation.REQUIRED)
    public Map modify(Long no) throws Exception {
        // 판매글 내용을 받아서 전달
        SaleDto saleDto = saleDao.select(no);
        List<TagDto> tagList = tagDao.getTagContents(no);

        String tagContents = "";
        for(TagDto tagDto : tagList) {
            tagContents += "#" + tagDto.getContents();
        }
        Map map = new HashMap();
        map.put("saleDto", saleDto);
        map.put("tagContents", tagContents);

        return map;
    }

    public int getCount(Map map) throws Exception {

        int totalCnt = saleDao.countSale(map);

        return totalCnt;
    }

    public List<SaleDto> getSearchPage(SearchCondition sc) throws Exception {
        return saleDao.selectSearchPage(sc);
    }
    public int getSearchCnt(SearchCondition sc) throws Exception {
        return saleDao.selectSearchCount(sc);
    }
}

