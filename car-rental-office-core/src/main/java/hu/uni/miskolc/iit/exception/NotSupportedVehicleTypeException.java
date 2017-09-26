package hu.uni.miskolc.iit.exception;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
public class NotSupportedVehicleTypeException extends IllegalArgumentException {
    private final String type;

    public NotSupportedVehicleTypeException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NotSupportedVehicleTypeException{" +
                "type='" + type + '\'' +
                '}';
    }
}
