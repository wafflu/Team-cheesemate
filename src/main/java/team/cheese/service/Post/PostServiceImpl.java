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

    @Override
    public int write(PostDto postDto) throws Exception {
        return postDao.insert(postDto);
    }

    @Override
    public int remove(Integer sn) throws Exception {
        return postDao.delete(sn);
    }

    @Override
    public int modify(PostDto postDto) throws Exception {
        return postDao.update(postDto);
    }

    @Override
    public PostDto read(Integer sn) throws Exception {
        PostDto postDto = postDao.select(sn);
        postDao.increaseViewCnt(sn);
        return postDto;
    }

    @Override
    public int getCount() throws Exception {
        return postDao.count();
    }

    @Override
    public List<PostDto> getTopTen() throws Exception {
        return postDao.getTopTen();
    }
}
