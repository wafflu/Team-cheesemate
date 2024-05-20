package team.cheese.service;

import team.cheese.domain.AddrCdDto;

import java.util.List;

public interface AddrCdService {
    // *** 모든 유저의 수 카운트하는 메서드 ***
    int getAddrCdCnt();

    // *** 모든 유저를 리스트로 불러오는 메서드 ***
    List<AddrCdDto> getAllAddrCd();

    // *** 특정 아이디를 가진 유저를 가져오는 메서드 ***
    List<AddrCdDto> getAddrCdByUserId(String userId);

    // *** 유저의 주소코드 추가 ***
    void insertAddrCd(AddrCdDto addrCdDto);
}
