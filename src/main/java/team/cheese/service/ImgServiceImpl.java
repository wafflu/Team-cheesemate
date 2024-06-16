package team.cheese.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import team.cheese.entity.ImgFactory;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgDto;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgDao imgDao;
    ImgFactory ifc = new ImgFactory();

    public ImgServiceImpl() {}

    //아래 테스트코드에서 쓰기위한객체
    public ImgServiceImpl(ImgDao imgdao2){
        this.imgDao = imgdao2;
    }

    @Override
    public ResponseEntity<List<ImgDto>> uploadimg(MultipartFile[] uploadFiles, boolean check, String userid){
        if(!ifc.CheckImg(uploadFiles)){
            return null;
        }
        List<ImgDto> list = ifc.makeImg(uploadFiles, "r", check, userid);

        if(list == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            ResponseEntity<List<ImgDto>> result = new ResponseEntity<>(list, HttpStatus.OK);
            return result;
        }
    }

    @Override
    public ResponseEntity<byte[]> display(String fileName){
        String folderPath = ifc.getFolderPath()+File.separator;

        File file = new File(folderPath+fileName);

//        ResponseEntity<byte[]> result = null;
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));
            return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //이미지 등록
    @Override
    @Transactional
    public String reg_img(ArrayList<ImgDto> imgList, int gno, String userid) throws Exception {
        boolean cnt = true;
        String spath = "";
        String folderpath = ifc.getFolderPath() + File.separator + ifc.getDatePath();

        for(ImgDto idto : imgList){
            if (idto.getW_size() < 0 || idto.getH_size() < 0) {
                throw new Exception("이미지 사이즈가 올바르지 않습니다.");
            }

            File file = new File(folderpath, idto.getO_name()+idto.getE_name());
            if(cnt) {
                // 썸네일은 한개만
                ImgDto simg = ifc.makeImg(file, "s", gno, 292, 292, userid);
                spath = simg.getImg_full_rt();
                imgDao.insert(simg);
                imgDao.insert(imggroup(gno, simg.getNo(), userid));
                cnt = false;
            }

            //미리보기
            imgDao.insert(idto);
            imgDao.insert(imggroup(gno, idto.getNo(), userid));

            //본문
            ImgDto wimg = ifc.makeImg(file, "w", gno, 856, 856, userid);
            imgDao.insert(wimg);
            imgDao.insert(imggroup(gno, wimg.getNo(), userid));
        }
        return spath;
    }

    @Override
    @Transactional
    public ImgDto reg_img_one(String filename, String imgtype, String userid) throws IOException {
        String folderpath = ifc.getFolderPath() + File.separator + ifc.getDatePath();

        int gno = imgDao.select_group_max()+1;

        File file = new File(folderpath, filename);
        BufferedImage bi = ImageIO.read(file);

        int width = (int) bi.getWidth();
        int height = (int) bi.getHeight();

        ImgDto originalimg = ifc.setImginfo(file, filename, imgtype, width, height, userid);

        imgDao.insert(originalimg);
        imgDao.insert(imggroup(gno, originalimg.getNo(), userid));
        //임시적
        originalimg.setGroup_no(gno);
        return originalimg;
    }

    @Override
    @Transactional
    public String modify_img(ArrayList<ImgDto> imgList, int gno, int salegno, String userid) {
        ArrayList<ImgDto> list = imgDao.select_img(salegno);

        for(ImgDto img : list){
            deleteFile(img.getImg_full_rt());
        }

        boolean cnt = true;
        String spath = "";

        //기존 이미지 상태 전부 비활성화
        HashMap updatestate = new HashMap<>();
        updatestate.put("state", "N");
        updatestate.put("no", salegno);
        imgDao.update(updatestate);

        for(ImgDto idto : imgList){

            String folderpath = ifc.getFolderPath() + File.separator + idto.getFile_rt();

            File file = new File(folderpath, idto.getO_name()+idto.getE_name());
            if(cnt) {
                // 썸네일은 한개만
                ImgDto simg = ifc.makeImg(file, "s", gno, 292, 292, userid);
                spath = simg.getImg_full_rt();
                imgDao.insert(simg);
                imgDao.insert(imggroup(gno, simg.getNo(), userid));
                cnt = false;
            }

            //미리보기
            if(idto.getU_name() == null){
                ImgDto rimg = ifc.makeImg(file, "r", gno, 78, 78, userid);
                imgDao.insert(rimg);
                imgDao.insert(imggroup(gno, rimg.getNo(), userid));
            } else {
                imgDao.insert(idto);
                imgDao.insert(imggroup(gno, idto.getNo(), userid));
            }

            //본문
            ImgDto wimg = ifc.makeImg(file, "w", gno, 856, 856, userid);
            imgDao.insert(wimg);
            imgDao.insert(imggroup(gno, wimg.getNo(), userid));
        }

        return spath;
    }

    @Override
    public void deleteFile(String filename){
        File Dfile = new File(ifc.getFolderPath()+File.separator+filename);
        if(Dfile.exists()){
            Dfile.delete();
        }
    }

    @Override
    public ArrayList<ImgDto> readall() {
        return imgDao.select_all_img();
    }

    @Override
    public ArrayList<ImgDto> read(int gno){
        return imgDao.select_img(gno);
    }

    //rollbackFor = 지정된 예외
    @Override
    @Transactional
    public boolean reg_img(int gno, ImgDto idto, String userid) throws Exception {
        if(idto == null){
            throw new Exception("이미지가 비어 있습니다.");
        }
        int img = imgDao.insert(idto);
        int img2 = imgDao.insert(imggroup(gno, idto.getNo(), userid));
        if (img <= 0 || img2 <= 0) {
            throw new Exception("이미지 등록 오류");
        }
        return true;
    }

    @Override
    public int update(HashMap map){
        return imgDao.update(map);
    }

    @Override
    public void delete(String tb_name){
        imgDao.delete(tb_name);
    }

    @Override
    public int count(String tb_name){
        return imgDao.count(tb_name);
    }

    @Override
    public int getGno(){
        return imgDao.select_group_max();
    }

    @Override
    public List<ImgDto> load_cssimg(String imgtype){
        return imgDao.select_css(imgtype);
    }
}

