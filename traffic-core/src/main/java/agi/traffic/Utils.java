package agi.traffic;

public class Utils {
    public static float kmh2ms(float kmh) {
        return kmh * 1000 / 3600;
    }

    public static float ms2kmh(float ms) {
        return ms * 3600 / 1000;
    }

    public static float brakeTime(float speed, float acc) {
        return speed / acc;
    }

    public static float brakeDistance(float speed, float acc) {
        return speed * speed / acc / 2;
    }

    private static final String letters = "ABCDEF";
    public static String getRandomColor() {
        String color = "";
        for (int i = 0; i < 6; i++) {
            color += letters.charAt((int) Math.floor(Math.random() * letters.length()));
        }
        return color;
    }

}
