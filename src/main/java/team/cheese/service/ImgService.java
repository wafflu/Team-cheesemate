package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgDto;

import java.util.HashMap;

@Service
public class ImgService {
    @Autowired
    ImgDao imgDao;

}
