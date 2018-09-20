package agi.traffic.scenarios;

import agi.traffic.Car;
import agi.traffic.Road;
import agi.traffic.Traffic;
import agi.traffic.TrafficLight;

import static agi.traffic.Utils.kmh2ms;

public class TrafficLightRoad extends Traffic {
    public TrafficLightRoad() {
        super("Traffic light");
    }

    @Override
    public void reset() {
        setNewCarDelay(3);

        road = new Road();
        road.setLength(200);
        road.setTopSpeed(50);

        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setX(100);
        trafficLight.setCycleDuration(10);
        road.getItems().add(trafficLight);

        Car car = carFactory.newCar(road, 0);
        car.setSpeed(kmh2ms(50));
        road.getItems().add(car);
    }

    @Override
    protected Car createNewCar() {
        Car car = carFactory.newCar(road, 0);
        car.setSpeed(kmh2ms(50));
        return car;
    }
}
