package team.cheese.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ImgExceptionHandler {
    //사용자 예외만 처리하기
    @org.springframework.web.bind.annotation.ExceptionHandler(ImgNullException.class)
    public ResponseEntity<String> handleNullPointerException(ImgNullException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataFailException.class)
    public ResponseEntity<String> DataFailException(DataFailException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
