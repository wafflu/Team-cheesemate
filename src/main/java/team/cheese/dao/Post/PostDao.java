package team.cheese.dao.Post;

import team.cheese.Domain.Post.PostDto;

import java.util.List;

public interface PostDao {
    int count()throws Exception;

    int insert(PostDto postDto)throws Exception;

    List<PostDto> selectAll()throws Exception;

    PostDto select(Integer sn)throws Exception;

    int update(PostDto postDto)throws Exception;

    int delete(Integer sn)throws Exception;
    int deleteAll()throws Exception;
    int increaseViewCnt(Integer sn)throws Exception;

    List<PostDto> getTopTen()throws Exception;
}
