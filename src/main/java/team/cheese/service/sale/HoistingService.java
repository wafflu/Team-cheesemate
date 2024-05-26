package team.cheese.service.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.dao.*;
import team.cheese.domain.*;

@Service
public class HoistingService {
    @Autowired
    SaleDao saleDao;
    @Autowired
    HoistingDao hoistingDao;
    
    // 판매자가 자신의 게시글을 끌어올릴 때
    @Transactional(propagation = Propagation.REQUIRED)
    public void hoistingSale(SaleDto saleDto) throws Exception {
        System.out.println("hoistingSale : " + saleDto);

        Long sal_no = saleDto.getNo();
        String addr_cd = saleDto.getAddr_cd();
        String addr_name = saleDto.getAddr_name();
        String seller_id = saleDto.getSeller_id();
        String sal_i_cd = saleDto.getSal_i_cd();
        String sal_name = saleDto.getSal_name();

        HoistingDto hoistingDto = new HoistingDto(sal_no, addr_cd, addr_name, seller_id, sal_i_cd, sal_name);

        System.out.println("hoitingDto 확인 : " + hoistingDto);

        int result = hoistingDao.insert(hoistingDto);
        System.out.println("hositngDao insert 성공? : " + result);
        hoistingSaleCount(sal_no);
    }

    // 판매글 끌어올리기 횟수 증가
    @Transactional(propagation = Propagation.REQUIRED)
    public void hoistingSaleCount(Long no) throws Exception {
        int result = saleDao.increaseHoistingCnt(no);
        System.out.println("increaseHoistingCnt 성공? : " + result);
    }
}

