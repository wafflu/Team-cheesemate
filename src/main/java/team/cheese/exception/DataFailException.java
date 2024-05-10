package team.cheese.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DataFailException  extends RuntimeException {
    public DataFailException(String msg) {
        super(msg);
    }

    public DataFailException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
