package team.cheese.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteDao {
    @Autowired
    SaleDao saleDao;
    @Autowired
    SaleHistoryDao salehistoryDao;
    @Autowired
    HoistingDao hoistingDao;
    @Autowired
    BidingDao bidingDao;
    @Autowired
    SaleTagDao saleTagDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    SaleImgDao saleImgDao;
    @Autowired
    ImgDao imgDao;

    public void deleteAll() throws Exception {
//        saleImgDao.deleteAll();
//        imgDao.deleteAll();
        saleTagDao.deleteAll();
        tagDao.deleteAll();
        bidingDao.deleteAll();
        hoistingDao.deleteAll();
        salehistoryDao.deleteAll();
        saleDao.deleteAll();

//        imgDao.resetAutoIncrement();
        tagDao.resetAutoIncrement();
        bidingDao.resetAutoIncrement();
        hoistingDao.resetAutoIncrement();
        salehistoryDao.resetAutoIncrement();
        saleDao.resetAutoIncrement();
    }
}
