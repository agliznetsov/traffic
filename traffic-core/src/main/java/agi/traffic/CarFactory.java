package agi.traffic;

import static agi.traffic.Utils.kmh2ms;

public class CarFactory {
    public Car newCar(Road road, int lane) {
        Car car = new Car();
        car.topSpeed = kmh2ms(200);
        car.bounds = getBounds(road, lane, 1.7f, 3.5f);
        car.acc = 3;
        car.dec = 9; //max 10m/s2
        car.speed = 0;
        car.color = Utils.getRandomColor();
        return car;
    }

    public Car newTruck(Road road, int lane) {
        Car car = new Car();
        car.topSpeed = kmh2ms(90);
        car.bounds = getBounds(road, lane, 2.5f, 6);
        car.acc = 2;
        car.dec = 9; //max 10m/s2
        car.speed = 0;
        car.color = Utils.getRandomColor();
        return car;
    }

    private Rect getBounds(Road road, int lane, float width, float length) {
        float interval = ((road.getLaneWidth() - width) / 2);
        float y = road.getWidth() - road.getLaneWidth() * (lane + 1);
        return new Rect(0, interval + y, length, width);
    }
}
