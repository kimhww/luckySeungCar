package com.luckyRepair.seungme.luckySeungCar.main.login;

public class LoginFailException extends Exception{
    private static final long serialVersionUID = -6550602935199420445L;

    public LoginFailException(String msg) {
        super(msg);
    }

    public String toString() {
        return "LoginFailException : " + this.getMessage();
    }
}
