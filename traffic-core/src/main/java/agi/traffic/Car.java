package agi.traffic;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import static agi.traffic.Utils.kmh2ms;


@Getter
@Setter
public class Car implements TrafficItem {
    @Setter(AccessLevel.NONE)
    protected Rect bounds;
    protected float topSpeed;
    protected float speed;
    protected float acc;
    protected float dec;
    protected String color;

    private boolean crash;
    private boolean breaking;

    protected Car() {
    }

    public float getX() {
        return this.bounds.getX();
    }

    public void setX(float x) {
        this.bounds.setX(x);
    }

    public void step(float dt, Road road) {
        crash = false;
        breaking = false;
        analyseRoad(road);
        if (!crash) {
            move(dt);
            if (breaking) {
                _break(dt);
            } else {
                accelerate(dt, road);
            }
        } else {
//            System.out.println("crash!");
        }
    }

    private void analyseRoad(Road road) {
        float safeDistance = 0.1f * speed + 0.5f;

        for (TrafficItem item : road.getItems()) {
            if (item != this && pathIntersects(item)) {
                //check collision
                if (item instanceof Car) {
                    Car car = (Car) item;
                    if (bounds.intersects(car.bounds) && speed > 0) {
                        crash = true;
                        speed = 0;
                        return;
                    }
                }

                //check safe distance
                float bd = Utils.brakeDistance(speed - item.getSpeed(), dec) + 1f;
                float distance = item.getX() - bounds.right();
                if (bd > distance || safeDistance > distance) {
                    breaking = true;
                    return;
                }
            }
        }
    }

    private boolean pathIntersects(TrafficItem item) {
        if (item instanceof Car) {
            Car car = (Car)item;
            return car.getX() > this.getX() && bounds.intersectsY(car.bounds);
        } else if (item instanceof TrafficLight) {
            TrafficLight tl = (TrafficLight) item;
            return tl.getX() > this.getX() && tl.getColor() == TrafficLight.LightColor.RED;
        }
        return false;
    }

//    public Rect getSafeDistanceBounds() {
//        float sd = 0.1f * speed + 0.5f;
//        return new Rect(this.bounds.right(), this.bounds.y, sd, this.bounds.height);
//    }

    private void move(float dt) {
        this.bounds.setX(bounds.getX() + dt * speed);
    }

    private void accelerate(float dt, Road road) {
        if (speed < topSpeed && speed < road.getTopSpeed()) {
            speed += acc * dt;
        }
    }

    private void _break(float dt) {
        if (speed > 0) {
            speed -= dec * dt;
        }
    }




}
