package agi.traffic.ui;

import javafx.animation.AnimationTimer;

public class TrafficController {
    public static final int DEFAULT_FPS = 60;
    private static final long NS = 1_000_000_000;

    private TrafficModel model;
    private TrafficView view;
    private long last = 0;

    private AnimationTimer animator = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (last > 0) {
                float dt = (float) ((now - last) * 1.0 / NS);
                stepAndRender(dt);
            }
            last = now;
        }
    };

    public TrafficController(TrafficModel model, TrafficView trafficView) {
        this.model = model;
        this.view = trafficView;
    }

    public void playTest(int testNum) {
    }

    public void reset() {
        model.reset();
        view.render();
        //start();
    }

    public void init() {
        //start();
        view.render();
    }

    public void toggleRun() {
        if (model.isRunning()) {
            stop();
        } else {
            start();
        }
    }

    public void start() {
        model.setRunning(true);
        animator.start();
    }

    public void stop() {
        last = 0;
        model.setRunning(false);
        animator.stop();
    }

    public void step() {
        stop();
        stepAndRender(1.0f / DEFAULT_FPS);
    }

    protected void stepAndRender(float dt) {
        model.getTraffic().step(dt);
        view.render();
    }

}
