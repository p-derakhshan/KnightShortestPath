package com.der.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class ChessBoard extends JPanel {

    public static int[][] FinalMatrix;
    public static int level;
    public static int total;

    public void paint(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(90, 90, 420, 420);
        g.setColor(Color.BLACK);
        g.fillRect(100, 100, 400, 400);
        g.setColor(Color.WHITE);
        for (int i = 100; i <= 400; i += 100) {
            for (int j = 100; j <= 400; j += 100) {
                g.fillRect(i, j, 50, 50);
            }
        }
        g.setColor(Color.WHITE);
        for (int i = 150; i <= 450; i += 100) {
            for (int j = 150; j <= 450; j += 100) {
                g.fillRect(i, j, 50, 50);
            }
        }
        color(g);
    }

    public void color(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Shortest path's lenght : " + (total - 1), 230, 50);
        int x = 0, y = 0;
        Color mycolor = new Color(205,205,205);
        for (int i = 0; i < level; i++) {
            g.setColor(mycolor);
            x = FinalMatrix[i][0] - 1;
            y = FinalMatrix[i][1] - 1;
            g.fillRect((x * 50) + 102, (y * 50) + 102, 46, 46);
            g.setColor(Color.RED);
            g.drawString(String.valueOf(i), (x * 50) + 120, (y * 50) + 130);
        }
        if (level != 0) {
            Image img = Toolkit.getDefaultToolkit().getImage("src/com/der/graphics/knight.png");
            g.drawImage(img, (x * 50) + 104, (y * 50) + 103, 40, 44, null, this);
        }
    }

}
