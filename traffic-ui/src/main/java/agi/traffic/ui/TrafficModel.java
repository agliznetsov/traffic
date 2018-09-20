package agi.traffic.ui;

import agi.traffic.Traffic;
import agi.traffic.scenarios.SimpleRoad;
import agi.traffic.scenarios.TrafficLightRoad;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
public class TrafficModel {
    private final List<Traffic> traffics;
    private final TestbedSettings settings = new TestbedSettings();
    private Traffic traffic;
    @Setter
    private boolean running;

    public TrafficModel() {
        this.traffics = Arrays.asList(new TrafficLightRoad(), new SimpleRoad());
        this.traffic = traffics.get(0);
    }

    public void reset() {
        this.traffic.reset();
    }
}
