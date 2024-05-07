package team.cheese.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler {
    //글로벌
//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//        // 예외 메시지를 응답으로 반환
//        return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ImgNullException.class)
    public ResponseEntity<String> handleNullPointerException(ImgNullException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>("1 : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataFailException.class)
    public ResponseEntity<String> DataFailException(DataFailException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>("2 : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> DataIntegrityViolationException(DataIntegrityViolationException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>("3 : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> DataIntegrityViolationException(SQLIntegrityConstraintViolationException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>("4 : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleException(SQLException e) {
        // 예외 메시지를 응답으로 반환
        return new ResponseEntity<>("asdas서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
