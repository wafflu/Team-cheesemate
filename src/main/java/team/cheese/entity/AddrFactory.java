package team.cheese.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.cheese.dao.AdministrativeDao;
import team.cheese.domain.AdministrativeDto;

import java.util.Collections;
import java.util.List;

@RestController
public class AddrFactory {
    @Autowired
    AdministrativeDao administrativeDao;

    // ajax 주소 검색
    @RequestMapping("/searchLetter")
    @ResponseBody
    public ResponseEntity<List<AdministrativeDto>> getAdministrative(@RequestParam String searchLetter, Model model) throws Exception {
        // 검색어를 이용하여 주소를 검색
        try{
            return new ResponseEntity<>(administrativeDao.searchLetter(searchLetter), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
