package model;

import java.awt.*;

public class DrawableRect extends Rectangle {

    Color outColor = Color.red;

    public Color getOutColor() {
        return outColor;
    }

    public void setOutColor(Color outColor) {
        this.outColor = outColor;
    }

    public DrawableRect() {
        super();
    }

    public DrawableRect(int height, int width) {
        super(height, width);
    }

    public DrawableRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect(getX1(), getY1(), getWidth(), getHeight());
    }
}
