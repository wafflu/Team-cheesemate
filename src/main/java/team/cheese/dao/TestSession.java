package team.cheese.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.cheese.domain.AddrCdDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class TestSession {
    @Autowired
    AddrCdDao addrCdDao;

    HttpSession session;

    public TestSession() {

    }

    public HttpSession getSession() {
        return session;
    }

    public HttpSession setSession(String ur_id, HttpSession session) {
        session.setAttribute("userId", ur_id);
        session.setAttribute("userNick", "닉네임");

        List<AddrCdDto> userAddrCdList = addrCdDao.getAddrCdByUserId(ur_id);
        session.setAttribute("userAddrCdDtoList", userAddrCdList);

        return session;
    }

}
