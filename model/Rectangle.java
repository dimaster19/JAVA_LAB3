package model;

import java.util.HashMap;
import java.util.Map;

public class Rectangle {
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;

    public int speedX = 1;
    public int speedY = 1;

    
    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX1() {
        return x1;
    }


    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY1() {
        return y1;
    }


    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX2() {
        return x2;
    }


    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY2() {
        return y2;
    }

    public int getWidth() {
        return x2 - x1;
    }

    public int getHeight() {
        return y2 - y1;
    }

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle(int width, int height) {
        x2 = width;
        y2 = height;
    }

    public Rectangle() {
    }

    public String rect_print() {
        return String.format("(%d, %d), (%d, %d)", x1, y1, x2, y2);
    }

    public void move(){
        x1 += speedX;
        x2 += speedX;
        y1 += speedY;
        y2 += speedY;
    }

    public void move(int dx, int dy) {
        if (x1 + dx < 0 || x2 + dx < 0)
            return;
        if (y1 + dy < 0 || y2 + dy < 0)
            return;

        x1 += dx;
        x2 += dx;

        y1 += dy;
        y2 += dy;
    }

    public static Rectangle union(Rectangle a, Rectangle b) {
        Map<String, Integer> map = calcCoordinates(a, b);
        return new Rectangle(map.get("x1"), map.get("y1"), map.get("x2"), map.get("y2"));
    }

    public Rectangle union(Rectangle rect) {

        Map<String, Integer> map = calcCoordinates(this, rect);

        x1 = map.get("x1");
        x2 = map.get("x2");
        y1 = map.get("y1");
        y2 = map.get("y2");

        return this;
    }

    private static Map<String, Integer> calcCoordinates(Rectangle a, Rectangle b) {
        Map<String, Integer> result = new HashMap<String, Integer>();

        result.put("x1", a.x1 < b.x1 ? a.x1 : b.x1);
        result.put("x2", a.x2 > b.x2 ? a.x2 : b.x2);

        result.put("y1", a.y1 < b.y1 ? a.y1 : b.y1);
        result.put("y2", a.y2 > b.y2 ? a.y2 : b.y2);

        return result;
    }
}
