package hu.uni.miskolc.iit.exception;

import hu.uni.miskolc.iit.model.SearchUserRequest;

/**
 * Created by pmagnucz on 2017. 10. 03..
 */
public class UserNotFoundException extends Exception {
    private String message;
    private String cause;

    public UserNotFoundException(String s) {
        this.message = s;
    }

    public UserNotFoundException(String s, SearchUserRequest searchUserRequest) {
        this.message = s;
        this.cause = searchUserRequest.toString();
    }

    @Override
    public String toString() {
        return "UserNotFoundException{" +
                "message='" + message + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return toString();
    }

}