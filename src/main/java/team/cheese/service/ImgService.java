package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.ImgFactory;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgDto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Transactional(readOnly = true)
public class ImgService {
    @Autowired
    ImgDao imgDao;
    ImgFactory ifc;

    public ImgService() {}

    public ImgService(ImgDao imgdao2){
        this.imgDao = imgdao2;
    }

    //최우선 실행되어야함
    public ImgFactory path(HttpServletRequest request){
//        System.out.println("path : "+ System.getProperty("user.home")+"/Desktop" );
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/");
        String folderPath = realPath.substring(0, realPath.indexOf("target"))+"src/main/webapp/resources/img";
        return new ImgFactory(folderPath);
    }

    //테스트용도용서비스 코드
    public ArrayList<ImgDto> readall() {
        try{
            return imgDao.select_all_img();
        } catch (Exception e) {
            throw new RuntimeException("이미지 로딩 실패", e);
        }
    }

    public ArrayList<ImgDto> read(HashMap map){
        try{
            return imgDao.select_img(map);
        } catch (Exception e){
            throw new RuntimeException("이미지 로딩 실패", e);
        }
    }

    //rollbackFor = 지정된 예외
    @Transactional
    public boolean reg_img(int gno, ImgDto idto) throws Exception {
        try{
            int img = imgDao.insert(idto);
            int img2 = imgDao.insert_group(imggroup(gno, idto.getNo()));
            if(idto.getW_size() < 0 || idto.getH_size() < 0 || img+img2 != 2){
                return false;
            }
        } catch (Exception e){
            throw new Exception("서버와의 연결이 끊겼습니다", e);
        }
        return true;
    }

    public int update(HashMap map){
        return imgDao.update(map);
    }

    private HashMap imggroup(int gno, int imgno){
        HashMap map = new HashMap<>();
        map.put("group_no", gno);
        map.put("img_no", imgno);
        return map;
    }

    public void delete(String tb_name){
        imgDao.delete(tb_name);
    }

    public int count(String tb_name){
        return imgDao.count(tb_name);
    }

    public int getGno(){
        return imgDao.select_group_max();
    }
}
