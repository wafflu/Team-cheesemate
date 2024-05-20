package team.cheese.service.CommunityBoard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.CommunityBoard.CommunityBoardDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;
import team.cheese.domain.ImgDto;
import team.cheese.service.ImgService;
import team.cheese.service.ImgServiceImpl;


import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {
    @Autowired
    CommunityBoardDao communityBoardDao;

    @Autowired
    ImgService imgService;

    @Override
    public List<CommunityBoardDto> readAll() throws Exception {
        Character fixedUrState = 'y';
        try{
            List<CommunityBoardDto> list = communityBoardDao.selectAll(fixedUrState);

            if(list==null){
                throw new Exception();
            }
        }catch (Exception e){
            throw e;
        }
        return communityBoardDao.selectAll(fixedUrState);
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

    @Transactional
    @Override
    public CommunityBoardDto read(Integer no) throws Exception {
        //1. 예외체크
            //1.1  no가 null일경우
            //1.2 제목,내용, 작성자, 일자 등이 null일 경우
        //2. 제목,내용,작성자,일자 등을 시간안에 받아오지 못할 경우
        if(no == null){
            throw  new IllegalArgumentException("게시글 번호를 받아오지 못했다.");
        }
        CommunityBoardDto communityBoardDto = communityBoardDao.select(no);
        if (communityBoardDto == null) {
            throw new IllegalArgumentException(no + "에 해당하는 글이 존재하지 않습니다.");
        }
        if(communityBoardDto.getTitle()==null || communityBoardDto.getContents()==null){
            throw new IllegalArgumentException("글의 제목이나 내용이 없습니다.");
        }
        communityBoardDao.increaseViewCnt(no);
        return communityBoardDto;
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
           throw new Exception();

        }
        //반대 상태로 바꾼다.
        String current_state = String.valueOf(existingDto.getur_state());
        String new_state = current_state.equals("y") ? "n":"y";
        Character state = new_state.charAt(0);
        existingDto.setur_state(state);

        int updateResult = communityBoardDao.userChangeState(existingDto);

        if(updateResult != 1) {
            throw new Exception();
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
