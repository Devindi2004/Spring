package org.example.back_end.exeception;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
