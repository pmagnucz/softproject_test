package hu.uni.miskolc.iit.exception;

/**
 * Created by zsoltme on 2017.10.28..
 */
public class RentIdAlreadyExistsException extends Exception{
    private String message;

    public RentIdAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Rent Id already exists: " + message + ".";
    }
}
