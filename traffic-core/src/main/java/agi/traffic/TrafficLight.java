package agi.traffic;

import lombok.Getter;
import lombok.Setter;

import static agi.traffic.TrafficLight.LightColor.*;

public class TrafficLight implements TrafficItem {
    @Getter
    @Setter
    private float x = 0;

    @Getter
    private float cycleDuration = 10;

    @Getter
    private LightColor color = GREEN;

    @Getter
    private float time = 10;

    public void setCycleDuration(float cycleDuration) {
        this.cycleDuration = cycleDuration;
        this.time = cycleDuration;
    }

    @Override
    public void step(float dt, Road road) {
        time -= dt;
        if (time <= 0) {
            changeColor();
        }
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    public void changeColor() {
        time = cycleDuration;
        color = (color == GREEN) ? RED : GREEN;
    }

    public static enum LightColor {
        RED, GREEN;
    }
}
