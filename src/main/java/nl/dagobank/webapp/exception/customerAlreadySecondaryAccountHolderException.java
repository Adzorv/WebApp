package nl.dagobank.webapp.exception;

public class customerAlreadySecondaryAccountHolderException extends Exception{
    public customerAlreadySecondaryAccountHolderException(String errorMessage){
        super(errorMessage);
    }
}
