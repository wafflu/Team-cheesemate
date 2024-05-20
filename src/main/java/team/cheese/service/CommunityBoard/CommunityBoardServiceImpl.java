package team.cheese.service.CommunityBoard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;
import team.cheese.domain.ImgDto;
import team.cheese.service.ImgService;
import team.cheese.service.ImgServiceImpl;


import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {
    @Autowired
    CommunityBoardDao communityBoardDao;

    @Autowired
    ImgService imgService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CommunityBoardDto> readAll() throws Exception {
        Character fixedUrState = 'y';
        List<CommunityBoardDto> list = communityBoardDao.selectAll(fixedUrState);
        if(list==null){
            throw new Exception("게시물 목록을 불러오는데 실패햇습니다");
        }

        return list;
    }

//    @Override
//    public List<CommunityBoardDto> getPage(Integer page, Integer pageSize) throws Exception {
//        Map map = new HashMap();
//        map.put("offset",(page-1)*pageSize);
//        map.put("pageSize",pageSize);
//        List<CommunityBoardDto> list = communityBoardDao.selectPage(map);
//        if(list==null)
//            throw new Exception("목록 조회 중 오류가 발생했습니다");
//        return list;
//    }

    public List<CommunityBoardDto> getPageByCategory(int page, int pageSize, String category) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("offset", (page - 1) * pageSize);
        params.put("pageSize", pageSize);


        // 디버그 로그 추가
        System.out.println("Page: " + page);
        System.out.println("PageSize: " + pageSize);
        System.out.println("offset: " + (page - 1) * pageSize);
        System.out.println("Category: " + category);


        List<CommunityBoardDto> list = communityBoardDao.selectPageByCategory(params);
        if(list==null){
            throw new Exception("게시글 목록 조회 중 오류 발생했습니다.");
        }

        return list;
    }

    public int getCountByCategory(String category) {
        return communityBoardDao.selectCountByCategory(category);
    }




    @Transactional(rollbackFor = Exception.class)
    @Override
    public int write(Map map) throws Exception {
        CommunityBoardDto communityBoardDto = (CommunityBoardDto) map.get("communityBoardDto");

        ArrayList<ImgDto> imgList = (ArrayList<ImgDto>) map.get("imgList");

        if(communityBoardDto.getTitle()==null || communityBoardDto.getTitle().isEmpty()){
            throw new IllegalArgumentException("제목입력하지않았습니다");
        }
        if(communityBoardDto.getContents() == null|| communityBoardDto.getContents().isEmpty()){
            throw new IllegalArgumentException("내용을입력하지않았습니다");
        }

        if(imgList != null){
            int gno = imgService.getGno()+1;
            String img_full_rt = imgService.reg_img(imgList, gno, communityBoardDto.getur_id());
            communityBoardDto.setImg_full_rt(img_full_rt);
            communityBoardDto.setGroup_no(gno);
        } else {
            communityBoardDto.setImg_full_rt("0");
            communityBoardDto.setGroup_no(0);
        }

        return communityBoardDao.insert(communityBoardDto);
    }


    @Override
    public int remove(Integer no) throws Exception {
       return communityBoardDao.delete(no);
    }

    @Override
    public int removeAll() throws Exception {
       return communityBoardDao.deleteAll();
    }

    @Override
    public int modify(Map map) throws Exception {
        //1. 예외체크
            //1.1 null일 경우
            //1.2둘 중 하나가 null인 경우
        CommunityBoardDto communityBoardDto = (CommunityBoardDto) map.get("communityBoardDto");

        if(communityBoardDto == null){
            throw new IllegalArgumentException("null값 입력");
        } else if (communityBoardDto.getTitle()==null || communityBoardDto.getContents()==null) {
            throw new IllegalArgumentException("제목 또는 컨텐츠 널");
        }

        //이미지 영역
        ArrayList<ImgDto> imgList = (ArrayList<ImgDto>) map.get("imgList");

        if(imgList != null){
            int gno = imgService.getGno()+1;
            String img_full_rt = imgService.modify_img(imgList, gno, communityBoardDto.getGroup_no(), communityBoardDto.getur_id());
            communityBoardDto.setImg_full_rt(img_full_rt);
            communityBoardDto.setGroup_no(gno);
        } else {
            communityBoardDto.setImg_full_rt("0");
            communityBoardDto.setGroup_no(0);
        }

        System.out.println("serivce modify : "+communityBoardDto);

        return communityBoardDao.update(communityBoardDto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommunityBoardDto read(Integer no) throws Exception {
        CommunityBoardDto readCommunity = communityBoardDao.select(no);
        communityBoardDao.increaseViewCnt(no);
        if(readCommunity==null){
            throw new Exception("읽어오는 도중 예외가 발생했습니다");
        }
        return readCommunity;
    }

    @Override
    public int getCount() throws Exception {
        return communityBoardDao.count();
    }

    @Override
    public List<CommunityBoardDto> getTopTen() throws Exception {
        return communityBoardDao.getTopTen();
    }


    //글 삭제
    @Override
    public int userStateChange(CommunityBoardDto communityBoardDto) throws Exception {
        //현재 상태를 읽어온다.
        CommunityBoardDto existingDto = communityBoardDao.select(communityBoardDto.getno());
        if(existingDto == null) {
           throw new Exception("존재하지않는 게시글");

        }
        //반대 상태로 바꾼다.
        String current_state = String.valueOf(existingDto.getur_state());
        String new_state = current_state.equals("y") ? "n":"y";
        Character state = new_state.charAt(0);
        existingDto.setur_state(state);

        int updateResult = communityBoardDao.userChangeState(existingDto);

        if(updateResult != 1) {
            throw new Exception("삭제 실패");
        }
        return updateResult;
    }

    @Override
    public String saveFile(MultipartFile file) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String baseFileName = file.getOriginalFilename();
        String timestampPart = sdf.format(timestamp);
        String fileName = timestampPart + baseFileName;
        File saveFile = new File( "/Users/gominjeong/Desktop/cheese/TeamProject/src/main/webapp/resources/img"+ "/" +fileName);
        try{
            file.transferTo(saveFile);
        }catch (Exception e){
            throw new RuntimeException("File saving failed", e);
        }
        return fileName;
    }

    @Override
    public CommunityBoardDto findCommunityBoardById(Integer no) throws Exception {
        CommunityBoardDto entity = communityBoardDao.select(no);
        if (entity == null) {
            throw new Exception();
        }
        return entity;
    }

    @Override
    public int totalLike(Integer no) throws Exception {
        return communityBoardDao.totalLikeCount(no);
    }


}
