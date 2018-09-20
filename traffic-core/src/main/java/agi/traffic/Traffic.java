package agi.traffic;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Traffic {
    protected final String name;
    protected Road road;
    protected CarFactory carFactory = new CarFactory();
    @Setter
    protected int newCarDelay;

    private float carAddedTime = 0;

    public Traffic(String name) {
        this.name = name;
        reset();
    }

    public void step(float dt) {
        road.getItems().forEach(it -> it.step(dt, road));
        addCar(dt);
    }

    private void addCar(float dt) {
        carAddedTime += dt;
        if (newCarDelay > 0 && carAddedTime > newCarDelay) {
            carAddedTime = 0;
            Car car = createNewCar();
            if (car != null) {
                road.getItems().add(car);
            }
        }
    }

    protected Car createNewCar() {
        return null;
    }

    public abstract void reset();
}
