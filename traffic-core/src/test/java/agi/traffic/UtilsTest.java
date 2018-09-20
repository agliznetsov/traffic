package agi.traffic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void brakeDistance() {
        float v0 = 10f;
        float v = v0;
        float a = 3;
        float t = 0;
        float dt = 0.001f;
        float x = 0;
        while (v > 0) {
            v -= a * dt;
            x += v * dt;
            t += dt;
        }
//        System.out.println("t = " + t + " x = " + x);
        assertEquals(t, Utils.brakeTime(v0, a), 0.1);
        assertEquals(x, Utils.brakeDistance(v0, a), 0.1);
    }
}
