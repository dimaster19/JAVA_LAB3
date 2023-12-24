package model;

import java.awt.*;

public class ColoredRect extends DrawableRect {
    
    Color inColor = Color.gray;

    public Color getInColor() {
        return inColor;
    }

    public void setInColor(Color inColor) {
        this.inColor = inColor;
    }

    public ColoredRect() {
        super();
    }

    public ColoredRect(int height, int width) {
        super(height, width);
    }

    public ColoredRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect(getX1(), getY1(), getWidth(), getHeight());
        g.setColor(inColor);
        g.fillRect(getX1() + 1, getY1() + 1, getWidth() - 1, getHeight() - 1);

    }
}
