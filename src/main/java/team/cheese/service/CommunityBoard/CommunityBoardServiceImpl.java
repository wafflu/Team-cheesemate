package team.cheese.service.CommunityBoard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.Domain.CommunityBoard.CommunityBoardDto;
import team.cheese.dao.CommunityBoard.CommunityBoardDao;


import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService {
    @Autowired
    CommunityBoardDao communityBoardDao;

//    @Autowired
//    CommunityBoardDto communityBoardDto;

    @Override
    public List<CommunityBoardDto> readAll() throws Exception {
        Character fixedUrState = 'y';
        return communityBoardDao.selectAll(fixedUrState);
    }



    //write: 글 등록 : 제목과 내용, 기타 정보 등이 들어가는지 확인한다.
        //제목 혹은 내용이 들어가 있지 않은 경우
        //등록이 시간안에 안되는 경우
        //사진까지 들어간 경우 트랜잭션으로 처리
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    @Override
    public int write(CommunityBoardDto communityBoardDto) throws Exception {
        if(communityBoardDto.getTitle()==null || communityBoardDto.getTitle().isEmpty()){
            throw new IllegalArgumentException("제목입력하지않았습니다");
        }
        if(communityBoardDto.getContents() == null|| communityBoardDto.getContents().isEmpty()){
            throw new IllegalArgumentException("내용을입력하지않았습니다");
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
    public int modify(CommunityBoardDto communityBoardDto) throws Exception {
        return communityBoardDao.update(communityBoardDto);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CommunityBoardDto read(Integer no) throws Exception {
        CommunityBoardDto communityBoardDto = communityBoardDao.select(no);
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


//    //PreparedInfo: 유저정보_세션아이디와 로그인 아이디가 일치하면 호출된다.
//    @Override
//    public CommunityBoardDto preparedInfo(CommunityBoardDto communityBoardDto) throws Exception {
//        communityBoardDto = new CommunityBoardDto();
//        communityBoardDto.setaddr_cd("11010720");
//        communityBoardDto.setaddr_no(2);
//        communityBoardDto.setaddr_name("서울특별시 종로구 사직동");
//        communityBoardDto.setNick("skyLee");
//
//        return communityBoardDto;
//    }




}
