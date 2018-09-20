package agi.traffic;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static agi.traffic.Utils.kmh2ms;

@Getter
@Setter
public class Road {
    private float length = 500f;
    private int laneCount = 2;
    private float laneWidth = 3.5f;
    private float topSpeed = kmh2ms(90);
    private final List<TrafficItem> items = new ArrayList<>();


    public float getWidth() {
        return laneCount * laneWidth;
    }
}
