package hu.uni.miskolc.iit.exception;

public class UserTypeDoesNotExistException extends Exception {

    private String message;

    public UserTypeDoesNotExistException(String message){this.message = message;}

    @Override
    public String toString() {
        return message;
    }
}
