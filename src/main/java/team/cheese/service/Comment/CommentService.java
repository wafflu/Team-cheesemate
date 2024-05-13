package team.cheese.service.Comment;

import team.cheese.Domain.Comment.CommentDto;

import java.util.List;

public interface CommentService {

    int getCount(Integer no) throws Exception;

    int write(CommentDto commentDto) throws Exception;

    int update(CommentDto commentDto) throws Exception;

    CommentDto read(Integer no) throws Exception;

    List<CommentDto> readAll(Integer post_no) throws Exception;



    int delete(Integer no) throws Exception;

    int deleteAll() throws Exception;

    int userChangeState(CommentDto commentDto) throws Exception;


}
