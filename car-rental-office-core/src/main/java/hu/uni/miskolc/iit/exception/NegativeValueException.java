package hu.uni.miskolc.iit.exception;

/**
 * Created by zsoltme on 2017.10.23..
 */
public class NegativeValueException extends Exception {

    private String message;

    public NegativeValueException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Negative value is incorrect" + message + ".";
    }
}
