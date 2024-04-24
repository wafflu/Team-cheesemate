package team.cheese.dao.Post;

import team.cheese.Domain.Post.PostDto;

import java.util.List;

public interface PostDao {
    int count()throws Exception;

    int insert(PostDto postDto)throws Exception;

    List<PostDto> selectAll()throws Exception;
}
