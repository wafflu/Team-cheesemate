package team;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.dao.MyPage.UserInfoDaoImpl;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class userinfo_dao_test {
    @Autowired
    UserInfoDao userinfoDao;

    @Test
    public void profile() throws Exception {
        HashMap map = new HashMap<>();
        map.put("img_full_rt", "aaaa.png");
        map.put("group_no", 1);
        map.put("ur_id", "1");
        assertTrue(userinfoDao.updateProfile(map) != 0);
    }

}
