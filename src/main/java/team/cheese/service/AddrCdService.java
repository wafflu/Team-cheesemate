package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import team.cheese.dao.AddrCdDao;
import team.cheese.domain.AddrCdDto;

import java.util.List;

@Service
public class AddrCdService {

    @Autowired
    AddrCdDao addrCdDao;

    // *** 모든 유저의 수 카운트하는 메서드 ***
    public int getAddrCdCnt() {
        return addrCdDao.getAddrCdCnt();
    }

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    public List<AddrCdDto> getAllAddrCd() {
        return addrCdDao.getAllAddrCd();
    }

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    public List<AddrCdDto> getAddrCdByUserId(String userId) {
        System.out.println("*** AddrCdService getAddrCdByUserId 기능을 수행합니다. ***");

        try {
            System.out.println("user 아아디 : " +  userId);

            List<AddrCdDto> addrCdDtoList = addrCdDao.getAddrCdByUserId(userId);

            return addrCdDtoList;
        } catch (DataAccessException e) {
            System.out.println("DB Access Exception");
            e.printStackTrace();
            return null;
        }
    }

    // *** 유저의 주소코드 추가 ***
    public int insertAddrCd(AddrCdDto addrCdDto) {
        return addrCdDao.insertAddrCd(addrCdDto);
    }
}