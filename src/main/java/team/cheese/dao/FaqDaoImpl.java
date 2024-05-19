package team.cheese.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.FaqDto;

import java.util.List;
import java.util.Map;

@Repository
public class FaqDaoImpl implements FaqDao {

    @Autowired
    private SqlSession session;

    private String namespace = "team.cheese.dao.FaqDao.";

    @Override
    public int count(){
        return session.selectOne(namespace+"count");
    }
    @Override
    public List<FaqDto> selectAllFaq(){
        return session.selectList(namespace+"faqSelectAll");
    }
    @Override
    public List<FaqDto> selectMajorFaq(Integer que_id){
        return session.selectList(namespace+"faqSelectMajor",que_id);
    }
    @Override
    public List<FaqDto> searchFaqs(Map<String, Object> search){return session.selectList(namespace + "searchSelect", search);}
    @Override
    public String selectContents(Integer no){return session.selectOne(namespace + "selectGetContentByTitle", no);}
    @Override
    public int deleteAdmin(Integer no){return session.delete(namespace + "deleteAdmin", no);}
    @Override
    public int insertAdmin(FaqDto faq){return session.insert(namespace + "insertAdmin",faq);}
}
