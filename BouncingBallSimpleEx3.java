/*
 * Модифицировать код предыдущей программы таким образом, чтобы
 * движение осуществляли сразу 10 экземпляров класса Rectangle,
 * 10 класса DrawableRect и 10 экземпляров класса ColorableRect.
 * Все созданные объекты должны хранится в одном массиве с
 * типом класса родителя Rectangle.
 */

import java.awt.*;
import java.util.Random;

import javax.swing.*;

import model.ColoredRect;
import model.DrawableRect;
import model.Rectangle;

public class BouncingBallSimpleEx3 extends JPanel {

    private static final int BOX_WIDTH = 640;
    private static final int BOX_HEIGHT = 480;

    private static final int RECT_HEIGHT = 50;
    private static final int RECT_WIDTH = 50;

    private static final int MAX_SPEED = 5;
    private static final int MIN_SPEED = 1;

    private static final int FILLED = 10;
    private static final int OUTLINED = 10;
    private static final int RECTS = 10;

    private static final int UPDATE_RATE = 30;
    
    
    private static Rectangle[] components = new Rectangle[RECTS + OUTLINED + FILLED];
    private static final Random rand = new Random();

    private static int getRandomSpeed() {
        return rand.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED;
    }

    public BouncingBallSimpleEx3() {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    for (Rectangle rectangle : components) {
                        if (rectangle.getX2() + rectangle.speedX >= BOX_WIDTH) {
                            rectangle.speedX = -getRandomSpeed();
                        } else if (rectangle.getY2() + rectangle.speedY >= BOX_HEIGHT) {
                            rectangle.speedY = -getRandomSpeed();
                        } else {
                            rectangle.move();
                        }

                        if (rectangle.getX1() + rectangle.speedX <= 0) {
                            rectangle.speedX = getRandomSpeed();
                        } else if (rectangle.getY1() + rectangle.speedY <= 0) {
                            rectangle.speedY = getRandomSpeed();
                        } else {
                            rectangle.move();
                        }

                    }

                    repaint();

                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        gameThread.start(); 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        for (Rectangle rectangle : components) {
            if (rectangle instanceof ColoredRect a) {
                a.setOutColor(Color.MAGENTA);
                a.setInColor(Color.BLUE);
                a.draw(g);
            } else if (rectangle instanceof DrawableRect a) {
                a.setOutColor(Color.RED);
                a.draw(g);
            } else {
                g.setColor(Color.RED);
                g.drawRect(rectangle.getX1(), rectangle.getY1(), rectangle.getWidth(), rectangle.getHeight());
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < components.length; i++) {
            if (i < RECTS) {
                components[i] = new Rectangle(0, 0, RECT_WIDTH, RECT_HEIGHT);
                components[i].speedX = getRandomSpeed();
                components[i].speedY = getRandomSpeed();
            } else if (i < RECTS + OUTLINED) {
                components[i] = new DrawableRect(0, 0, RECT_WIDTH, RECT_HEIGHT);
                components[i].speedX = getRandomSpeed();
                components[i].speedY = getRandomSpeed();
            } else {
                components[i] = new ColoredRect(0, 0, RECT_WIDTH, RECT_HEIGHT);
                components[i].speedX = getRandomSpeed();
                components[i].speedY = getRandomSpeed();
            }
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("A Bouncing Ball(Rectangle) Ex3");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new BouncingBallSimpleEx3());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
