package com.yunsseong.chuckbodymainserver.common.exception;

import org.springframework.http.HttpStatus;

public interface StatusConst {
    HttpStatus getHttpStatus();
    String getMessage();
    int getStatusCode();
}
