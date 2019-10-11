package rs.rnk.tasks.rest.exception;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppException extends Exception {

    private String message;
    private Timestamp timestamp;
    private String httpMethod;
    private String endpoint;
    private int code;
    private int httpStatus;


    public AppException(String message, String httpMethod, String endpoint, int code, int httpStatus) {
        timestamp = new Timestamp(new Date().getTime());
        this.message = message;
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public AppException() {
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getTimestampFormatted(String pattern) {
        // Pattern for Serbia: dd.MM.yyyy HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat();
        return format.format(new Date(timestamp.getTime()));
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
