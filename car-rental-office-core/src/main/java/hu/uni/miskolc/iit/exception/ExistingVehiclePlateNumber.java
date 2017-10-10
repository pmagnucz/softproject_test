package hu.uni.miskolc.iit.exception;

public class ExistingVehiclePlateNumber extends Exception{
    private String message;

    public ExistingVehiclePlateNumber(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
