/*
 * Модифицировать код программы bouncingcircle таким образом,
 * чтобы вместо круга движение осуществлял экземпляр
 * реализованного ранее (в лабораторной работе №2) класса
 * ColorableRect().
 */

import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

import model.ColoredRect;

public class BouncingBallSimpleEx2 extends JPanel {

    private static final int BOX_WIDTH = 640;
    private static final int BOX_HEIGHT = 480;

    private static final int RECT_HEIGHT = 100;
    private static final int RECT_WIDTH = 100;
    private int rectX1 = 0;
    private int rectY1 = 0;
    private int rectX2 = RECT_WIDTH;
    private int rectY2 = RECT_HEIGHT;
    private int rectSpeedX = 3;
    private int rectSpeedY = 2;

    private static final int UPDATE_RATE = 30; 

    public BouncingBallSimpleEx2() {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    rectX1 += rectSpeedX;
                    rectY1 += rectSpeedY;
                    rectX2 += rectSpeedX;
                    rectY2 += rectSpeedY;
                    if (rectX2 >= BOX_WIDTH) {
                        rectSpeedX = -rectSpeedX;
                    } else if (rectY2 >= BOX_HEIGHT) {
                        rectSpeedY = -rectSpeedY;
                    }
                    if (rectX1 <= 0) {
                        rectSpeedX = -rectSpeedX;
                    } else if (rectY1 <= 0) {
                        rectSpeedY = -rectSpeedY;
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

        var cr = new ColoredRect(rectX1, rectY1, rectX2, rectY2);
        cr.setInColor(Color.gray);
        cr.setOutColor(Color.orange);
        cr.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        StringBuilder sb = new StringBuilder();
        try (Formatter formatter = new Formatter(sb)) {
            formatter.format("Rectangle TL(%3d,%3d); BR(%3d,%3d) Speed=(%2d,%2d)", rectX1, rectY1, rectX2, rectY2,
                    rectSpeedX, rectSpeedY);
        }
        g.drawString(sb.toString(), 20, 30);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("A Bouncing Ball(Rectangle) Ex2");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new BouncingBallSimpleEx2());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
