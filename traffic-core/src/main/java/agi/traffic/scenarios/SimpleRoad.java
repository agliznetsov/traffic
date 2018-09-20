package agi.traffic.scenarios;

import agi.traffic.Car;
import agi.traffic.Road;
import agi.traffic.Traffic;

import static agi.traffic.Utils.kmh2ms;

public class SimpleRoad extends Traffic {
    public SimpleRoad() {
        super("Simple");
    }

    @Override
    public void reset() {
        road = new Road();
        road.setLength(200);

        Car car = carFactory.newCar(road, 0);
//        car.setSpeed(road.getTopSpeed());
        car.setSpeed(kmh2ms(90));
        road.getItems().add(car);

        Car truck = carFactory.newTruck(road, 0);
        truck.setX(100);
        truck.setTopSpeed(kmh2ms(0));
        truck.setSpeed(truck.getTopSpeed());
        road.getItems().add(truck);
    }
}
