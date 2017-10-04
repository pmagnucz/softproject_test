package hu.uni.miskolc.iit.exception;

/**
 * Created by pmagnucz on 2017. 10. 03..
 */
public class WrongUserIdFormatException extends Exception {
    private String message;

    public WrongUserIdFormatException(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}