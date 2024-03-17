package com.chiradet.training.backend.exception;

public abstract class BaseException extends RuntimeException{

    public BaseException(String code){
        super(code);
    }

}
