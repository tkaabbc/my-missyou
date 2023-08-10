package com.example.mymissyou.core;

import com.example.mymissyou.core.configuration.ExceptionCodeConfiguration;
import com.example.mymissyou.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;
    @ExceptionHandler(Exception.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req, Exception e) {
        String requestUrl = req.getRequestURI();
        String requestMethod = req.getMethod();
        System.out.println(e);

        return new UnifyResponse(9999, "服务器异常", requestMethod + " " + requestUrl);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e) {
        String requestUrl = req.getRequestURI();
        String requestMethod = req.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(), codeConfiguration.getMessage(e.getCode()), requestMethod + " " + requestUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());

        return new ResponseEntity<>(message, headers, httpStatus);
    }

}
