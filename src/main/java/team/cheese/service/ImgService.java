package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.cheese.ImgFactory;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgDto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImgService {
    @Autowired
    ImgDao imgDao;
    ImgFactory ifc;

    //최우선 실행되어야함
    public ImgFactory path(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/");
        String folderPath = realPath.substring(0, realPath.indexOf("target"))+"src/main/webapp/resources/img";
        return new ImgFactory(folderPath);
    }

    public ArrayList<ImgDto> readall(){
        ArrayList<ImgDto> list = imgDao.select_all_img();
        return list;
    }

    public ArrayList<ImgDto> read(int no){
        ArrayList<ImgDto> list = imgDao.select_img(no);
        return list;
    }

    //rollbackFor = 지정된 예외
    @Transactional(rollbackFor = Exception.class)
    public void reg_img(int gno, ImgDto idto) throws Exception{
        if(idto.getW_size() < 0 || idto.getH_size() < 0){
            throw new Exception();
        }
        imgDao.insert(idto);
        imgDao.insert_group(imggroup(gno, idto.getNo()));
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
