package hu.uni.miskolc.iit.model;

import java.io.Serializable;

/**
 * Created by rozgonyi on 2017. 10. 15..
 */

public enum VehicleStatusType implements Serializable {
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