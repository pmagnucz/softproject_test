package hu.uni.miskolc.iit.model;

import java.io.Serializable;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public enum VehichleType implements Serializable {
    CAR("CAR"),
    SHIP("SHIP"),
    OTHER("OTHER");

    private final String value;

    VehichleType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
