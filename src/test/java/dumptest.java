import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.AddrCdDao;
import team.cheese.dao.AdministrativeDao;
import team.cheese.domain.AddrCdDto;
import team.cheese.domain.AdministrativeDto;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class dumptest {
    @Autowired
    AdministrativeDao adao;

    @Test
    public void addrlist() throws Exception {
        List<AdministrativeDto> list = adao.selectAll();

        System.out.println(list.size());

        Collections.shuffle(list);

        // 13개의 랜덤 요소를 뽑기 위해 서브리스트를 생성합니다.
        List<AdministrativeDto> randomList = list.subList(0, 13);
        String[] user = {
                "alswjd",
                "asdf",
                "david234",
                "dbrud",
                "i123456",
                "rptmxm1",
                "rudtlr",
                "user123",
                "whdgjs",
                "wjdgk",
                "wjdgns",
                "wlsdn",
                "wogjs",
        };

        int a = 0;
        String str = "";
        StringBuilder sbr = new StringBuilder();

        // 랜덤으로 선택된 13개의 요소를 출력합니다.
        for (AdministrativeDto item : randomList) {
//            System.out.println(item.getAddr_cd()+"/"+item.getAddr_name());
            sbr.append("INSERT INTO addr_cd (ur_id, addr_cd, addr_name, state, first_id, last_id) VALUES (");
            sbr.append("'"+user[a]+"', ");
            sbr.append("'"+item.getAddr_cd()+"', ");
            sbr.append("'"+item.getAddr_name()+"', ");
            sbr.append("'Y', 'admin', 'admin');\n");
            a++;
        }
        System.out.println(sbr.toString());
    }
    //getAllAddrCd
}
