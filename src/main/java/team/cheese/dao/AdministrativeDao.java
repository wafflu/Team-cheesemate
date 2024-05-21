package team.cheese.dao;

import team.cheese.domain.AdministrativeDto;

import java.util.List;

public interface AdministrativeDao {
    int count() throws Exception;

    List<AdministrativeDto> selectAll() throws Exception;

    List<AdministrativeDto> selectAddrCd(String addr_cd) throws Exception;

    List<AdministrativeDto> searchLetter(String letter) throws Exception;

    int notUse(AdministrativeDto administrativeDto) throws Exception;

    List<AdministrativeDto> selectLargeCategory() throws Exception;

    List<AdministrativeDto> selectMediumCategory(String largeAddrCd) throws Exception;

    List<AdministrativeDto> selectSmallCategory(String mediumAddrCd) throws Exception;

    AdministrativeDto selectAddrCdByAddrCd(String addr_cd);
}
