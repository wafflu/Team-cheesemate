package team.cheese.service.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.Domain.Post.PostDto;
import team.cheese.dao.Post.PostDao;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostDao postDao;

    @Override
    public List<PostDto>selectAll()throws Exception{
        return postDao.selectAll();
    }
}
