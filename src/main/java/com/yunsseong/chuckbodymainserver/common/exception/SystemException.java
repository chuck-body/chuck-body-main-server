package com.yunsseong.chuckbodymainserver.common.exception;

import lombok.Getter;

@Getter
public class SystemException extends RuntimeException{
    private final StatusConst statusConst;

    public SystemException(StatusConst statusConst) {
        super(statusConst.getMessage());
        this.statusConst = statusConst;
    }
}
