package hu.uni.miskolc.iit.model;

/**
 * Created by rozgonyi on 2017. 10. 15..
 */

public enum VehicleStatusType {
    FREE("FREE"),
    RESERVED("RESERVED"),
    NOT_AVAILABLE("NOT_AVAILABLE");

    private final String value;

    VehicleStatusType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}