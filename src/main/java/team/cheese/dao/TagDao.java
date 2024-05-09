package team.cheese.dao;

import team.cheese.domain.TagDto;

import java.math.BigInteger;

public interface TagDao {

    int count() throws Exception;

    int insert(TagDto tagDto) throws Exception;

    TagDto selectTagContents(String contents) throws Exception;

    int updateSys(TagDto tagDto) throws Exception;

    int updateTag(TagDto tagDto) throws Exception;

    int deleteAll() throws Exception;

    int resetAutoIncrement() throws Exception;
}
