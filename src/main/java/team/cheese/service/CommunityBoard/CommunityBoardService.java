package team.cheese.service.CommunityBoard;

import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.domain.MyPage.ReviewCommentDTO;

import java.util.List;
import java.util.Map;

public interface CommunityBoardService {
    List<CommunityBoardDto> readAll() throws Exception;


    public int write(Map map) throws Exception;

    public int remove(Integer no) throws Exception;

    public int removeAll() throws Exception;

    public int modify(Map map) throws Exception;

    public CommunityBoardDto read(Integer no) throws Exception;

    public int getCount() throws Exception;

    List<CommunityBoardDto> getTopTen() throws Exception;

    public int userStateChange(CommunityBoardDto communityBoardDto) throws Exception;


    public static final String IMAGE_REPO = "src/main/webapp/resources/img";

    public String saveFile(MultipartFile file) throws Exception;

    public CommunityBoardDto findCommunityBoardById(Integer no) throws Exception;

    public int totalLike(Integer no) throws Exception;

        //paging

    public List<CommunityBoardDto> getPageByCategory(String category, int page, int pageSize) throws Exception;

    public int getCountByCategory(String category) throws Exception;
}
