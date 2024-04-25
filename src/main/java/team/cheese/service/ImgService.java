package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgVO;

@Service
public class ImgService {
    @Autowired
    ImgDao imgDao;

    public int reg_img(ImgVO imgVO){
        return imgDao.insert_img(imgVO);
    }

}
