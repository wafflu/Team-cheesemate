import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testng.annotations.Test;
import team.cheese.dao.ImgDao;
import team.cheese.dao.SaleDao;
import team.cheese.domain.ImgDto;
import team.cheese.domain.SaleDto;
import team.cheese.entity.ImgFactory;
import team.cheese.service.ImgService;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class makeimgtest {
    @Autowired
    ImgService imgService;

    @Autowired
    ImgDao imgDao;

    @Autowired
    SaleDao saleDao;

    //아래 이미지 만들기용도
    @Test
    public void imgmaketest(){
        ImgFactory ifc = new ImgFactory();

        String Path = ifc.getFolderPath()+"/event";

        // 디렉토리 객체 생성
        File directory = new File(Path);

        //디렉토리에 있는 파일과 디렉토일 확인
        File[] files = directory.listFiles();

        if(files != null){
            for(File file : files) {
                String mimeType = getMimeType(file);
                if (mimeType != null && mimeType.startsWith("image/")) {
                    ImgDto img = imgdtomake(file, "event", 0, 0, "admin");
                    System.out.println(img.getImg_full_rt());
//                    imgDao.insert(img);
                }
            }
        } else {
            System.out.println("해당 경로에 아무것도 없음");
        }
    }

    private static String getMimeType(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ImgDto imgdtomake(File imgfile, String imgtype, int wsize, int hsize, String userid){
        ImgDto img = new ImgDto();
        String uploadFileName = imgfile.getName();
        img.setImgtype(imgtype);
        img.setFile_rt("event");
        img.setU_name(uploadFileName);
        img.setO_name(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
        img.setE_name(uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length()));
        img.setImg_full_rt("event/"+uploadFileName);
        img.setW_size(wsize);
        img.setH_size(hsize);
        img.setFirst_id(userid);
        img.setLast_id(userid);
        return img;
    }


    @Test
    public void testUpdateSaleSCdRandomRCode() throws Exception {
        for (int i=0; i<25; i++) {
            Long no = (long) (Math.random() * saleDao.count() + 1);
            System.out.println(no);
            SaleDto saleDto = saleDao.select(no);

            String sal_s_cd = "R";
            Map<String, Object> map = new HashMap<>();
            if(!saleDto.getSeller_id().equals("i1234")) {
                saleDto.setBuyer_id("asdf");
                saleDto.setBuyer_nick("asdf");
                saleDto.setSal_s_cd(sal_s_cd);
                assertTrue(saleDao.buySale(saleDto) == 1);

            }
        }
    }

}
