package agi.traffic.ui;

import agi.traffic.*;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

public class TrafficView extends Pane {
    public static final float TOP_MARGIN = 50f;
    public static final Color GRAY = Color.gray(0.3);

    private static final Map<String, Color> colorMap = new HashMap<>();

    private TrafficModel model;
    private Canvas canvas;
    private GraphicsContext gc;
    private Bounds bounds;
    private float zoom = 5f;

    public TrafficView(TrafficModel model) {
        this.model = model;
        canvas = new Canvas(0, 0);
        getChildren().add(canvas);
        canvas.getGraphicsContext2D().setFont(new Font("Courier New", 12));
        render();
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
    }

    public void render() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKGREEN);
        bounds = getBoundsInLocal();
        gc.fillRect(bounds.getMinX(), bounds.getMinX(), bounds.getWidth(), bounds.getHeight());
        drawRoad(model.getTraffic().getRoad());
        drawItems(model.getTraffic().getRoad());
    }

    private void drawItems(Road road) {
        gc.setLineWidth(1.0);
        for (TrafficItem item : road.getItems()) {
            if (item instanceof Car) {
                drawCar((Car)item);
            } else if (item instanceof TrafficLight) {
                drawTrafficLight((TrafficLight)item);
            }
        }
    }

    private void drawTrafficLight(TrafficLight item) {
        gc.setStroke(Color.WHITE);
        gc.setFill(item.getColor() == TrafficLight.LightColor.RED ? Color.RED : Color.GREEN);
        double x = item.getX() * zoom;
        double y = TOP_MARGIN - 25;
        double w = 20;
        double h = 20;
        gc.fillOval(x, y, w, h);
        gc.strokeOval(x, y, w, h);
        String time = String.valueOf((int)item.getTime());
        gc.setFill(Color.WHITE);
        gc.fillText(time, x + 25, y + 15);
    }

    private void drawCar(Car car) {
        gc.setStroke(Color.WHITE);
        gc.setFill(getColor(car.getColor()));
        drawRect(car.getBounds(), true);

        if (car.isBreaking()) {
            gc.setFill(Color.RED);
            Rect b = car.getBounds().clone();
            double x = b.getX() * zoom;
            double y = b.getY() * zoom + TOP_MARGIN;
            double w = 3;
            double h = b.getHeight() * zoom;
            gc.fillRect(x, y, w, h);
        }
    }

    private void drawRect(Rect b, boolean fill) {
        double x = b.getX() * zoom;
        double y = b.getY() * zoom + TOP_MARGIN;
        double w = b.getWidth() * zoom;
        double h = b.getHeight() * zoom;
        if (fill) {
            gc.fillRect(x, y, w, h);
        }
        gc.strokeRect(x, y, w, h);
    }

    private static Paint getColor(String color) {
        return colorMap.computeIfAbsent(color, Color::web);
    }

    private void drawRoad(Road road) {
        gc.setFill(GRAY);
        gc.fillRect(0, TOP_MARGIN, road.getLength() * zoom, road.getWidth() * zoom);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3.0);
        for (int i = 0; i <= road.getLaneCount(); i++) {
            double y = TOP_MARGIN + i * road.getLaneWidth() * zoom;
            gc.setLineDashes(i > 0 && i < road.getLaneCount() ? 20 : 0);
            gc.strokeLine(0, y, road.getLength() * zoom, y);
        }
    }

}
