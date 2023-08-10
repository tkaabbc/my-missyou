package com.example.mymissyou.exception.http;

public class ForbiddenException extends HttpException {
    public ForbiddenException(int code){
        this.code = code;
        this.httpStatusCode = 403;
    }
}
