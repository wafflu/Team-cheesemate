package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.AdministrativeDao;
import team.cheese.dao.SaleCategoryDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.SaleDto;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleCategoryDao saleCategoryDao;
    @Autowired
    AdministrativeDao administrativeDao;

    @Override
    public int getCount() throws Exception {
        // 총 작성된 글을 count해주어야 됨
        return 0;
    }

    @Override
    public int remove(BigInteger no, String writer) throws Exception {
        // 현재 상태를 'N'으로 변경해주는거 여기서 처리
        return 0;
    }

    @Override
    public int write(SaleDto saleDto) throws Exception {
        // insert 해주는 거 여기서 처리

        return saleDao.insert(saleDto);
    }

    @Override
    public List<SaleDto> getList() throws Exception {
        List<SaleDto> saleList = saleDao.selectAll();
        System.out.println(saleList.size());

        return saleList;
    }

    @Override
    public SaleDto read(BigInteger no) throws Exception {
        // 판매글 번호를 넘겨 받아서 Dao에서 select로 처리
        SaleDto saleDto = saleDao.select(no);

        System.out.println("sale.jsp로 전달");
        System.out.println("판매자 아이디 : " + saleDto.getSeller_id());

        return saleDto;
    }

    @Override
    public int modify(SaleDto saleDto) throws Exception {
        // 판매글 내용을 받아서 수정하도록 처리
        return saleDao.update(saleDto);
    }
}
