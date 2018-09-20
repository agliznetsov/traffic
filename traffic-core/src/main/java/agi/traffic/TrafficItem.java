package agi.traffic;

public interface TrafficItem {
    void step(float dt, Road road);

    float getSpeed();

    float getX();
}
