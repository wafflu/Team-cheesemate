package team.cheese.dao;

import team.cheese.domain.AdministrativeDto;

import java.util.List;

public interface AdministrativeDao {
    int count() throws Exception;

    List<AdministrativeDto> selectAll() throws Exception;

    List<AdministrativeDto> selectAddrCd(String addr_cd) throws Exception;

    List<AdministrativeDto> searchLetter(String letter) throws Exception;

    int notUse(AdministrativeDto administrativeDto) throws Exception;
}
