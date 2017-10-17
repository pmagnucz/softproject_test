package hu.uni.miskolc.iit.model;

import static org.junit.Assert.*;
import org.junit.*;
/**
 * Created by botty on 2017. 10. 16..
 */

public class CarModelTest{
    private Car car;

    @Before
    public void setUp() throws Exception{
        this.car = new Car("ABC-123","a123a321b",TRUE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPlateNumber() throws Exception {
        String expected = "ABC-123";
        String actual = car.getPlateNumber();
        assertEquals(expected, actual);
    }

    @Test
    public void getVehicleIdentificationNumber() throws Exception{
        String expected = "a123a321b";
        String actual = car.getVehicleIdentificationNumber();
        assertEquals(expected, actual);
    }

    @Test
    public void isDrawBar() throws Exception {
        boolean expected = true;
        boolean actual = car.isDrawBar();
        assertEquals(expected,actual);
    }

}