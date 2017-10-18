package hu.uni.miskolc.iit.model;

import static org.junit.Assert.*;

import hu.uni.miskolc.iit.model.Ship;
import org.junit.*;


public class ShipModelTest {
    private Ship ship;

    @Before
    public void setUp() throws Exception{
        this.ship = new Ship("Cunami", 10000, true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getShipID() throws Exception{
        String expected = "Cunami";
        String actual = ship.getShipId();
        assertEquals(expected, actual);
    }

    @Test
    public void getLenght() throws Exception {
        double expected = 10000;
        double actual = ship.getLength();
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void isWithTrailer() throws Exception{
        boolean expected = true;
        boolean actual = ship.isWithTrailer();
        assertEquals(expected, actual);
    }

}