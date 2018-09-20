package agi.traffic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rect {
    public float x;
    public float y;
    public float width;
    public float height;

    public Rect(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public float bottom() {
        return y + height;
    }

    public float right() {
        return x + width;
    }

    public boolean intersects(Rect rect) {
        return this.x < rect.right() && this.right() > rect.x && this.y < rect.bottom() && this.bottom() > rect.y;
    }

    public boolean intersectsY(Rect rect) {
        return this.y < rect.bottom() && this.bottom() > rect.y;
    }

    public Rect clone() {
        return new Rect(x, y, width, height);
    }
}
