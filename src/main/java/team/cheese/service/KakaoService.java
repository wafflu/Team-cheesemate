package team.cheese.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KakaoService {

    // 토큰(Token) 발급
    // 카카오톡 측에 client_id (= REST API key) 및 code (= 인가코드) 넘겨서 토큰 요청하는 getAccessTokenFromKakao() 메서드
    // 요청 방식: POST
    public String getAccessTokenFromKakao(String client_id, String code) throws IOException {

        // Access Token 을 요청하기 위한 URL
        // 파라미터로 REST API key 및 인가코드 함께 전송
        String reqURL = "https://kauth.kakao.com/oauth/token" +
                "?grant_type=authorization_code&client_id=" + client_id +
                "&code=" + code;
        URL url = new URL(reqURL); // URL 객체 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // HTTP 연결
        conn.setRequestMethod("POST"); // Access Token 요청 위해 client_id, code 를 카카오톡 서버로 전송하니까 요청방식 POST

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line = "";
        String result = "";

        // result : 카카오톡 서버에서 보내준 토큰 정보들이 모두 합쳐져서 하나의 문자열로 저장됨.
        while ((line = br.readLine()) != null) {
            result += line;
        }

        // 정규식 패턴 생성 후 패턴의 매칭 결과 확인하여 result 문자열을 잘라서 토큰값만 저장하고자 함.
        Pattern pattern = Pattern.compile("\"access_token\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(result);

        String accessToken = "";
        if (matcher.find()) {
            // 첫 번째 그룹에서 매칭된 값 가져오기
            // => matcher.group(0); <- access_token 들어간 문장 전체 반환
            accessToken = matcher.group(1); // access token 값만 반환
            System.out.println("Access Token: " + accessToken);
        } else {
            System.out.println("Access Token not found.");
        }

        return accessToken;
    }

    // Client_id 주체가 요청한 정보를 받아오는 getUserInfo() 메서드
    // 요청 방식: GET
    public HashMap<String, Object> getUserInfo(String access_Token) throws IOException {
        HashMap<String, Object> userInfo = new HashMap<>();

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + access_Token);

        String line = "";
        String result = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        while ((line = br.readLine()) != null) {
            result += line;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {});

        // 사용자정보 Map 에 저장
        Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
        Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

        Long id = (Long) jsonMap.get("id");
        String nickname = properties.get("nickname").toString();
        String profileImage = properties.get("profile_image").toString();

        //userInfo에 넣기
        userInfo.put("id", id);
        userInfo.put("nickname", nickname);
        userInfo.put("profileImage", profileImage);
        System.out.println("result : " + result);

        return userInfo;
    }
}
