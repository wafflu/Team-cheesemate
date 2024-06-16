package team.cheese.service.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.domain.Comment.CommentDto;
import team.cheese.dao.Comment.CommentDao;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    CommunityBoardDao communityBoardDao;

    @Override
    public int getCount(Integer no) throws Exception {
        return commentDao.count(no);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto commentDto) throws Exception {
        communityBoardDao.updateCommentCnt(commentDto.getPost_no(),1);
        return commentDao.insert(commentDto);
    }

    @Override
    public int update(CommentDto commentDto) throws Exception {
        return commentDao.update(commentDto);
    }

    @Override
    public CommentDto read(CommentDto commentDto) throws Exception {
        return commentDao.select(commentDto);
    }

    @Override
    public List<CommentDto> readAll(Integer post_no) throws Exception {
        return commentDao.selectAll(post_no);
    }

    @Override
    public int delete(Integer no) throws Exception {
        return commentDao.delete(no);
    }

    @Override
    public int deleteAll() throws Exception {
        return commentDao.deleteAll();
    }

    @Override
    public int userChangeState(CommentDto commentDto) throws Exception {
        return commentDao.userChangeState(commentDto);
    }

    @Override
    public int findMaxByPostNo(Integer post_no) throws Exception {
        return commentDao.findMaxByPostNo(post_no);
    }
}
