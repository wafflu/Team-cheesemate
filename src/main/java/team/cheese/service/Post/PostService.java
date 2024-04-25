package team.cheese.service.Post;

import team.cheese.Domain.Post.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto>selectAll()throws Exception;

    public int write(PostDto postDto)throws Exception;

    public int remove(Integer sn)throws Exception;

    public int modify(PostDto postDto)throws Exception;

    public PostDto read(Integer sn)throws Exception;

    public int getCount()throws Exception;
}
