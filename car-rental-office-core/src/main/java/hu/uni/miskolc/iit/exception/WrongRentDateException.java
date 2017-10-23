package hu.uni.miskolc.iit.exception;

/**
 * Created by zsoltme on 2017.10.23..
 */
public class WrongRentDateException extends Exception {

    private String message;

    public WrongRentDateException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Wrong Date values. " + message;
    }
}
