package team.cheese.Service.MyPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.Dao.MyPage.UserInfoDao;
import team.cheese.Domain.MyPage.UserInfoDTO;

import javax.servlet.http.HttpSession;

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
        try {
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
                    throw new Exception("incrementViewCnt ERR");
            }
            // 3. 해당 ur_id에 따른 소개글 읽어오기
            return read(ur_id);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 소개글을 읽어오기 위한 메서드 (오버로딩)
    // => UserInfoController에서 호출
    @Override
    public UserInfoDTO read(String ur_id) throws Exception{
        try {
            UserInfoDTO userInfoDTO = userInfoDao.select(ur_id);
//            // 1. 소개글을 읽었을때 null이면
            if(userInfoDTO==null)
                throw new Exception("select ERR");
//            // 2. null이 아니면 userInfoDTO를 반환
            return userInfoDTO;
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // insert -> write
    // 소개글 작성을 위한 메서드
    // 회원가입 insert 시 소개글도 insert로 처리
    @Override
    public boolean write(UserInfoDTO userInfoDTO){
        try {
            int rowCnt = userInfoDao.insert(userInfoDTO);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("insert ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
    // update -> modify
    // 소개글을 수정하기 위한 메서드
    @Override
    public boolean modify(UserInfoDTO userInfoDTO) {
        try {
            int rowCnt = userInfoDao.update(userInfoDTO);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("update ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
    // delete -> remove
    // 소개글을 삭제하기 위한 메서드
    @Override
    public boolean remove(String ur_id) {
        try {
            int rowCnt = userInfoDao.delete(ur_id);
            System.out.println(rowCnt);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("delete ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
    @Override
    public boolean successTx(String ur_id) {
        try {
            int rowCnt = userInfoDao.incrementCompleteCnt(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("incrementComplete ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
    @Override
    public boolean clickedLike(String ur_id)  {
        try {
            int rowCnt = userInfoDao.incrementLikeCnt(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("incrementLike ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
    @Override
    public boolean clickedHate(String ur_id)  {
        try {
            int rowCnt = userInfoDao.incrementHateCnt(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("incrementHate ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
    @Override
    public boolean reportView(String ur_id)  {
        try {
            int rowCnt = userInfoDao.incrementRptCnt(ur_id);
            // 1. rowCnt가 1이 아니면 예외발생
            if(rowCnt!=1)
                throw new Exception("incrementRpt ERR");
            // 2. 1이면 true를 반환
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            // 3. 예외 발생시 false를 반환
            return false;
        }
    }
}
