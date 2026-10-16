package com.cinema.exception;

/**
 * 账号锁定异常
 */
public class AccountLockedException extends BaseException{
    public AccountLockedException(String msg){super(msg);}
    public AccountLockedException(){}
}
