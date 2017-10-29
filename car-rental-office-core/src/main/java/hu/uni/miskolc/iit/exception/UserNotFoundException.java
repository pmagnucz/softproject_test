package hu.uni.miskolc.iit.exception;

/**
 * Created by pmagnucz on 2017. 10. 03..
 */
public class UserNotFoundException extends Exception {
    private String message;

    public UserNotFoundException(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return toString();
    }
}