package team.cheese.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.domain.ImgDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ImgService {
    ResponseEntity<List<ImgDto>> uploadimg(MultipartFile[] uploadFiles, boolean check, String userid);

    ResponseEntity<byte[]> display(String fileName);

    //이미지 등록
    @Transactional
    String reg_img(ArrayList<ImgDto> imgList, int gno, String userid) throws Exception;

    @Transactional
    ImgDto reg_img_one(String filename, String imgtype, String userid) throws IOException;

    @Transactional
    String modify_img(ArrayList<ImgDto> imgList, int gno, int salegno, String userid);

    void deleteFile(String filename);

    ArrayList<ImgDto> readall();

    ArrayList<ImgDto> read(int gno);

    //rollbackFor = 지정된 예외
    @Transactional
    boolean reg_img(int gno, ImgDto idto, String userid) throws Exception;

    int update(HashMap map);

    default HashMap imggroup(int gno, int imgno, String userid) {
        HashMap map = new HashMap<>();
        map.put("group_no", gno);
        map.put("img_no", imgno);
        map.put("userid", userid);
        return map;
    }

    void delete(String tb_name);

    int count(String tb_name);

    int getGno();

    List<ImgDto> load_cssimg(String imgtype);
}
