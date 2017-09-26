package hu.uni.miskolc.iit.model;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public enum VehichleType {
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
