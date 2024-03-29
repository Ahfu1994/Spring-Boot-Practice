package com.chiradet.training.backend.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("User. "+code);
    }

    public static UserException requestNull(){
        return  new UserException("register.request.null");
    }
    //user.register.email.null
    public static UserException emailNull(){
        return  new UserException("register.email.null");
    }

    public static UserException nameNull(){
        return  new UserException("register.name.null");
    }

    public static UserException passwordNull(){
        return  new UserException("register.password.null");
    }

    public static UserException createEmailDuplicated(){
        return  new UserException("register.email.duplicated");
    }

    //Login fail
    public static UserException loginFailEmailNotFound(){
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect(){
        return new UserException("login.fail");
    }

    public static UserException userNotFound(){
        return new UserException("user.not.found");
    }

    public static UserException unauthorized() {
        return new UserException("unauthorized");
    }

    public static UserException notFound() {
        return new UserException("user.not.found");
    }
}
