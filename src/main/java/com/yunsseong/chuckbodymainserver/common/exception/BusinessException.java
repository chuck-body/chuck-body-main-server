package com.yunsseong.chuckbodymainserver.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final StatusConst statusConst;

    public BusinessException(StatusConst statusConst) {
        super(statusConst.getMessage());
        this.statusConst = statusConst;
    }
}
