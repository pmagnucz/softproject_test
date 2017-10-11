package hu.uni.miskolc.iit.exception;

public class VehicleNotFoundException extends Exception{
    private String message;

    public VehicleNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
