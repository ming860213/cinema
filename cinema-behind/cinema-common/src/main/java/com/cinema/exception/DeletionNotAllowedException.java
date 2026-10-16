package com.cinema.exception;

/**
 * 删除异常
 */
public class DeletionNotAllowedException extends BaseException{

    public DeletionNotAllowedException(String msg){
        super(msg);
    }
}
