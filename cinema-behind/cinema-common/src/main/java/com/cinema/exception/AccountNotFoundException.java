package com.cinema.exception;

public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException(String msg){
        super(msg);
    }
    public AccountNotFoundException(){}
}
