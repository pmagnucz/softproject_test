package hu.uni.miskolc.iit.exception;

public class NotValidPlateNumberFormatException extends Exception {
    private String message;

    public NotValidPlateNumberFormatException(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
