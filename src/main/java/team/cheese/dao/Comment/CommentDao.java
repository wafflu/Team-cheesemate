package team.cheese.dao.Comment;

import team.cheese.domain.Comment.CommentDto;

import java.util.List;

public interface CommentDao {


    int count(Integer no)throws Exception;


    int insert(CommentDto commentDto)throws Exception;

    int update(CommentDto commentDto)throws Exception;

    CommentDto select(CommentDto commentDto)throws Exception;

    List<CommentDto> selectAll(Integer post_no) throws Exception;

    int delete(Integer no)throws Exception;

    int deleteAll()throws Exception;

    int userChangeState(CommentDto commentDto)throws Exception;

    int findMaxByPostNo(Integer post_no)throws Exception;



}
