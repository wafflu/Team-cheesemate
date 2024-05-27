package team.cheese.service.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.dao.MyPage.JjimDao;
import team.cheese.dao.MyPage.JjimDaoImpl;
import team.cheese.dao.MyPage.ReviewCommentDao;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.MyPage.JjimDTO;
import team.cheese.domain.MyPage.ReviewCommentDTO;
import team.cheese.domain.SaleDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JjimServiceImpl implements JjimService {

    JjimDao jjimDao;

    SaleDao saleDao;

    @Autowired
    public JjimServiceImpl(JjimDao jjimDao,SaleDao saleDao) {
        this.jjimDao = jjimDao;
        this.saleDao = saleDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int checkLike(JjimDTO jjimDTO) throws Exception {
        int result = 0;
        JjimDTO find = jjimDao.findLike(jjimDTO);
        if(find == null) { // find가 null일 경우 -> like 삽입 -> result=1
            result = jjimDao.insertLike(jjimDTO);
            saleDao.updateLikeCnt(jjimDTO.getSal_no(), 1);
        } else {
            jjimDao.deleteLike(jjimDTO); //find가 null이 아닐 경우 -> like 삭제 -> result = 0
            saleDao.updateLikeCnt(jjimDTO.getSal_no(), -1);
        }
        return result;
    }

    @Override
    public int countAll(String buyer_id) throws Exception {
        return jjimDao.countAll(buyer_id);
    }

    @Override
    public int countLikes(String buyer_id, String option) throws Exception {
        Map map = new HashMap<>();
        map.put("buyer_id",buyer_id);
        map.put("option",option);
        return jjimDao.countLikes(map);
    }

    @Override
    public List<SaleDto> selectAllLike(String buyer_id,int page,int pageSize,String option) throws Exception {
        Map map = new HashMap<>();
        map.put("buyer_id",buyer_id);
        map.put("offset",(page-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("option",option);
        return jjimDao.selectAllLike(map);
    }

    @Override
    public int deleteAllLike(String buyer_id) throws Exception {
        return jjimDao.deleteAllLike(buyer_id);
    }

    @Override
    public int deleteSelectedSales(String buyer_id, List<Long> salNos) throws Exception {

        Map map = new HashMap();
        map.put("buyer_id",buyer_id);
        map.put("salNos",salNos);

        return jjimDao.deleteSelectedSales(map);
    }
}
