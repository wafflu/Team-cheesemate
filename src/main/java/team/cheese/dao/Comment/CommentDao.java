package team.cheese.dao.Comment;

import team.cheese.Domain.Comment.CommentDto;

import java.util.List;

public interface CommentDao {


    int count(Integer no)throws Exception;


    int insert(CommentDto commentDto)throws Exception;

    int update(CommentDto commentDto)throws Exception;

    CommentDto select(Integer no)throws Exception;

    List<CommentDto> selectAll(Integer post_no) throws Exception;

    int delete(Integer no)throws Exception;

    int deleteAll()throws Exception;

    int userChangeState(CommentDto commentDto)throws Exception;





}
