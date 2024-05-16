package team.cheese.dao;

import team.cheese.domain.TagDto;

import javax.servlet.jsp.tagext.Tag;
import java.math.BigInteger;
import java.util.List;

public interface TagDao {

    int count() throws Exception;

    int insert(TagDto tagDto) throws Exception;

    TagDto selectTagContents(String contents) throws Exception;

    int updateSys(TagDto tagDto) throws Exception;

    int updateTag(TagDto tagDto) throws Exception;

    int deleteAll() throws Exception;

    int resetAutoIncrement() throws Exception;

    List<TagDto> getTagContents(Long sal_no) throws Exception;
}
