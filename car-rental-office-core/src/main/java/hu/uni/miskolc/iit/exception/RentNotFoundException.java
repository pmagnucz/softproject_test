package hu.uni.miskolc.iit.exception;

/**
 * Created by zsoltme on 2017.10.29..
 */
public class RentNotFoundException extends Exception {
    private String message;

    public RentNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Rent with Id, doesnt exist: " + message + ".";
    }
}
