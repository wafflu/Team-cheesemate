package team.cheese.service.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.dao.MyPage.UserInfoDao;
import team.cheese.domain.MyPage.UserInfoDTO;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    // count -> getCount
    @Override
    public int getCount() throws Exception{
          return  userInfoDao.count();
    }

    // select -> read , incrementViewCnt
    // 자신의 소개글인지 다른 사람의 소개글인지 판단하여 소개글을 읽어오기 위한 read메서드
    // => MyPageController에서 호출
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDTO read(String ur_id,String session_id) throws Exception {
        // 자신의 소개글을 읽을땐 방문자수를 늘리면 안됨
        // 자신의 소개글인지 다른 사람의 소개글인지 판단
            // 1. ur_id값이 null이면 -> 자신의 소개글 -> 방문자수 증가 없이 읽어오기
            if(ur_id==null) {
                // 1-1. ur_id값이 null 이면 ur_id값도 세션객체의 id
                ur_id = session_id;
            } else {
                // 2. 다른 사람의 소개글이라면 해당 소개글 방문자수를 증가
                //  2-2. 방문자수 증가
                int rowCnt =userInfoDao.incrementViewCnt(ur_id);
                // 2-3. rowCnt가 1이 아니면 예외발생
                if(rowCnt!=1)
                    throw new Exception("소개글 조회 중 오류가 발생했습니다.");
            }
            // 3. 해당 ur_id에 따른 소개글 읽어오기
            return read(ur_id);
    }

    // 소개글을 읽어오기 위한 메서드 (오버로딩)
    // => UserInfoController에서 호출
    @Override
    public UserInfoDTO read(String ur_id) throws Exception{
            UserInfoDTO userInfoDTO = userInfoDao.select(ur_id);
            // 1. 소개글을 읽었을때 null이면
            if(userInfoDTO==null)
                throw new Exception("소개글 조회 중 오류가 발생했습니다.");
            // 2. null이 아니면 userInfoDTO를 반환
            // 3. userInfoDTO를 반환받은 별점평균값으로 초기화 시켜주기
            userInfoDTO.setStar_avg(starRating(ur_id));
            return userInfoDTO;
    }

    // insert -> write
    // 소개글 작성을 위한 메서드
    // 회원가입 insert 시 소개글도 insert로 처리
    @Override
    public void write(UserInfoDTO userInfoDTO) throws Exception{
            int rowCnt = userInfoDao.insert(userInfoDTO);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("소개글 작성 중 오류가 발생했습니다.");
    }
    // update -> modify
    // 소개글을 수정하기 위한 메서드
    @Override
    public void modify(UserInfoDTO userInfoDTO) throws Exception{
            int rowCnt = userInfoDao.update(userInfoDTO);
            // 1. rowCnt가 1이 아니면 예외발생
            if (rowCnt != 1) {
                throw new Exception("소개글 수정 중 오류가 발생했습니다.");
            }
    }
    // delete -> remove
    // 소개글을 삭제하기 위한 메서드
    @Override
    public void remove(String ur_id) throws Exception{
            int rowCnt = userInfoDao.delete(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("소개글 삭제 중 오류가 발생했습니다.");
    }
    @Override
    public void successTx(String ur_id) throws Exception {
            int rowCnt = userInfoDao.incrementCompleteCnt(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("incrementComplete ERR");
    }
    @Override
    public void reportView(String ur_id) throws Exception {
            int rowCnt = userInfoDao.incrementRptCnt(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("incrementRpt ERR");
    }

    @Override
    public Double starRating(String ur_id) throws Exception {
        Double starAverage = userInfoDao.starAverage(ur_id);
        // 1. 소개글을 읽었을때 null이면
        if(starAverage==null)
            throw new Exception("평균별점수를 조회 중 오류가 발생했습니다.");
        // 2. null이 아니면 userInfoDTO를 반환
        return starAverage;
    }

    public void regprofile(HashMap map) throws Exception {
        userInfoDao.updateProfile(map);
    }
}
