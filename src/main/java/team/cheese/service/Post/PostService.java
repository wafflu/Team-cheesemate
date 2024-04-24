package team.cheese.service.Post;

import team.cheese.Domain.Post.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto>selectAll()throws Exception;
}
