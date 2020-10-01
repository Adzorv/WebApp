package nl.dagobank.webapp.exception;

public class noValidTokenException extends Exception{
    public noValidTokenException(String errorMessage){
        super(errorMessage);
    }
}
