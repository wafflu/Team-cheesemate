import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.cheese.dao.ImgDao;
import team.cheese.domain.ImgDto;
import team.cheese.service.ImgService;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class test {
    @Autowired
    ImgService imgService;

    //이미지 수정테스트

    @Test
    public void test(){

        List<ImgDto> list = imgService.read(21);

        Iterator it = list.iterator();

        while (it.hasNext()){
            ImgDto idto = (ImgDto) it.next();
            if(!idto.getImgtype().equals("r") && !idto.getImgtype().equals("s")){
                String imgname = idto.getO_name()+idto.getE_name().toLowerCase();

                String normalizedImgname = Normalizer.normalize(imgname, Normalizer.Form.NFC);
                //

                String[] arr = {"마루.jpeg", "아보카도.png", "adasd.jpeg"};

                for (String name : arr) {
                    if (imgname.equals(name)) {
                        System.out.println(imgname + " 와 " + name + " 는 동일합니다.");
                    } else {
                        System.out.println(imgname + " 와 " + name + " 는 동일하지 않습니다.");
                    }
                }
            }
        }
    }

   @Test
    public void hash(){
        String pass = "1번_아보카도.png";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String hashpass = passwordEncoder.encode(pass);

       System.out.println("해시된 비밀번호 : "+hashpass);

       boolean ispass = passwordEncoder.matches(pass, hashpass);
       System.out.println("비밀번호 일치 : "+ispass);
   }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        // SHA-256 해시 알고리즘 인스턴스 생성
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // 입력된 비밀번호를 바이트 배열로 변환하여 해싱
        byte[] encodedHash = digest.digest(password.getBytes());

        // 해시값을 16진수 문자열로 변환
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Test
    public void pass() throws NoSuchAlgorithmException {
        String password = "user123아보카도.png";

        // 비밀번호를 해시화
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // 데이터베이스에서 가져온 해시값
        String storedHashedPassword = "0d648b01768eae5c49592f999ecc2b6b2735e8de1caad9e50b2f6057daa539bb";

        // 사용자가 입력한 비밀번호를 해시화하여 데이터베이스에서 가져온 해시값과 비교
        boolean passwordsMatch = hashPassword(password).equals(storedHashedPassword);
        System.out.println("Passwords Match: " + passwordsMatch);
    }

    @Test
    public void path(){
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }

    @Test
    public void filetest(){
        String path = System.getProperty("user.home")+"/Desktop/Img";
        ///Users/jehyeon/Desktop/Img
        System.out.println(path);

        // 디렉토리 객체 생성
        File directory = new File(path);

        //디렉토리에 있는 파일과 디렉토일 확인
        File[] files = directory.listFiles();

        if(files != null){
            for(File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("해당 경로에 아무것도 없음");
        }
    }
}
