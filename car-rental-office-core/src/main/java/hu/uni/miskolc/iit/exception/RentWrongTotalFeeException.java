package hu.uni.miskolc.iit.exception;

/**
 * Created by zsoltme on 2017.10.28..
 */
public class RentWrongTotalFeeException extends Exception {
    private String message;

    public RentWrongTotalFeeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Wrong TotalFee value: " + message + ".";
    }
}
